package com.spr.jfluxpackagegenerator.model;

import com.spr.jfluxpackagegenerator.ui.layout.id.ProductionInfoLayoutIds;

/**
 * The class includes ids for "ExternalSupplier\ContactPerson\ContactPersonName" element.
 * 
 * @author Alexey Dergalev
 */
public class ExternalPublisherContactPersonNameModel implements NameModel {
    
    @Override
    public String getPrefixId() {
        return ProductionInfoLayoutIds.TXT_CONP_PRE.toString();
    }
    
    @Override
    public String getGivenName1Id() {
        return ProductionInfoLayoutIds.TXT_CONP_GNM1.toString();
    }
    
    @Override
    public String getParticleId() {
        return ProductionInfoLayoutIds.TXT_CONP_PAR.toString();
    }
    
    @Override
    public String getFamilyNameId() {
        return ProductionInfoLayoutIds.TXT_CONP_FNM.toString();
    }
    
    @Override
    public String getSuffixId() {
        return ProductionInfoLayoutIds.LST_CONP_SUF.toString();
    }
    
    @Override
    public String getDisplayOrderId() {
        return null;
    }
    
    @Override
    public String getGivenName2Id() {
        return ProductionInfoLayoutIds.TXT_CONP_GNM2.toString();
    }
    
    @Override
    public String getGivenName3Id() {
        return ProductionInfoLayoutIds.TXT_CONP_GNM3.toString();
    }
    
}
