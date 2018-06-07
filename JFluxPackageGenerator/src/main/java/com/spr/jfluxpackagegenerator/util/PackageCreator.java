package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.Document;

/**
 * The class is designed to create folders and files for FSV package and create a zip archive.
 * 
 * @author Alexey Dergalev
 */
public class PackageCreator {
    
    
    private final Document jobsheet;
    
    private final Settings settings;
    
    public PackageCreator(final Document js, final Settings settings) {
        jobsheet = js;
        this.settings = settings;
    }
    
    public File createPackage(final String jobsheetName, final String packageName,
            final Map<String, String> templatesMap) throws Exception {
        
        // create a sub-folder inside the work folder
        final String subFolderName = packageName.substring(0, packageName.lastIndexOf(".")); // dot
                                                                                             // before
                                                                                             // extension
        final File workFolder = new File(settings.getWorkPath(), subFolderName);
        // FileHelper.deleteRecursive(workFolder); // if already exists
        workFolder.mkdirs();
        
        // create a zip file
        final File zipFile = new File(settings.getDestinationPath(), packageName);
        
        // create jobsheet file
        final File jobSheetFile = new File(workFolder.getAbsoluteFile(), jobsheetName);
        final OutputStream ous = new FileOutputStream(jobSheetFile);
        DOMHelper.writeDocument(jobsheet, ous);
        StreamHelper.close(ous);
        
        // populate package internal stuff
        createPackageFiles(workFolder, templatesMap);
        
        // create zip archive
        final ZipCreator zipCreator = new ZipCreator();
        zipCreator.zip(workFolder.listFiles(), zipFile);
        
        // clean-up work sub-folder
        
        return zipFile;
    }
    
    private void createPackageFiles(final File workFolder, final Map<String, String> templatesMap)
            throws Exception {
        
        final Iterator<String> iter = templatesMap.keySet().iterator();
        while (iter.hasNext()) {
            final String fileName = iter.next();
            
            // check if it is a relative path
            if (fileName.contains("/")) {
                // check if sub-folder exists
                final String subFolderName = fileName.substring(0, fileName.lastIndexOf("/"));
                final File subFolder =
                        new File(workFolder.getAbsolutePath() + File.separator + subFolderName);
                if (!subFolder.exists()) {
                    subFolder.mkdirs();
                }
            }
            
            final File file = new File(workFolder.getAbsolutePath() + File.separator + fileName);
            final String templateName = templatesMap.get(fileName);
            String resourceName = "/com/spr/jfluxpackagegenerator/templates/" + templateName;
            InputStream templateStream = PackageCreator.class.getResourceAsStream(resourceName);
            if (templateStream == null) {
                // this way some templates are hidden from IDE validator
                resourceName += ".dontvalidate";
                templateStream = PackageCreator.class.getResourceAsStream(resourceName);
                if (templateStream == null) {
                    throw new Exception("Template " + templateName + " not found");
                }
            }
            
            FileHelper.copyFileAndCloseInput(templateStream, file);
            
            if (fileName.endsWith("Article.xml")) {
                // create A++
                final String articleFileName = fileName.contains("/")
                        ? fileName.substring(fileName.lastIndexOf("/") + 1) : fileName;
                createAPlusPlusFile(file, articleFileName, fileName);
            }
        }
        
    }
    
    private void createAPlusPlusFile(final File aPlusPlusFile, final String shorArticleFileName,
            final String fileRef) throws Exception {
        final ArticleXMLCreator creator =
                new ArticleXMLCreator(aPlusPlusFile, shorArticleFileName, fileRef, jobsheet);
        creator.create();
    }
    
}
