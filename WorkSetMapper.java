package com.spr.flux.methods.jobs.imports.importFromRL;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPackage;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfWorkitem;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfId;
import com.spr.flux.common.dm.DMHelper;
import com.spr.flux.common.dm.DMSession;
import com.spr.flux.common.dm.logic.BookFmBm;
import com.spr.flux.common.dm.logic.Chapter;
import com.spr.flux.common.dm.logic.DiscreteObject;
import com.spr.flux.common.dm.logic.Flux;
import com.spr.flux.common.dm.logic.Project;
import com.spr.flux.common.dm.logic.SysObject;
import com.spr.flux.common.dm.someattrvalues.BookFmBmType;
import com.spr.flux.common.log.Logger;
import com.spr.flux.common.util.dmutil.DocbaseConstants;
import com.spr.flux.common.util.dmutil.datatype.DMWorkitemHelper;

/**
 * Queries the inbox of a user, creates a WorkSet of all components of a task and puts it into a map
 * with the chapter name as key.
 * 
 * @author Gerald Buehner
 * @author Surender Kumar (copied from BWF and updated)
 * @author Pavel Ilinskiy (refactoring)
 */
public class WorkSetMapper {

    /**
     * Queries all tasks and maps the components of the workitem packages.
     * 
     * @param session
     *            session
     * @param userName
     *            userName who owns the tasks
     * @throws DfException
     */
    public WorkSetMapper(final DMSession session, final String userName) throws DfException {
        this.flux = Flux.get(session);

        IDfCollection col = null;
        try {
            col = session.getDfcSession().getTasks(userName, IDfSession.DF_TASKS,
                    NO_ADDITIONAL_ATTRIBUTES, DocbaseConstants.TASK_NAME);
            while (col.next()) {
                visitTask(session, col);
            }
        } finally {
            DMHelper.close(col);
        }
    }

    /**
     * @return work map.
     */
    public Map<String, WorkSet> getMap() {
        return map;
    }

    /**
     * @param objectName
     *            .
     * @return work set from map.
     */
    public WorkSet getWorkSet(final String objectName) {
        return map.get(objectName);
    }

    /**
     * Visit RL user task.
     * 
     * @param session
     *            docbase session wrapper.
     * @param col
     *            task.
     * @throws DfException
     */
    private void visitTask(final DMSession session, final IDfCollection col) throws DfException {
        final String taskName = col.getString(DocbaseConstants.TASK_NAME);
        final IDfId workitemID = col.getId(DocbaseConstants.ITEM_ID);
        final Date dateSent = col.getTime(DocbaseConstants.DATE_SENT).getDate();

        LOGGER.debug("Processing '" + taskName + "' task. Workitem ID: " + workitemID);

        // Skip tasks not in dormant or acquired state
        final String taskState = col.getString(DocbaseConstants.TASK_STATE);
        if (!DocbaseConstants.DORMANT.equals(taskState)
                && !DocbaseConstants.ACQUIRED.equals(taskState)) {
            LOGGER.info("Skipping " + taskState + " '" + taskName + "' task");
        } else {
            final IDfWorkitem workitem = (IDfWorkitem) session.getObject(workitemID);
            try {
                createWorkSet(workitem, dateSent);
            } catch (final Exception e) {
                LOGGER.error("Could not handle '" + taskName + "' task: " + e.getMessage(), e);
                workitem.pause();
            }
        }
    }

    /**
     * Create work set.
     * 
     * @param workitem
     *            RL waiting workitem.
     * @param dateSent
     *            Workitem sent date.
     * @throws DfException
     */
    private void createWorkSet(final IDfWorkitem workitem, final Date dateSent) throws DfException {
        IDfPackage pack = DMWorkitemHelper.getFirstPackageByType(workitem,
                DiscreteObject.SPR_DISCRETE_OBJECT);
        if (null != pack) {
            LOGGER.debug("Task contains a chapter.");
            processDiscreteObject(workitem, dateSent, pack);
        } else {
            pack = DMWorkitemHelper.getFirstPackageByType(workitem, Project.SPR_PROJECT);
            if (null != pack) {
                LOGGER.debug("Task contains a project.");
                processProject(workitem, dateSent, pack);
            } else {
                LOGGER.warn("No package found - task skipped!");
            }
        }

    }

    /**
     * Process discrete object workitem.
     * 
     * @param workitem
     *            workitem.
     * @param dateSent
     *            workitem date sent.
     * @param pack
     *            package.
     * @throws DfException
     */
    private void processDiscreteObject(final IDfWorkitem workitem, final Date dateSent,
            final IDfPackage pack) throws DfException {
        final Chapter chapter = (Chapter) getComponentObject(pack);
        final ChapterWorkSet workSet = new ChapterWorkSet(workitem, dateSent, chapter);
        putWorkSet(chapter.getObjectName(), workSet);
    }

    /**
     * Process project object package.
     * 
     * @param workitem
     *            workitem.
     * @param dateSent
     *            workitem date sent
     * @param pack
     *            package.
     * @throws DfException
     */
    private void processProject(final IDfWorkitem workitem, final Date dateSent,
            final IDfPackage pack) throws DfException {
        final Project project = (Project) getComponentObject(pack);

        for (final Chapter chapter : project.getEdition().getChapters()) {
            if (!chapter.isAPageObject()) {
                final ChapterProjectWorkSet workSet = new ChapterProjectWorkSet(workitem, dateSent,
                        project, chapter);
                putWorkSet(chapter.getObjectName(), workSet);
            }
        }

        final BookFmBm bookBackmatter = project.getEdition().getBookmatter(
                BookFmBmType.BOOK_BACK_MATTER);

        // Book Back Matter object null check.
        if (null != bookBackmatter) {
            final BookFmBmProjectWorkSet workSet = new BookFmBmProjectWorkSet(
                    workitem,
                    dateSent,
                    project,
                    bookBackmatter);
            putWorkSet(bookBackmatter.getObjectName(), workSet);
        }
    }

    /**
     * Puts a work set to the map and logs a warning if there was already one using the chapter
     * name.
     * 
     * @param key
     *            the name of the chapter
     * @param workSet
     *            the work set
     */
    private void putWorkSet(final String key, final WorkSet workSet) {
        if (null != this.map.put(key, workSet)) {
            LOGGER.warn("There already was a task containing object '" + key + "'.");
        }
    }

    /**
     * Gets the component object from a package.
     * 
     * @param pack
     *            the package
     * @return the component
     * @throws DfException
     *             on session or attribute access error
     */
    private SysObject getComponentObject(final IDfPackage pack) throws DfException {
        final IDfId componentID = pack.getId(DocbaseConstants.R_COMPONENT_ID);
        return (SysObject) this.flux.getObject(componentID);
    }

    /**
     * Framework.
     */
    private final Flux flux;

    /** Maps names to WorkSets. */
    private final Map<String, WorkSet> map = new HashMap<String, WorkSet>();

    /** The logger for this class. */
    private static final Logger LOGGER = new Logger(WorkSetMapper.class);

    /** Used to add no attributes on querying. */
    private static final String NO_ADDITIONAL_ATTRIBUTES = null;
}