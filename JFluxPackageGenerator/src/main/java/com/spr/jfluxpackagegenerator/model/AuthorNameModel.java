package com.spr.jfluxpackagegenerator.model;

import com.spr.jfluxpackagegenerator.ui.layout.id.AuthorGroupLayoutIds;

/**
 * The class is designed to provide information about control ids for AuthorName element creation.
 * 
 * @author Alexey Dergalev
 */
public class AuthorNameModel implements NameModel {
    
    @Override
    public String getPrefixId() {
        
        return AuthorGroupLayoutIds.TXT_AUTH1_PRE.toString();
    }
    
    @Override
    public String getGivenName1Id() {
        
        return AuthorGroupLayoutIds.TXT_AUTH1_GNM1.toString();
    }
    
    @Override
    public String getGivenName2Id() {
        
        return AuthorGroupLayoutIds.TXT_AUTH1_GNM2.toString();
    }
    
    @Override
    public String getGivenName3Id() {
        
        return AuthorGroupLayoutIds.TXT_AUTH1_GNM3.toString();
    }
    
    @Override
    public String getParticleId() {
        
        return AuthorGroupLayoutIds.TXT_AUTH1_PAR.toString();
    }
    
    @Override
    public String getFamilyNameId() {
        
        return AuthorGroupLayoutIds.TXT_AUTH1_FNM.toString();
    }
    
    @Override
    public String getSuffixId() {
        
        return AuthorGroupLayoutIds.LST_AUTH1_SUF.toString();
    }
    
    @Override
    public String getDisplayOrderId() {
        
        return "";
    }
    
}
