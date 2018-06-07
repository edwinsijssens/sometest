package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.APageFile;
import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.model.enums.APageType;
import com.spr.jfluxpackagegenerator.model.enums.TargetType;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;

public class APageCreator implements FileElementCreator {
    
    private final String templateFileName;
    
    private final APageType aPageType;
    
    private final String pageValue;
    
    private final String position;
    
    public APageCreator(final String tempFileName, final APageType type, final String page,
                        final String pos) {
        templateFileName = tempFileName;
        aPageType = type;
        pageValue = page;
        position = pos;
    }
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory obj) {
        final File file = obj.createFile();
        
        final APageFile apageFile = obj.createAPageFile();
        
        final String objectName = "Issue" + position;
        final String fileRefValue =
                JobSheetFileNames.ISSUE_PRINT_PDF.format(new String[] {
                        fileNamePrefix,
                        objectName,
                        objectName });
        
        fileNameConductor.put(fileRefValue, templateFileName);
        
        apageFile.setFileRef(fileRefValue);
        apageFile.setFirstPage(pageValue);
        apageFile.setLastPage(pageValue);
        apageFile.setTargetType(TargetType.PrintPDF.toString());
        apageFile.setPosition(position);
        apageFile.setType(aPageType.toString());
        
        file.setAPageFile(apageFile);
        file.setDiscreteObjectID(aPageType.toString());
        
        return file;
    }
    
}
