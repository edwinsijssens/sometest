/**
 * 
 */
package com.spr.jfluxpackagegenerator.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;

/**
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Environment {
    
    @XmlIDREF
    @XmlElement(name = "ftpServerId")
    private FTPServer ftpServer;
    
    private final Map<String, String> dirsByVendor = new LinkedHashMap<String, String>();
    
    public FTPServer getFtpServer() {
        return ftpServer;
    }
    
    public void setFtpServer(final FTPServer ftpServer) {
        this.ftpServer = ftpServer;
    }
    
    public Map<String, String> getDirsByVendor() {
        return dirsByVendor;
    }
    
}
