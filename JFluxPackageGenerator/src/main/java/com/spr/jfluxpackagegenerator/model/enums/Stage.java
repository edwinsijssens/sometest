package com.spr.jfluxpackagegenerator.model.enums;

/**
 * Stage enum.
 * 
 * @author Alexey Dergalev
 */
public enum Stage {
    
    S100EXTERNAL("S100 - Article package received from co-publisher"),
    
    S200EXTERNAL("S200 - Article package received from JWF"),
    
    S500EXTERNAL("S500 - Article package received from co-publisher");
    
    private final String stageDescription;
    
    Stage(final String name) {
        stageDescription = name;
    }
    
    @Override
    public String toString() {
        return stageDescription;
    }
    
}
