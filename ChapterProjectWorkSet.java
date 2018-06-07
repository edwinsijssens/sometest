package com.spr.flux.methods.jobs.imports.importFromRL;

import java.util.Date;

import com.documentum.fc.client.IDfWorkitem;
import com.spr.flux.common.dm.logic.Chapter;
import com.spr.flux.common.dm.logic.Project;

/**
 * Represents chapter workset in project workflow for Import RL Job.
 * 
 * @author Pavel Ilinskiy
 */
public class ChapterProjectWorkSet extends ProjectWorkSet {

    /** Book chapter. */
    private final Chapter chapter;

    /**
     * Constructor.
     * 
     * @param workitem
     *            Waiting RL workitem.
     * @param dateSent
     *            Workitem sent date.
     * @param project
     *            Workflow project.
     * @param chapter
     *            Book chapter.
     */
    public ChapterProjectWorkSet(final IDfWorkitem workitem, final Date dateSent,
            final Project project, final Chapter chapter) {
        super(workitem, dateSent, project);
        this.chapter = chapter;
    }

    /**
     * @return book chapter.
     */
    public Chapter getChapter() {
        return chapter;
    }
}
