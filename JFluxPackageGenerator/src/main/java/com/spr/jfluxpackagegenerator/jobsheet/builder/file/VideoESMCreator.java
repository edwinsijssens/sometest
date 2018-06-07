package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.MediaObject;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.VideoObject;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;
import com.spr.jfluxpackagegenerator.model.files.TemplateFileNames;

public class VideoESMCreator implements FileESMElementCreator {
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory, final int esmNumber) {
        final File file = objectFactory.createFile();
        final MediaObject mObject = objectFactory.createMediaObject();
        final VideoObject object = objectFactory.createVideoObject();
        final String fileRef =
                JobSheetFileNames.ESM.format(new String[] {
                        fileNamePrefix,
                        Integer.toString(esmNumber),
                        "wmv" });
        object.setFileRef(fileRef);
        mObject.getVideoObject().add(object);
        file.setMediaObject(mObject);
        fileNameConductor.put(fileRef, TemplateFileNames.VIDEO_ESM);
        return file;
    }
    
}
