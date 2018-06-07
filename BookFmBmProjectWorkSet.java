package com.spr.flux.methods.jobs.imports.importFromRL;

import java.util.Date;

import com.documentum.fc.client.IDfWorkitem;
import com.spr.flux.common.dm.logic.BookFmBm;
import com.spr.flux.common.dm.logic.Project;

/**
 * Represents bookmatter workset for Import RL Job.
 * 
 * @author Pavel Ilinskiy
 */
public class BookFmBmProjectWorkSet extends ProjectWorkSet {

    /** Bookmatter. */
    private final BookFmBm bookFmBm;

    /**
     * Constructor.
     * 
     * @param workitem
     *            Waiting RL workitem.
     * @param dateSent
     *            Workitem sent date.
     * @param project
     *            Workflow project.
     * @param bookFmBm
     *            Bookmatter.
     */
    public BookFmBmProjectWorkSet(final IDfWorkitem workitem, final Date dateSent,
            final Project project, final BookFmBm bookFmBm) {
        super(workitem, dateSent, project);
        this.bookFmBm = bookFmBm;
    }

    /**
     * @return bookmatter.
     */
    public BookFmBm getBookmatter() {
        return bookFmBm;
    }
}
