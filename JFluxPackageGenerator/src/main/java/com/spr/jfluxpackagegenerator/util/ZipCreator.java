package com.spr.jfluxpackagegenerator.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipCreator {
    
    
    /**
     * {@inheritDoc}
     */
    private static final Logger LOG = Logger.getLogger(ZipCreator.class);
    
    public void zip(final File[] filesToZip, final File targetFile) throws IOException {
        
        BufferedOutputStream fout = null;
        ZipOutputStream zout = null;
        try {
            fout = new BufferedOutputStream(new FileOutputStream(targetFile));
            zout = new ZipOutputStream(fout);
            
            File currentSource = null;
            for (int i = 0; i < filesToZip.length; i++) {
                currentSource = filesToZip[i];
                if (currentSource.isDirectory()) {
                    addDirectoryRecursively(currentSource, zout, "");
                } else {
                    addFile(currentSource, zout, "");
                }
            }
        } catch (final IOException e) {
            
            LOG.error("IOException in zip() while zipping file is " + e.getMessage());
        } finally {
            if (null != zout) {
                zout.finish();
                StreamHelper.close(zout);
            }
            StreamHelper.close(fout);
        }
    }
    
    /**
     * Adds a directory and its content (i.e. other directories and files) recursively to the zip
     * stream.
     * 
     * @param directory The directory to be added.
     * @param zipStream The zip stream to which the contents are added.
     * @param path Path of the folder that contains the new one being created.
     * @throws IOException
     */
    public void addDirectoryRecursively(final File directory, final ZipOutputStream zipStream,
            final String path) throws IOException {
        
        final StringBuilder bufPath = new StringBuilder(32);
        bufPath.append(path).append(directory.getName()).append("/");
        final String curRelativePath = bufPath.toString();
        zipStream.putNextEntry(new ZipEntry(curRelativePath));
        
        final File allFiles[] = directory.listFiles();
        for (int ctrFiles = 0; ctrFiles < allFiles.length; ctrFiles++) {
            final File curFile = allFiles[ctrFiles];
            if (curFile.isDirectory()) {
                addDirectoryRecursively(curFile, zipStream, curRelativePath);
            } else {
                addFile(curFile, zipStream, curRelativePath);
            }
        }
    }
    
    /**
     * Adds a single file to the zip.
     * 
     * @param file the file to be added.
     * @param zipStream the zip stream to which the file will be added
     * @param path The path of the folder that contains this file.
     * @throws IOException
     */
    private void addFile(final File file, final ZipOutputStream zipStream, final String path)
            throws IOException {
        LOG.info("Deflating file: " + file.getName());
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            
            final int bufSize = 1024 * 30;
            final byte ipBuf[] = new byte[bufSize];
            int lenRead = 0;
            
            final String filename = path + file.getName();
            final ZipEntry zipEntry = new ZipEntry(filename);
            if (file.getName().toLowerCase().endsWith("zip")) {
                zipEntry.setMethod(ZipEntry.STORED);
                zipEntry.setSize(file.length());
                zipEntry.setCompressedSize(file.length());
                final CRC32 crc = new CRC32();
                crc.reset();
                while ((lenRead = in.read(ipBuf)) > 0) {
                    if (crc != null) {
                        crc.update(ipBuf, 0, lenRead);
                    }
                }
                in.close();
                in = new BufferedInputStream(new FileInputStream(file));
                zipEntry.setCrc(crc.getValue());
            }
            zipStream.putNextEntry(zipEntry);
            
            while ((lenRead = in.read(ipBuf)) > 0) {
                zipStream.write(ipBuf, 0, lenRead);
            }
            
        } finally {
            zipStream.closeEntry();
            StreamHelper.close(in);
        }
    }
    
}