package com.spr.jfluxpackagegenerator.model.data;

import java.util.Map;

public class ArticleDataStorage extends DataStorage {
    
    private int sequenceNumber;
    
    public ArticleDataStorage(final Map<String, String> values, final int articleSequenceNumber) {
        super(values);
        sequenceNumber = articleSequenceNumber;
    }
    
    public int getSequenceNumber() {
        return sequenceNumber;
    }
    
}
