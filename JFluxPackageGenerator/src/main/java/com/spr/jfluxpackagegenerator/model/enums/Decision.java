package com.spr.jfluxpackagegenerator.model.enums;

/**
 * Enum for Boolean and Deliverables attribute values.
 * 
 * @author Alexey Dergalev
 */
public enum Decision {
    
    Yes, IfApplies, No;
    
    private static final Decision[] BOOLEANS = new Decision[] { Yes, No };
    
    /**
     * Get boolean attribute values "Yes" and "No"
     * 
     * @return array, never null
     */
    public static Decision[] getBooleanValues() {
        return BOOLEANS;
    }
}
