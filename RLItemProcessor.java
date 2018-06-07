package com.spr.flux.methods.jobs.imports.importFromRL;

import java.io.IOException;

import com.documentum.fc.common.DfException;
import com.spr.flux.common.dm.DMSession;

/**
 * Process RL Item.
 * 
 * @author Pavel Ilinskiy
 */
public interface RLItemProcessor {

    /**
     * Complete RL waiting.
     * 
     * @throws DfException
     */
    void completeWorkitem() throws DfException;

    /**
     * Handle error.
     * 
     * @param session
     *            Docbase session wrapper.
     * @param e
     *            exception.
     * @throws DfException
     */
    void onError(DMSession session, Exception e) throws DfException;

    /**
     * Move RL item.
     * 
     * @return true if moving was success.
     * @throws DfException
     * @throws IOException
     */
    boolean moveFile() throws DfException, IOException;

    /**
     * @return item workset.
     */
    WorkSet getWorkSet();
}
