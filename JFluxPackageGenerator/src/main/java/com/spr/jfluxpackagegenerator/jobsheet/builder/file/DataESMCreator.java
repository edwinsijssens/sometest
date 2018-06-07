package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.DataObject;
import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.MediaObject;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;
import com.spr.jfluxpackagegenerator.model.files.TemplateFileNames;

public class DataESMCreator implements FileESMElementCreator {
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory objectFactory, final int esmNumber) {
        final File file = objectFactory.createFile();
        final MediaObject mObject = objectFactory.createMediaObject();
        final DataObject object = objectFactory.createDataObject();
        final String fileRef =
                JobSheetFileNames.ESM.format(new String[] {
                        fileNamePrefix,
                        Integer.toString(esmNumber),
                        "pdf" });
        object.setFileRef(fileRef);
        mObject.getDataObject().add(object);
        file.setMediaObject(mObject);
        fileNameConductor.put(fileRef, TemplateFileNames.DATA_ESM);
        return file;
    }
    
}
