package com.spr.flux.methods.jobs.imports.importFromRL;

import java.util.Date;

import com.documentum.fc.client.IDfWorkitem;

/**
 * Represents task (workitem) and corresponding business object(s).
 * 
 * @author Surender Kumar (copied from BWF and updated)
 */
public abstract class WorkSet {

    /** RL waiting workitem. */
    private final IDfWorkitem workitem;

    /** Woritem date sent. */
    private final Date dateSent;

    /**
     * The constructor for this class.
     * 
     * @param workitem
     *            the work item
     * @param dateSent
     *            the date when the task was created
     */
    public WorkSet(final IDfWorkitem workitem, final Date dateSent) {
        this.workitem = workitem;
        this.dateSent = dateSent;
    }

    /**
     * Gets the work item.
     * 
     * @return the work item
     */
    public IDfWorkitem getWorkitem() {
        return this.workitem;
    }

    /**
     * @return the dateSent.
     */
    public Date getDateSent() {
        return dateSent;
    }
}
