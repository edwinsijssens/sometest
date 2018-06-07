package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class CorrectionsheetCreator {
    
    
    private final File corresheetFile;
    
    private Document corr;
    
    private final Document jobsheet;
    
    private static final String ARTICLE = "Article";
    
    public CorrectionsheetCreator(final File corrXMLFile, final Document js) {
        corresheetFile = corrXMLFile;
        jobsheet = js;
    }
    
    public void create() throws Exception {
        
        updateCorrSheetMetadata();
        serializeArticleXMl();
    }
    
    private void serializeArticleXMl() throws Exception {
        final OutputStream ous = new FileOutputStream(corresheetFile);
        DOMHelper.writeDocument(getCorrSheetDocument(), ous);
        ous.flush();
        StreamHelper.close(ous);
        
    }
    
    private Document getCorrSheetDocument() throws Exception {
        if (corr == null) {
            corr = DOMHelper.parse(corresheetFile);
        }
        return corr;
    }
    
    private void updateCorrSheetMetadata() throws Exception {
        final Document docCorrSheetNode = getCorrSheetDocument();
        final CorrectionsheetData corrdata = getCorrSheetDataFromJobSheet();
        
        String strArticleDOI = "";
        strArticleDOI = corrdata.getArticleDOI();
        if (strArticleDOI.contains("/")) {
            strArticleDOI =
                    strArticleDOI.substring(strArticleDOI.indexOf('/') + 1, strArticleDOI.length());
        }
        
        updateNodeAttribute(docCorrSheetNode, ARTICLE, "ArticleDOI", strArticleDOI);
        
        String journalID = "";
        journalID = corrdata.getJournalID();
        updateNodeAttribute(docCorrSheetNode, "Journal", "JournalID", journalID);
        
        String artFirstPage = "";
        artFirstPage = corrdata.getArtFirstPage();
        updateNodeAttribute(docCorrSheetNode, ARTICLE, "ArticleFirstPage", artFirstPage);
        
        String artLastPage = "";
        artLastPage = corrdata.getArtiLastPage();
        updateNodeAttribute(docCorrSheetNode, ARTICLE, "ArticleLastPage", artLastPage);
        
        String errArticleFirstPage = "";
        errArticleFirstPage = corrdata.getErrFirstPage();
        updateNodeAttribute(docCorrSheetNode, ARTICLE, "Erroneous.ArticleFirstPage",
                errArticleFirstPage);
        
        String errArticleLastPage = "";
        errArticleLastPage = corrdata.getErrLastPage();
        updateNodeAttribute(docCorrSheetNode, ARTICLE, "Erroneous.ArticleLastPage",
                errArticleLastPage);
    }
    
    private CorrectionsheetData getCorrSheetDataFromJobSheet() throws Exception {
        
        final String journalID = DOMHelper.selectText(jobsheet, "//JournalInfo/JournalID");
        final String articleDOI = DOMHelper.selectText(jobsheet, "//ArticleInfo/ArticleDOI");
        final String articleFirstPage =
                DOMHelper.selectText(jobsheet, "//ArticleInfo/ArticleFirstPage");
        final String articleLastPage =
                DOMHelper.selectText(jobsheet, "//ArticleInfo/ArticleLastPage");
        final String erroneousArticleFirstPage =
                DOMHelper.selectText(jobsheet, "//ArticleInfo/ErroneousArticleFirstPage");
        final String erroneousArticleLastPage =
                DOMHelper.selectText(jobsheet, "//ArticleInfo/ErroneousArticleLastPage");
        return new CorrectionsheetData(journalID, articleDOI, articleFirstPage, articleLastPage,
                erroneousArticleFirstPage, erroneousArticleLastPage);
    }
    
    public static final void updateNodeAttribute(final Document xml, final String nodeName,
            final String attName, final String attValue) throws Exception {
        
        // Get the XML element by tag name directly
        final Node artNode = xml.getElementsByTagName(nodeName).item(0);
        
        // update staff attribute
        final NamedNodeMap attr = artNode.getAttributes();
        final Node nodeAttr = attr.getNamedItem(attName);
        nodeAttr.setTextContent(attValue);
        
        xml.normalize();
        
    }
}
