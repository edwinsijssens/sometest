/**
 * 
 */
package com.spr.jfluxpackagegenerator.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class FTPServer {
    
    
    @XmlID
    private String id;
    
    private String hostname;
    
    private String port;
    
    private String username;
    
    private String password;
    
    public String getId() {
        return id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setPort(final String port) {
        this.port = port;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
}
