package com.spr.jfluxpackagegenerator.model;

import com.spr.jfluxpackagegenerator.ui.layout.id.ProductionInfoLayoutIds;

/**
 * The class includes ids for "ExternalSupplier\ContactPerson\Contact" element.
 * 
 * @author Alexey Dergalev
 */
public class ExternalPublisherContactPersonContact implements ContactModel {
    
    @Override
    public AddressModel getAddressModel() {
        return new AddressModel() {
            
            @Override
            public String getStreetId() {
                
                return ProductionInfoLayoutIds.TXT_CONP_STR.toString();
            }
            
            @Override
            public String getPostboxId() {
                return ProductionInfoLayoutIds.TXT_CONP_PBX.toString();
            }
            
            @Override
            public String getPostcodeId() {
                return ProductionInfoLayoutIds.TXT_CONP_PCD.toString();
            }
            
            @Override
            public String getCityId() {
                return ProductionInfoLayoutIds.TXT_CONP_CTY.toString();
            }
            
            @Override
            public String getStateId() {
                return ProductionInfoLayoutIds.TXT_CONP_STA.toString();
            }
            
            @Override
            public String getCountryId() {
                return ProductionInfoLayoutIds.TXT_CONP_CNT.toString();
            }
            
            @Override
            public String getCountryCodeId() {
                return ProductionInfoLayoutIds.TXT_CONP_CNT_CODE.toString();
            }
            
        };
    }
    
    @Override
    public String getOrgDivisionId() {
        return ProductionInfoLayoutIds.TXT_CONP_ODIV.toString();
    }
    
    @Override
    public String getOrgNameId() {
        return ProductionInfoLayoutIds.TXT_CONP_ONM.toString();
    }
    
    @Override
    public String getPhone1Id() {
        return ProductionInfoLayoutIds.TXT_CONP_PH1.toString();
    }
    
    @Override
    public String getFax1Id() {
        return ProductionInfoLayoutIds.TXT_CONP_FX1.toString();
    }
    
    @Override
    public String getEmail1Id() {
        return ProductionInfoLayoutIds.TXT_CONP_EM1.toString();
    }
    
    @Override
    public String getURL1Id() {
        return ProductionInfoLayoutIds.TXT_CONP_URL1.toString();
    }
    
    @Override
    public String getPhone2Id() {
        return ProductionInfoLayoutIds.TXT_CONP_PH2.toString();
    }
    
    @Override
    public String getFax2Id() {
        return ProductionInfoLayoutIds.TXT_CONP_FX2.toString();
    }
    
    @Override
    public String getEmail2Id() {
        return ProductionInfoLayoutIds.TXT_CONP_EM2.toString();
    }
    
    @Override
    public String getURL2Id() {
        return ProductionInfoLayoutIds.TXT_CONP_URL2.toString();
    }
    
}
