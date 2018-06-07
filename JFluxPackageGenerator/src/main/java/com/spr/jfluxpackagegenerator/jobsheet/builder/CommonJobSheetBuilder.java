package com.spr.jfluxpackagegenerator.jobsheet.builder;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.JobSheet;

/**
 * Common interface for article and issue jobsheet builder
 * 
 * @author Alexey Dergalev
 */
public interface CommonJobSheetBuilder {
    
    /**
     * Get JobSheet.
     * 
     * @return jobsheet, never null
     */
    JobSheet getProduct();
    
    /**
     * Build jobsheet;
     */
    void build();
    
    /**
     * Get map which contains pair <FileRef value - Template name>
     * 
     * @return map, never null;
     */
    Map<String, String> getTemplatesForFiles();
    
    /**
     * Get jobsheet name for the builder information.
     * 
     * @return
     */
    String getJobSheetName();
}
