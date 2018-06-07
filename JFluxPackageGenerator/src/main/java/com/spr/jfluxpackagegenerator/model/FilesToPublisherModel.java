package com.spr.jfluxpackagegenerator.model;

import java.util.List;

import com.spr.jfluxpackagegenerator.model.files.FileEntry;

/**
 * The interface is designed to provide methods for files to package inclusion.
 * 
 * @author Alexey Dergalev
 */
public interface FilesToPublisherModel {
    
    boolean isValidPitStopReport();
    
    List<FileEntry> getFilesEntries();
    
}
