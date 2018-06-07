package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ImageObject;
import com.spr.jfluxpackagegenerator.jobsheet.MediaObject;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;
import com.spr.jfluxpackagegenerator.model.files.TemplateFileNames;

public class ImageObjectCreator implements FileESMElementCreator {
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory, final int esmNumber) {
        final File file = objectFactory.createFile();
        final MediaObject mObject = objectFactory.createMediaObject();
        final ImageObject object = objectFactory.createImageObject();
        object.setColor("BlackWhite");
        object.setFormat("GIF");
        object.setRendition("HTML");
        object.setType("Linedraw");
        // TODO: check file name format
        final String fileRef =
                JobSheetFileNames.ESM.format(new String[] {
                        fileNamePrefix,
                        Integer.toString(esmNumber),
                        "gif" });
        object.setFileRef(fileRef);
        mObject.setImageObject(object);
        file.setMediaObject(mObject);
        fileNameConductor.put(fileRef, TemplateFileNames.IMAGE_ESM);
        return file;
    }
    
}
