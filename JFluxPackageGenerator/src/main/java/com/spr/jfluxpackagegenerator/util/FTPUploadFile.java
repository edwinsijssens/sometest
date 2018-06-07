package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.spr.jfluxpackagegenerator.config.FTPServer;
import com.spr.jfluxpackagegenerator.model.enums.Vendor;

/**
 * A program to upload files from local computer/folder to a remote FTP server using Apache Commons
 * Net API.
 * 
 * @author shah02
 */
public class FTPUploadFile {
    
    
    private static final Logger LOG = Logger.getLogger(FTPUploadFile.class);
    
    private final Settings settings;
    
    public FTPUploadFile(final Settings settings) {
        this.settings = settings;
    }
    
    public String ftpUploadFile(final String environment, final Vendor vendor,
            final String sZipFileName, final String sZipFilePath) throws IOException {
        
        String ftpServer = "";
        final int port = 21;
        
        String user = "";
        String pass = "";
        
        String sEnvPath = "";
        String sMessage = "";
        InputStream inputStream = null;
        final FTPClient ftpClient = new FTPClient();
        final com.spr.jfluxpackagegenerator.config.Environment envConfig =
                settings.getConfig().getEnvsByName().get(environment);
        final FTPServer serverConfig = envConfig.getFtpServer();
        ftpServer = serverConfig.getHostname();
        user = serverConfig.getUsername();
        pass = serverConfig.getPassword();
        sEnvPath = envConfig.getDirsByVendor().get(vendor.toString());
        try {
            ftpClient.connect(ftpServer, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            final File firstLocalFile = new File(sZipFilePath);
            
            final String firstRemoteFile = sEnvPath + sZipFileName + ".zip";
            inputStream = new FileInputStream(firstLocalFile);
            
            if (ftpClient.storeFile(firstRemoteFile, inputStream)) {
                sMessage = "Success";
                
            }
            
        } catch (final IOException ex) {
            LOG.error("IOException in ftpUploadFile() is : " + ex.getMessage());
            
        } catch (final Exception exe) {
            
            LOG.error("Exception in ftpUploadFile() is " + exe.getMessage());
        } finally {
            closeFileStream(inputStream);
            closeFTPConnection(ftpClient);
        }
        return sMessage;
    }
    
    protected void closeFileStream(final InputStream inputStream) {
        try {
            inputStream.close();
            
        } catch (final Exception e) {
            LOG.error("Exception in closeFileStream() is " + e.getMessage());
        }
    }
    
    protected void closeFTPConnection(final FTPClient ftpClient) {
        try {
            
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (final Exception e) {
            LOG.error("Exception in closeFTPConnection() is " + e.getMessage());
        }
    }
}