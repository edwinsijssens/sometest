package com.spr.jfluxpackagegenerator.model.data;

import java.util.Map;

public class DataStorage {
    
    private final Map<String, String> dataValues;
    
    public DataStorage(final Map<String, String> values) {
        dataValues = values;
    }
    
    public Map<String, String> getDataValues() {
        return dataValues;
    }
}
