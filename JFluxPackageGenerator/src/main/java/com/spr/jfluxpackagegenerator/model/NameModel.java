package com.spr.jfluxpackagegenerator.model;

/**
 * The interface is deisgned to provide ids of layout control block which describes a name model,
 * like AuthorName or ContactPersonName.
 * 
 * @author Alexey Dergalev
 */
public interface NameModel {
    
    /**
     * Gets the value of the prefix property id.
     * 
     * @return possible object is {@link String }
     */
    String getPrefixId();
    
    /**
     * Gets the value of the first give name property id.
     * 
     * @return possible object is {@link String }
     */
    String getGivenName1Id();
    
    /**
     * Gets the value of the second give name property id.
     * 
     * @return possible object is {@link String }
     */
    String getGivenName2Id();
    
    /**
     * Gets the value of the third give name property id.
     * 
     * @return possible object is {@link String }
     */
    String getGivenName3Id();
    
    /**
     * Gets the value of the particle property id.
     * 
     * @return possible object is {@link String }
     */
    String getParticleId();
    
    /**
     * Gets the value of the familyName property id.
     * 
     * @return possible object is {@link String }
     */
    String getFamilyNameId();
    
    /**
     * Gets the value of the suffix property id.
     * 
     * @return possible object is {@link String }
     */
    String getSuffixId();
    
    /**
     * Gets the value of the displayOrder property id.
     * 
     * @return possible object is {@link String }
     */
    String getDisplayOrderId();
}
