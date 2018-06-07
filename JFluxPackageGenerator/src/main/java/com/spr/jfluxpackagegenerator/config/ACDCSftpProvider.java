package com.spr.jfluxpackagegenerator.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cjn6673
 */
public class ACDCSftpProvider {
    
    
    /**
     * @param environment ""
     * @return ftpmap
     */
    public Map<String, String> getEnvironmentProperty(final String environment) {
        final Map<String, String> ftpMap = new HashMap<>();
        switch (environment) {
            case "Integration":
                ftpMap.put("host", "10.9.3.14");
                ftpMap.put("user", "cdci");
                ftpMap.put("pass", "welk0m");
                ftpMap.put("dir", "/u02/acdc/work/ImportDeliveryArchive/ImportBase/jwf/in");
                
                break;
            case "Acceptance":
                ftpMap.put("host", "10.9.2.54");
                ftpMap.put("user", "cdca");
                ftpMap.put("pass", "cdca07");
                ftpMap.put("dir", "/u02/acdc/work/ImportDeliveryArchive/ImportBase/jwf/in");
                
                break;
        }
        
        return ftpMap;
        
    }
}
