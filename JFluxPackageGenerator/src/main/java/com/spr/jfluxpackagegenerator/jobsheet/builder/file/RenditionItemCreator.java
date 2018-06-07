package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.text.Format;
import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.RenditionItem;
import com.spr.jfluxpackagegenerator.model.enums.TargetType;

public class RenditionItemCreator implements FileElementCreator {
    
    private final Format jobsheetFileName;
    
    private final String templateFileName;
    
    private final TargetType targetType;
    
    public RenditionItemCreator(final Format jsFileName, final String tempFileName,
                                final TargetType type) {
        jobsheetFileName = jsFileName;
        templateFileName = tempFileName;
        targetType = type;
    }
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory) {
        final File file = objectFactory.createFile();
        final RenditionItem item = objectFactory.createRenditionItem();
        
        final String fileRefValue = jobsheetFileName.format(new String[] { fileNamePrefix });
        fileNameConductor.put(fileRefValue, templateFileName);
        
        item.setFileRef(fileRefValue);
        item.setTargetType(targetType.toString());
        file.setRenditionItem(item);
        return file;
    }
    
}
