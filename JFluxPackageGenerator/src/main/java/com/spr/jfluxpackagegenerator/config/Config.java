/**
 * 
 */
package com.spr.jfluxpackagegenerator.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://spr.com/jfluxpackagegenerator/config")
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
    
    private String defaultEnvId;
    
    private String destinationDir;
    
    @XmlElementWrapper
    @XmlElement(name = "ftpServer")
    protected List<FTPServer> ftpServers = new ArrayList<FTPServer>();
    
    public Map<String, Environment> envsByName = new LinkedHashMap<String, Environment>();
    
    public String getDefaultEnvId() {
        return defaultEnvId;
    }
    
    public void setDefaultEnvId(final String defaultEnvId) {
        this.defaultEnvId = defaultEnvId;
    }
    
    public List<FTPServer> getFtpServers() {
        return ftpServers;
    }
    
    public Map<String, Environment> getEnvsByName() {
        return envsByName;
    }
    
    public String getDestinationDir() {
        return destinationDir;
    }
}
