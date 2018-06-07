package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * Utility class for file operations.
 * 
 * @author Alexey Dergalev
 */
public class FileHelper {
    
    
    private static final Logger LOG = Logger.getLogger(FileHelper.class);
    
    /**
     * Copy a file. <br/>
     * 
     * @param src The source input stream
     * @param dst The destination file.
     */
    public static void copyFileAndCloseInput(final InputStream src, final File dst)
            throws IOException {
        try (InputStream fin = src; FileOutputStream fout = new FileOutputStream(dst);) {
            IOUtils.copy(fin, fout);
            dst.setWritable(true, false);
        }
    }
    
    /**
     * Recurses through directory trees and deletes all files and directories. Does nothing if the
     * file does not exist.
     * 
     * @param f A file or directory.
     * @param check throw an exception if deletion failed.
     */
    public static void deleteRecursive(final File f, final boolean check) throws IOException {
        
        if (!f.exists()) {
            return;
        }
        
        if (f.isDirectory()) {
            deleteRecursive(f.listFiles());
        }
        if (!f.delete()) {
            if (check) {
                throw new IOException("Cannot delete '" + f.getAbsolutePath() + "'.");
            } else {
                LOG.info("Cannot delete '" + f.getAbsolutePath() + "'.");
                
            }
        }
    }
    
    /**
     * Recurses through directory trees and deletes all files and directories.
     * 
     * @param files Some files or directories.
     * @param check throw an exception if deletion failed.
     */
    public static void deleteRecursive(final File[] files, final boolean check) throws IOException {
        for (int i = 0; i < files.length; ++i) {
            deleteRecursive(files[i], check);
        }
    }
    
    /**
     * Recurses through directory trees and deletes all files and directories. Does not follow
     * symbolic links.
     * 
     * @param files Some files or directories.
     */
    public static void deleteRecursive(final File... files) throws IOException {
        for (int i = 0; i < files.length; ++i) {
            deleteRecursive(files[i]);
        }
    }
    
    /**
     * Recurses through directory trees and deletes all files and directories. Does not follow
     * symbolic links. Does nothing if the file does not exist.
     * 
     * @param f A file or directory.
     */
    public static void deleteRecursive(final File f) throws IOException {
        deleteRecursive(f, false);
    }
    
    /**
     * Get a file name without extension.
     * 
     * @param f The file.
     * @return The file name (not the full path) without extension or the original file name if the
     *         name has no dot or only one at the beginning.
     */
    public static String getNameWithoutExtension(final File f) {
        final String fileName = f.getName();
        final int dotPos = fileName.lastIndexOf('.');
        return dotPos < 1 ? fileName : fileName.substring(0, dotPos);
    }
    
}
