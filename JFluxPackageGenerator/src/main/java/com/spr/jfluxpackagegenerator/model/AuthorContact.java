package com.spr.jfluxpackagegenerator.model;

import com.spr.jfluxpackagegenerator.ui.layout.id.AuthorGroupLayoutIds;

public class AuthorContact implements ContactModel {
    
    
    @Override
    public AddressModel getAddressModel() {
        // AuthorContact doesn't have appropriate fields on layout
        return new AddressModel() {
            
            
            @Override
            public String getStreetId() {
                
                return "";
            }
            
            @Override
            public String getPostboxId() {
                
                return "";
            }
            
            @Override
            public String getPostcodeId() {
                
                return "";
            }
            
            @Override
            public String getCityId() {
                
                return "";
            }
            
            @Override
            public String getStateId() {
                
                return "";
            }
            
            @Override
            public String getCountryId() {
                
                return "";
            }
            
            @Override
            public String getCountryCodeId() {
                
                return "";
            }
            
        };
    }
    
    @Override
    public String getOrgDivisionId() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_ODIV.toString();
    }
    
    @Override
    public String getOrgNameId() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_ONM.toString();
    }
    
    @Override
    public String getPhone1Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_PH1.toString();
    }
    
    @Override
    public String getFax1Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_FX1.toString();
    }
    
    @Override
    public String getEmail1Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_EM1.toString();
    }
    
    @Override
    public String getURL1Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_URL1.toString();
    }
    
    @Override
    public String getPhone2Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_PH2.toString();
    }
    
    @Override
    public String getFax2Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_FX2.toString();
    }
    
    @Override
    public String getEmail2Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_EM2.toString();
    }
    
    @Override
    public String getURL2Id() {
        
        return AuthorGroupLayoutIds.TXT_AU1_CON1_URL2.toString();
    }
    
}
