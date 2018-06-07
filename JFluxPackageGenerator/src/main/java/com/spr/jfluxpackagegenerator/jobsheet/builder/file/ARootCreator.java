package com.spr.jfluxpackagegenerator.jobsheet.builder.file;

import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.AplusplusRootFile;
import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;
import com.spr.jfluxpackagegenerator.model.files.TemplateFileNames;

public class ARootCreator implements FileElementCreator {
    
    @Override
    public File create(final String fileNamePrefix, final Map<String, String> fileNameConductor,
            final ObjectFactory obj) {
        final File file = obj.createFile();
        final AplusplusRootFile aroot = obj.createAplusplusRootFile();
        aroot.setFileRef(JobSheetFileNames.AROOT.format(new String[] { fileNamePrefix }));
        file.setAplusplusRootFile(aroot);
        fileNameConductor.put(JobSheetFileNames.AROOT.format(new String[] { fileNamePrefix }),
                TemplateFileNames.AROOT);
        return file;
    }
    
}
