package com.spr.jfluxpackagegenerator.util;

public class ArticleData {
    
    private final String articleDOI;
    
    private final String articleType;
    
    private final String outputMedium;
    
    private final String articleRelDOI;
    
    public ArticleData(final String artDOI, final String artType, final String oMedium,
                       final String artRelDOI) {
        articleDOI = artDOI;
        articleType = artType;
        outputMedium = oMedium;
        articleRelDOI = artRelDOI;
    }
    
    public String getArticleDOI() {
        return articleDOI;
    }
    
    public String getArticleType() {
        return articleType;
    }
    
    public String getOutputMedium() {
        return outputMedium;
    }
    
    public String getArticleRelatedDOI() {
        return articleRelDOI;
    }
}
