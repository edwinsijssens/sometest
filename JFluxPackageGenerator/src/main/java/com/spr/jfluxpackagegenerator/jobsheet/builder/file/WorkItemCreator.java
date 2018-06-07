package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.text.Format;
import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.WorkItem;
import com.spr.jfluxpackagegenerator.model.enums.ItemType;

public class WorkItemCreator implements FileElementCreator {
    
    private final Format jobsheetFileName;
    
    private final String templateFileName;
    
    private final ItemType itemType;
    
    public WorkItemCreator(final Format jsFileName, final String tempFileName, final ItemType type) {
        jobsheetFileName = jsFileName;
        templateFileName = tempFileName;
        itemType = type;
    }
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory) {
        final File file = objectFactory.createFile();
        final WorkItem item = objectFactory.createWorkItem();
        
        final String fileRefValue = jobsheetFileName.format(new String[] { fileNamePrefix });
        fileNameConductor.put(fileRefValue, templateFileName);
        
        item.setFileRef(fileRefValue);
        item.setItemType(itemType.toString());
        file.setWorkItem(item);
        return file;
    }
    
}
