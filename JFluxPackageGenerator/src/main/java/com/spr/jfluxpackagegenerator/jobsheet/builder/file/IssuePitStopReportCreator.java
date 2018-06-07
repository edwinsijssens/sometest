package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.WorkItem;
import com.spr.jfluxpackagegenerator.model.enums.ItemType;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;

public class IssuePitStopReportCreator implements FileElementCreator {
    
    private final String templateFileName;
    
    private final ItemType itemType;
    
    private final String objectName;
    
    private final String discreteObjectId;
    
    public IssuePitStopReportCreator(final String tempFileName, final ItemType type,
                                     final String objName, final String discreteObj) {
        templateFileName = tempFileName;
        itemType = type;
        objectName = objName;
        discreteObjectId = discreteObj;
    }
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory) {
        final File file = objectFactory.createFile();
        final WorkItem item = objectFactory.createWorkItem();
        final String suffix = "PitStopReport_" + objectName;
        
        final String fileRefValue =
                JobSheetFileNames.ISSUE_PRINT_PDF.format(new String[] {
                        fileNamePrefix,
                        objectName,
                        suffix });
        
        fileNameConductor.put(fileRefValue, templateFileName);
        
        item.setFileRef(fileRefValue);
        item.setItemType(itemType.toString());
        file.setWorkItem(item);
        file.setDiscreteObjectID(discreteObjectId);
        return file;
    }
    
}
