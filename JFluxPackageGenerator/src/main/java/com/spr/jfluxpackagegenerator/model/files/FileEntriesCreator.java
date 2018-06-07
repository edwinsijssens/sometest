package com.spr.jfluxpackagegenerator.model.files;

import java.util.List;
import java.util.Set;

/**
 * The interface provides methods to create FileEntry elements.
 * 
 * @author Alexey Dergalev
 */
public interface FileEntriesCreator<T> {
    
    /**
     * Create file entries.
     * 
     * @param keys set of control ids
     * @return list of file entries, never null
     */
    List<FileEntry> createEntries(final Set<T> keys);
}
