package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;

/**
 * The interface of factory method which creates one File element inside FilesToPublisher section.
 * 
 * @author Alexey Dergalev
 */
public interface FileElementCreator {
    
    /**
     * Create a File element.
     * 
     * @param fileNamePrefix file name prefix for FileRef attribute
     * @param fileNameConductor is the map retains all FileRef attribute values
     * @param obj object factory to create elements
     * @return new File element
     */
    File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory obj);
}
