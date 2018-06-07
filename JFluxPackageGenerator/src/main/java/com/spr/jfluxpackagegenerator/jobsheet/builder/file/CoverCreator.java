package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.BodyRef;
import com.spr.jfluxpackagegenerator.jobsheet.Cover;
import com.spr.jfluxpackagegenerator.jobsheet.CoverFigure;
import com.spr.jfluxpackagegenerator.jobsheet.CoverInfo;
import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ImageObject;
import com.spr.jfluxpackagegenerator.jobsheet.MediaObject;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.model.enums.TargetType;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;
import com.spr.jfluxpackagegenerator.model.files.TemplateFileNames;

public class CoverCreator implements FileElementCreator {
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory obj) {
        final File file = obj.createFile();
        
        final Cover cover = obj.createCover();
        
        final CoverInfo covInfo = obj.createCoverInfo();
        covInfo.setCoverFirstPage("A1");
        covInfo.setCoverLastPage("A4");
        cover.setCoverInfo(covInfo);
        
        final CoverFigure figure = obj.createCoverFigure();
        final MediaObject mObject = obj.createMediaObject();
        final ImageObject image = obj.createImageObject();
        
        final String coverFigureFileRef =
                JobSheetFileNames.COVER_FIGURE.format(new String[] { fileNamePrefix });
        image.setColor("Color");
        image.setFileRef(coverFigureFileRef);
        image.setFormat("TIFF");
        image.setRendition("Print");
        image.setType("Halftone");
        
        mObject.setImageObject(image);
        
        figure.setMediaObject(mObject);
        
        final String objectName = "Cover";
        final String fileRefValue =
                JobSheetFileNames.ISSUE_PRINT_PDF.format(new String[] {
                        fileNamePrefix,
                        objectName,
                        objectName });
        
        final BodyRef bodyRef = obj.createBodyRef();
        bodyRef.setTargetType(TargetType.PrintPDF.toString());
        bodyRef.setFileRef(fileRefValue);
        
        fileNameConductor.put(coverFigureFileRef, TemplateFileNames.COVER_FIGURE);
        fileNameConductor.put(fileRefValue, TemplateFileNames.COVER);
        
        cover.setBodyRef(bodyRef);
        cover.setCoverFigure(figure);
        file.setCover(cover);
        file.setDiscreteObjectID(objectName);
        
        return file;
    }
    
}
