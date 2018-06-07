package com.spr.jfluxpackagegenerator.model;

/**
 * The interface is designed to provide ids of layout control block which describes a address model,
 * like Contact elements contain.
 * 
 * @author Alexey Dergalev
 */
public interface AddressModel {
    
    /**
     * Gets the value of the street property id.
     * 
     * @return possible object is {@link String }
     */
    String getStreetId();
    
    /**
     * Gets the value of the postbox property id.
     * 
     * @return possible object is {@link String }
     */
    String getPostboxId();
    
    /**
     * Gets the value of the postcode property id.
     * 
     * @return possible object is {@link String }
     */
    String getPostcodeId();
    
    /**
     * Gets the value of the city property id.
     * 
     * @return possible object is {@link String }
     */
    String getCityId();
    
    /**
     * Gets the value of the state property id.
     * 
     * @return possible object is {@link String }
     */
    String getStateId();
    
    /**
     * Gets the value of the Country property id.
     * 
     * @return possible object is {@link String }
     */
    String getCountryId();
    
    /**
     * Gets the value of the Country code property id.
     * 
     * @return possible object is {@link String }
     */
    String getCountryCodeId();
    
}
