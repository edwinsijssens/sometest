package com.spr.jfluxpackagegenerator.model.enums;

/**
 * Suffix enum.
 * 
 * @author Alexey Dergalev
 */
public enum Suffix {
    
    /** . */
    JR("Jr"),
    /** . */
    JR_DOT("Jr."),
    /** . */
    SR("Sr"),
    /** . */
    SR_DOT("Sr."),
    /** . */
    ROMAN_SECOND("II"),
    /** . */
    ROMAN_THIRD("III"),
    /** . */
    ROMAN_FOURTH("IV"),
    /** . */
    ROMAN_FIFTH("V"),
    /** . */
    ROMAN_SECOND_DOT("II."),
    /** . */
    ROMAN_THIRD_DOT("III."),
    /** . */
    ROMAN_FOURTH_DOT("IV."),
    /** . */
    ROMAN_FIFTH_DOT("V."),
    /** . */
    SECOND_D("2d"),
    /** . */
    SECOND_ND("2nd"),
    /** . */
    THIRD_D("3d"),
    /** . */
    THIRD_RD("3rd"),
    /** . */
    FOURTH("4th"),
    /** . */
    FIFTH("5th");
    
    private final String value;
    
    Suffix(final String name) {
        value = name;
    }
    
    @Override
    public String toString() {
        return value;
    }
    
}
