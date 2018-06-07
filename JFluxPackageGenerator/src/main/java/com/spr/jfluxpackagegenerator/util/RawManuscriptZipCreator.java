package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.spr.jfluxpackagegenerator.config.ACDCSftpProvider;
import com.spr.jfluxpackagegenerator.config.SendFiletoSFTP;
import com.spr.jfluxpackagegenerator.model.files.FilesPath;

/**
 * @author cjn6673
 */
public class RawManuscriptZipCreator {
    
    
    private Map<String, String> ftpMap = null;
    
    private String finalzipFileName;
    
    public final Map<String, String> map = new HashMap<String, String>();
    
    /**
     * @param filename ""
     * @param out ""
     * @throws IOException ""
     */
    public void addToZip(final String filename, final ZipOutputStream out) throws IOException {
        
        final File file = new File(filename);
        final FileInputStream fis = new FileInputStream(file);
        final ZipEntry zentry = new ZipEntry(file.getName());
        out.putNextEntry(zentry);
        
        final byte[] buff = new byte[1024];
        int length;
        while ((length = fis.read(buff)) >= 0) {
            out.write(buff, 0, length);
        }
        out.closeEntry();
        fis.close();
        
    }
    
    /**
     * @param path ""
     * @param srcFolder ""
     * @param zip ""
     * @throws Exception ""
     */
    public void addFolderToZip(final String path, final String srcFolder, final ZipOutputStream zip)
            throws Exception {
        final File folder = new File(srcFolder);
        if (folder.list().length == 0) {
            addFileToFolder(path, srcFolder, zip, true);
        } else {
            
            for (final String fileName : folder.list()) {
                if (path.equals("")) {
                    addFileToFolder(folder.getName(), srcFolder + "/" + fileName, zip, false);
                } else {
                    addFileToFolder(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip,
                            false);
                }
            }
        }
        zip.closeEntry();
        zip.flush();
        zip.close();
        
    }
    
    /**
     * @param path ""
     * @param srcFile ""
     * @param zip ""
     * @param flag ""
     * @throws Exception ""
     */
    public void addFileToFolder(final String path, final String srcFile, final ZipOutputStream zip,
            final boolean flag) throws Exception {
        
        final File file = new File(srcFile);
        final FileInputStream in = new FileInputStream(srcFile);
        
        if (flag == true) {
            zip.putNextEntry(new ZipEntry(path + "/" + file.getName() + "/"));
        } else {
            if (file.isDirectory()) {
                
                addFolderToZip(path, srcFile, zip);
            } else {
                
                final byte[] buf = new byte[1024];
                int len;
                zip.putNextEntry(new ZipEntry(path + "/" + file.getName()));
                while ((len = in.read(buf)) > 0) {
                    
                    zip.write(buf, 0, len);
                }
            }
        }
        zip.closeEntry();
        in.close();
        
    }
    
    /**
     * @return finalzipFileName
     */
    public String getfinalzipFileName() {
        return finalzipFileName;
    }
    
    /**
     * @param jrnlId ""
     * @param articleID ""
     * @param date ""
     * @return finalzipName
     */
    public String createFinalZipFileName(final String jrnlId, final String articleID,
            final String date) {
        
        final String finalzipName =
                "JournalID=" + jrnlId + "_Year=2017_ArticleID=" + articleID + "_" + date + ".zip";
        this.finalzipFileName = finalzipName;
        
        return finalzipName;
        
    }
    
    /**
     * @param fileName ""
     * @return localUpload
     * @throws Exception
     */
    
    public String sftpUploader(final String fileName, String envtVal) throws Exception {
        
        final ACDCSftpProvider ftpprovider = new ACDCSftpProvider();
        
        ftpMap = ftpprovider.getEnvironmentProperty(envtVal);
        final String localUpload = FilesPath.finalzipFolder;
        
        final File localFile = new File(fileName);
        
        final FileInputStream fis = new FileInputStream(localFile);
        
        if (!SendFiletoSFTP.putFile(ftpMap.get("host"), ftpMap.get("user"), ftpMap.get("pass"),
                ftpMap.get("dir"), fis, localFile.getName())) {
            
        }
        fis.close();
        return localUpload;
    }
    
}
