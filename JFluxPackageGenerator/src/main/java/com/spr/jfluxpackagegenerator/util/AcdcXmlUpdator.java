package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.spr.jfluxpackagegenerator.model.files.FilesPath;

/**
 * @author cjn6673
 */
public class AcdcXmlUpdator {
    
    
    /**
     * 
     */
    private static final Logger LOG = Logger.getLogger(AcdcXmlUpdator.class);
    
    private final RawManuscriptZipCreator rmzipCreator = new RawManuscriptZipCreator();
    
    private final InputStream input = null;
    
    private static final String ARTICLE_ID = "ArticleID";
    
    /**
     * @param articleID ""
     * @param jid ""
     * @param map ""
     * @return articlexmlFile
     */
    public String getArticlexXML(final String articleID, final String jid,
            final Map<String, String> map) {
        
        final String articleUpload = FilesPath.artFolderPath;
        
        final String article = FilesPath.articleXML;
        
        final File axml = new File(article);
        
        final File artXMLCopy =
                new File(articleUpload + jid + "_" + 2017 + "_" + articleID + "_Article" + ".xml");
        
        try {
            FileUtils.copyFile(axml, artXMLCopy);
            
            try {
                updateArticleXML(artXMLCopy.toString(), map);
                
            } catch (final Exception e) {
                
                LOG.error("Exception while updating file inside getArticlexXML() is"
                        + e.getMessage());
                
            }
        } catch (final IOException e) {
            
            LOG.error("Exception while copying file inside getArticlexXML() is" + e.getMessage());
        }
        return artXMLCopy.toString();
        
    }
    
    /**
     * @param journalId ""
     * @param artId ""
     * @param map ""
     * @return Jobsheet file
     */
    public String getJobSheet(final String journalId, final String artId,
            final Map<String, String> map) {
        
        final String jsUpload = FilesPath.jsFolder;
        
        final String jobsheet = FilesPath.jobSheet;
        
        final File jsxml = new File(jobsheet);
        
        final File jsxmlcopy = new File(
                jsUpload + journalId + "_" + "2017" + "_" + artId + "_JobSheet_200" + ".xml");
        try {
            FileUtils.copyFile(jsxml, jsxmlcopy);
            
            try {
                updateJobsheet(jsxmlcopy.toString(), map);
                
            } catch (final Exception e) {
                
                LOG.error("Exception while updating Jobsheet inside getJobSheet() is"
                        + e.getMessage());
            }
        } catch (final IOException e) {
            
            LOG.error("Exception while copying Jobsheet inside getJobSheet() is" + e.getMessage());
        }
        return jsxmlcopy.toString();
        
    }
    
    /**
     * @param artXMLCopy ""
     * @param map ""
     * @throws Exception
     */
    public void updateArticleXML(final String artXMLCopy, final Map<String, String> map)
            throws Exception {
        
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        final Document doc = docBuilder.parse(artXMLCopy);
        final String articleId = map.get(ARTICLE_ID);
        
        final Node art = doc.getElementsByTagName(ARTICLE_ID).item(0);
        art.setTextContent(articleId);
        
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        final DOMSource source = new DOMSource(doc);
        final StreamResult result = new StreamResult(new File(artXMLCopy));
        transformer.transform(source, result);
        
    }
    
    /**
     * @param fileName ""
     * @param map ""
     * @throws Exception
     */
    public void updateJobsheet(final String fileName, final Map<String, String> map)
            throws Exception {
        
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        final Document doc = docBuilder.parse(fileName);
        final String jrnlID = map.get("JournalID");
        final String artID = map.get(ARTICLE_ID);
        final String artlang = map.get("ArticleTitle");
        final String esmVal = map.get("ContainsESM");
        
        final XPath xPath = XPathFactory.newInstance().newXPath();
        final Node jrnlid = (Node) xPath.compile("/JobSheet/ArticleJobSheet/JournalInfo/JournalID")
                .evaluate(doc, XPathConstants.NODE);
        jrnlid.setTextContent(jrnlID);
        final Node articleid =
                (Node) xPath.compile("/JobSheet/ArticleJobSheet/ArticleInfo/ArticleID")
                        .evaluate(doc, XPathConstants.NODE);
        articleid.setTextContent(artID);
        final Node artLang =
                (Node) xPath.compile("/JobSheet/ArticleJobSheet/ArticleInfo/ArticleTitle")
                        .evaluate(doc, XPathConstants.NODE);
        final NamedNodeMap attr = artLang.getAttributes();
        final Node nodeAttr = attr.getNamedItem("Language");
        nodeAttr.setTextContent(artlang);
        
        final String cpeditCat = map.get("DeliverablesForDiscreteObjects");
        
        int count = 0;
        try {
            count = Integer.parseInt(cpeditCat);
        } catch (final NumberFormatException e) {
            count = 0;
        }
        
        final Node cpedit = (Node) xPath
                .compile("/JobSheet/ArticleJobSheet/ProductionInfo/WorkflowInfo/"
                        + "Supplier/FullServiceVendor/Deliverables/DeliverablesForDiscreteObjects")
                .evaluate(doc, XPathConstants.NODE);
        final NamedNodeMap attr2 = cpedit.getAttributes();
        final Node nodeAttr2 = attr2.getNamedItem("DeltaPDF");
        
        if (count > 1) {
            nodeAttr2.setTextContent("Yes");
        } else {
            nodeAttr2.setTextContent("No");
            
        }
        final Node esm = (Node) xPath.compile("/JobSheet/ArticleJobSheet/ArticleInfo").evaluate(doc,
                XPathConstants.NODE);
        final NamedNodeMap attr3 = esm.getAttributes();
        final Node nodeAttr3 = attr3.getNamedItem("ContainsESM");
        nodeAttr3.setTextContent(esmVal);
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        final DOMSource source = new DOMSource(doc);
        final StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
        
    }
    
    public String rawManuUpload(final String filename, final String type,
            final List<String> suppFiles) throws Exception {
        File zipName = null;
        String date = null;
        try {
            
            final String rawZip = FilesPath.rawzipFolder;
            date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SSS").format(new Date());
            
            zipName = new File(rawZip + filename + "_viv_" + date + ".zip");
            
            final FileOutputStream bos = new FileOutputStream(zipName);
            final ZipOutputStream zos = new ZipOutputStream(bos);
            
            final String docJFile = FilesPath.doc;
            final String txtFile = FilesPath.tex;
            final String docxJFile = FilesPath.docx;
            
            switch (type) {
                
                case "Doc":
                    rmzipCreator.addToZip(docJFile, zos);
                    if (suppFiles != null) {
                        for (int i = 0; i < suppFiles.size(); i++) {
                            
                            rmzipCreator.addToZip(suppFiles.get(i), zos);
                        }
                        
                    }
                    break;
                
                case "Docx":
                    rmzipCreator.addToZip(docxJFile, zos);
                    if (suppFiles != null) {
                        for (int i = 0; i < suppFiles.size(); i++) {
                            
                            rmzipCreator.addToZip(suppFiles.get(i), zos);
                            
                        }
                        
                    }
                    break;
                
                case "Tex":
                    rmzipCreator.addToZip(txtFile, zos);
                    if (suppFiles != null) {
                        for (int i = 0; i < suppFiles.size(); i++) {
                            
                            rmzipCreator.addToZip(suppFiles.get(i), zos);
                            
                        }
                        
                    }
                    break;
                
                default:
                    break;
                
            }
            
            zos.close();
            
        } catch (final IOException ex) {
            LOG.error("IOException in rawManuUpload() while adding types in zip is "
                    + ex.getMessage());
            
        } catch (final Exception exe) {
            LOG.error("Exception in rawManuUpload() while adding types in zip is "
                    + exe.getMessage());
            
        } finally {
            closeFileStream(input);
        }
        return zipName.toString();
    }
    
    /**
     * @param in ""
     */
    protected void closeFileStream(final InputStream in) {
        try {
            input.close();
        } catch (final Exception e) {
            LOG.error("Exception in closeFileStream() while closing file stream is "
                    + e.getMessage());
        }
    }
    
    /**
     * @param articleID ""
     * @param jrnlId ""
     * @param map ""
     * @param type ""
     * @param suppFiles ""
     * @return zipFile
     */
    public File createfinalZip(final String articleID, final String jrnlId,
            final Map<String, String> map, final String type, final List<String> suppFiles) {
        FileOutputStream fbos = null;
        final String finalZip = FilesPath.finalzipFolder;
        final String date = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss_SSS").format(new Date());
        final File finalzipName =
                new File(finalZip + rmzipCreator.createFinalZipFileName(jrnlId, articleID, date));
        
        try {
            fbos = new FileOutputStream(finalzipName);
        } catch (final FileNotFoundException e) {
            LOG.error("FileNotFoundException in FileOutputStream is " + e.getMessage());
        }
        final ZipOutputStream fzos = new ZipOutputStream(fbos);
        
        try {
            
            rmzipCreator.addToZip(getArticlexXML(articleID, jrnlId, map), fzos);
            rmzipCreator.addToZip(getJobSheet(jrnlId, articleID, map), fzos);
            rmzipCreator.addToZip(rawManuUpload(jrnlId, type, suppFiles), fzos);
            suppFiles.clear();
            
        } catch (final Exception e1) {
            LOG.error("Exception in adding files to zip is " + e1.getMessage());
        }
        try {
            fzos.close();
        } catch (final IOException e) {
            LOG.error("IOException in closing zip output stream is " + e.getMessage());
        }
        
        return finalzipName;
    }
    
}
