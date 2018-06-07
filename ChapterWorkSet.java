package com.spr.flux.methods.jobs.imports.importFromRL;

import java.util.Date;

import com.documentum.fc.client.IDfWorkitem;
import com.spr.flux.common.dm.logic.Chapter;

/**
 * Represents chpater workset in discrete workflow for Import RL Job.
 * 
 * @author Pavel Ilinskiy
 */
public class ChapterWorkSet extends WorkSet {

    /** Book chapter. */
    private Chapter chapter;

    /**
     * Constructor.
     * 
     * @param workitem
     *            Waiting RL workitem.
     * @param dateSent
     *            Workitem sent date.
     */
    public ChapterWorkSet(final IDfWorkitem workitem, final Date dateSent) {
        super(workitem, dateSent);
    }

    /**
     * Constructor.
     * 
     * @param workitem
     *            Waiting RL workitem.
     * @param dateSent
     *            Workitem sent date.
     * @param chapter
     *            Book chapter.
     */
    public ChapterWorkSet(final IDfWorkitem workitem, final Date dateSent, final Chapter chapter) {
        this(workitem, dateSent);
        this.chapter = chapter;
    }

    /**
     * @return book chapter.
     */
    public Chapter getChapter() {
        return chapter;
    }
}
