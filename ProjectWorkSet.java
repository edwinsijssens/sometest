package com.spr.flux.methods.jobs.imports.importFromRL;

import java.util.Date;

import com.documentum.fc.client.IDfWorkitem;
import com.spr.flux.common.dm.logic.Project;

/**
 * Represents project workset for Import RL Job.
 * 
 * @author Pavel Ilinskiy
 */
public abstract class ProjectWorkSet extends WorkSet {

    /** Workflow project. */
    private final Project project;

    /**
     * Constructor.
     * 
     * @param workitem
     *            Waiting RL workitem.
     * @param dateSent
     *            Workitem sent date.
     * @param project
     *            Workflow project.
     */
    public ProjectWorkSet(final IDfWorkitem workitem, final Date dateSent, final Project project) {
        super(workitem, dateSent);
        this.project = project;
    }

    /**
     * @return Workflow project.
     */
    public Project getProject() {
        return project;
    }
}
