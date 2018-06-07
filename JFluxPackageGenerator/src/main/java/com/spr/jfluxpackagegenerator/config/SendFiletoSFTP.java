package com.spr.jfluxpackagegenerator.config;

import java.io.FileInputStream;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author cjn6673
 */
public class SendFiletoSFTP {
    
    
    private static final String STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
    
    private static final Logger LOG = Logger.getLogger(SendFiletoSFTP.class);
    
    /**
     * @param args ""
     */
    public static void main(final String args[]) {
        final JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("cdci", "10.9.3.14", 22);
            session.setConfig(STRICT_HOST_KEY_CHECKING, "no");
            session.setPassword("welk0m");
            session.setTimeout(20000);
            session.connect();
            
            final Channel channel = session.openChannel("sftp");
            
            channel.connect();
            
            final ChannelSftp sftpChannel = (ChannelSftp) channel;
            
            sftpChannel.exit();
            session.disconnect();
        } catch (final Exception e) {
            LOG.error("Unable to connect due to exception" + e);
        }
        
    }
    
    /**
     * @param host ""
     * @param username ""
     * @param password ""
     * @param remoteDir ""
     * @param stream ""
     * @param filename ""
     * @return filestatus
     */
    public static boolean putFile(final String host, final String username, final String password,
            final String remoteDir, final FileInputStream stream, final String filename) {
        final JSch jsch = new JSch();
        Session session = null;
        try {
            
            session = jsch.getSession(username, host, 22);
            session.setConfig(STRICT_HOST_KEY_CHECKING, "no");
            session.setTimeout(20000);
            session.setPassword(password);
            if (!session.isConnected()) {
                session.connect();
            }
            final Channel channel = session.openChannel("sftp");
            channel.connect();
            final ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(remoteDir);
            sftpChannel.put(stream, filename, ChannelSftp.APPEND);
            sftpChannel.exit();
            session.disconnect();
            return true;
        } catch (final Exception e) {
            LOG.error("Unable to put file due to exception" + e);
            return false;
        }
        
    }
    
    /**
     * @param host ""
     * @param username ""
     * @param password ""
     * @param remoteDir ""
     * @param localFileName ""
     * @param filename ""
     * @return file status
     */
    public static boolean putFile(final String host, final String username, final String password,
            final String remoteDir, final String localFileName, final String filename) {
        final JSch jsch = new JSch();
        Session session = null;
        try {
            
            session = jsch.getSession(username, host, 22);
            session.setConfig(STRICT_HOST_KEY_CHECKING, "no");
            session.setPassword(password);
            session.setTimeout(20000);
            if (!session.isConnected()) {
                session.connect();
            }
            final Channel channel = session.openChannel("sftp");
            channel.connect();
            final ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(remoteDir);
            sftpChannel.put(localFileName, filename, ChannelSftp.APPEND);
            sftpChannel.exit();
            session.disconnect();
            return true;
        } catch (final Exception e) {
            LOG.error("Unable to put file due to exception" + e);
            return false;
        }
        
    }
    
    /**
     * @param host ""
     * @param username ""
     * @param password ""
     * @param remoteDir ""
     * @return getfile status
     */
    public static boolean getFile(final String host, final String username, final String password,
            final String remoteDir) {
        final JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(username, host, 22);
            session.setConfig(STRICT_HOST_KEY_CHECKING, "no");
            session.setTimeout(20000);
            if (!session.isConnected()) {
                session.connect();
            }
            session.setPassword(password);
            session.connect();
            final Channel channel = session.openChannel("sftp");
            channel.connect();
            final ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(remoteDir);
            
            sftpChannel.exit();
            session.disconnect();
            return true;
        } catch (final Exception e) {
            LOG.error("Unable to get file due to exception" + e);
            return false;
        }
    }
    
}
