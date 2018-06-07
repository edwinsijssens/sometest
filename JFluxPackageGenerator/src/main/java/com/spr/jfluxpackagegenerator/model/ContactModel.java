package com.spr.jfluxpackagegenerator.model;

/**
 * The interface is designed to provide ids of layout control block which describes a contact model,
 * like Contact elements. It includes an address model.
 * 
 * @author Alexey Dergalev
 */
public interface ContactModel {
    
    /**
     * Gets address model.
     * 
     * @return AddressModel
     */
    AddressModel getAddressModel();
    
    /**
     * Gets the value of the org division property id.
     * 
     * @return possible object is {@link String }
     */
    String getOrgDivisionId();
    
    /**
     * Gets the value of the org name property id.
     * 
     * @return possible object is {@link String }
     */
    String getOrgNameId();
    
    /**
     * Gets the value of the first phone property id.
     * 
     * @return possible object is {@link String }
     */
    String getPhone1Id();
    
    /**
     * Gets the value of the first fax property id.
     * 
     * @return possible object is {@link String }
     */
    String getFax1Id();
    
    /**
     * Gets the value of the first email property id.
     * 
     * @return possible object is {@link String }
     */
    String getEmail1Id();
    
    /**
     * Gets the value of the first URL property id.
     * 
     * @return possible object is {@link String }
     */
    String getURL1Id();
    
    /**
     * Gets the value of the second phone property id.
     * 
     * @return possible object is {@link String }
     */
    String getPhone2Id();
    
    /**
     * Gets the value of the second fax property id.
     * 
     * @return possible object is {@link String }
     */
    String getFax2Id();
    
    /**
     * Gets the value of the second email property id.
     * 
     * @return possible object is {@link String }
     */
    String getEmail2Id();
    
    /**
     * Gets the value of the second URL property id.
     * 
     * @return possible object is {@link String }
     */
    String getURL2Id();
    
}
