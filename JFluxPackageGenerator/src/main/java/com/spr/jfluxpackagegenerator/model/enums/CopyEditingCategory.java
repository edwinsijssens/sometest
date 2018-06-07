package com.spr.jfluxpackagegenerator.model.enums;

public enum CopyEditingCategory {
    zero("0"), one("1"), two("2"), three("3");
    
    private final String value;
    
    CopyEditingCategory(final String name) {
        value = name;
    }
    
    @Override
    public String toString() {
        return value;
    }
}
