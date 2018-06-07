package com.spr.jfluxpackagegenerator.util;

public class CorrectionsheetData {
    
    private final String journalID;
    
    private final String articleDOI;
    
    public CorrectionsheetData(final String jouID, final String artDOI, final String arFPage,
                               final String artLPage, final String errFPage,
                               final String errLPage) {
        journalID = jouID;
        articleDOI = artDOI;
        artFirstPage = arFPage;
        artiLastPage = artLPage;
        errFirstPage = errFPage;
        errLastPage = errLPage;
    }
    
    public String getJournalID() {
        return journalID;
    }
    
    public String getArticleDOI() {
        return articleDOI;
    }
    
    public String getArtFirstPage() {
        return artFirstPage;
    }
    
    public String getArtiLastPage() {
        return artiLastPage;
    }
    
    public String getErrFirstPage() {
        return errFirstPage;
    }
    
    public String getErrLastPage() {
        return errLastPage;
    }
    
    private final String artFirstPage;
    
    private final String artiLastPage;
    
    private final String errFirstPage;
    
    private final String errLastPage;
}
