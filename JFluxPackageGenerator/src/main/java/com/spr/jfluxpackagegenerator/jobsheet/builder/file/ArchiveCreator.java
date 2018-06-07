package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.text.Format;
import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.Archive;
import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.model.enums.ArchiveContent;

public class ArchiveCreator implements FileElementCreator {
    
    private final Format jobsheetFileName;
    
    private final String templateFileName;
    
    private final ArchiveContent archiveContent;
    
    public ArchiveCreator(final Format jsFileName, final String tempFileName,
                          final ArchiveContent content) {
        jobsheetFileName = jsFileName;
        templateFileName = tempFileName;
        archiveContent = content;
    }
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory) {
        final File file = objectFactory.createFile();
        final Archive item = objectFactory.createArchive();
        
        final String fileRefValue = jobsheetFileName.format(new String[] { fileNamePrefix });
        fileNameConductor.put(fileRefValue, templateFileName);
        
        item.setFileRef(fileRefValue);
        item.setContent(archiveContent.toString());
        file.setArchive(item);
        
        return file;
    }
    
}
