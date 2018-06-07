package com.spr.jfluxpackagegenerator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author cjn6673
 */
public class ArticleXMLCreator {
    
    
    private static final Logger LOG = Logger.getLogger(ArticleXMLCreator.class);
    
    private static String fileArticleXML;
    
    private static File fileOutPutArticleXML;
    
    private final File articleXmlFile;
    
    private final Document jobsheet;
    
    private final String articleName;
    
    private final String articleFileRef;
    
    private Document articleDoc;
    
    private static final String BODY_REF = "BodyRef";
    
    private static final String FILE_REF = "FileRef";
    
    private static final String XPATH_DISCREET_ISSUE = "//DiscreteIssueObjectInfo[@ID=\"";
    
    private static final String UTF_8 = "UTF-8";
    
    private static final String MEDIA_OBJECT = "MediaObject";
    
    /**
     * @param artXmlFile ""
     * @param artName ""
     * @param artFileRef ""
     * @param js ""
     */
    public ArticleXMLCreator(final File artXmlFile, final String artName, final String artFileRef,
                             final Document js) {
        articleXmlFile = artXmlFile;
        jobsheet = js;
        articleName = artName;
        articleFileRef = artFileRef;
    }
    
    /**
     * @throws Exception
     */
    public void create() throws Exception {
        copyInfoFromJobsheetIntoArticleXML();
        updateArticleMetadata();
        serializeArticleXMl();
        
    }
    
    private void serializeArticleXMl() throws Exception {
        final OutputStream ous = new FileOutputStream(articleXmlFile);
        DOMHelper.writeDocument(getArticleDocument(), ous);
        ous.flush();
        StreamHelper.close(ous);
        
    }
    
    private void updateArticleMetadata() throws Exception {
        final Document docArticleNode = getArticleDocument();
        final ArticleData artdata = getArticleDataFromJobSheet();
        
        String strArticleID = "";
        strArticleID = artdata.getArticleDOI();
        if (strArticleID.contains("/")) {
            strArticleID =
                    strArticleID.substring(strArticleID.indexOf('/') + 1, strArticleID.length());
        }
        
        updateNodeAttribute(docArticleNode, "Article", "ID", strArticleID);
        
        final String articleNamePrefix =
                articleName.substring(0, articleName.lastIndexOf("Article.xml"));
        // Updating BodyRef value
        final String onlinePDFName = articleNamePrefix + "OnlinePDF.pdf";
        updateNodeAttribute(docArticleNode, BODY_REF, FILE_REF, onlinePDFName);
        
        final String texDeliverableAttributeValue =
                DOMHelper.selectText(jobsheet, "//DeliverablesForDiscreteObjects/@TEX");
        if ("Yes".equalsIgnoreCase(texDeliverableAttributeValue)) {
            final String texZipName = articleNamePrefix + "TEX.zip";
            updateNodeAttribute(docArticleNode, BODY_REF, "TargetType", "TEX", FILE_REF,
                    texZipName);
        } else {
            removeNodeByAttributeValue(docArticleNode, BODY_REF, "TargetType", "TEX");
        }
        
        updateNodeAttribute(docArticleNode, "Article", "OutputMedium", artdata.getOutputMedium());
        
        // Check if type is Erratum
        if (artdata.getArticleType().equalsIgnoreCase("Erratum")) {
            // updating ExternalRef- RefSource
            final String sReleatedDOIURL = artdata.getArticleRelatedDOI();
            setNodeValue(docArticleNode, "RefSource", sReleatedDOIURL);
            
            final NodeList abslist = docArticleNode.getElementsByTagName("Abstract");
            final Element eleNodeAbs = (Element) abslist.item(0);
            eleNodeAbs.setAttribute("OutputMedium", "None");
            
            // updating RefTarget - Address
            updateNodeAttribute(docArticleNode, "RefTarget", "Address", sReleatedDOIURL);
            
        } else {
            removeNodeByAttributeValue(docArticleNode, "ArticleNote", "Type", "Misc");
        }
        final NodeList abslist = docArticleNode.getElementsByTagName("Abstract");
        final Element eleNodeAbs = (Element) abslist.item(0);
        // Remove Heading tag
        if (eleNodeAbs.getAttribute("OutputMedium").equalsIgnoreCase("None")
                && artdata.getArticleType().equalsIgnoreCase("Erratum")
                || artdata.getArticleType().equalsIgnoreCase("Correction")) {
            
            NodeList heading = docArticleNode.getElementsByTagName("Heading");
            if (heading.item(0) != null) {
                eleNodeAbs.removeChild(heading.item(0));
                
            }
            
        }
        
    }
    
    private ArticleData getArticleDataFromJobSheet() throws Exception {
        final boolean isIssueJobSheet = isIssueJobSheet();
        
        final String artId =
                DOMHelper.selectText(jobsheet, "//ContentFiles/File/AplusplusRootFile[@FileRef=\""
                        + articleFileRef + "\"]/../@DiscreteObjectID");
        
        final String articleDOI = isIssueJobSheet
                ? DOMHelper.selectText(jobsheet,
                        XPATH_DISCREET_ISSUE + artId + "\"]/ArticleInfo/ArticleDOI")
                : DOMHelper.selectText(jobsheet, "//ArticleInfo/ArticleDOI");
        final String articleType = isIssueJobSheet
                ? DOMHelper.selectText(jobsheet,
                        XPATH_DISCREET_ISSUE + artId + "\"]/ArticleInfo/@ArticleType")
                : DOMHelper.selectText(jobsheet, "//ArticleInfo/@ArticleType");
        final String outputMedium = isIssueJobSheet
                ? DOMHelper.selectText(jobsheet,
                        XPATH_DISCREET_ISSUE + artId + "\"]/ArticleInfo/@OutputMedium")
                : DOMHelper.selectText(jobsheet, "//ArticleInfo/@OutputMedium");
        final String articleRelDOI = isIssueJobSheet
                ? DOMHelper.selectText(jobsheet,
                        XPATH_DISCREET_ISSUE + artId
                                + "\"]/ArticleInfo/ArticleRelatedObject/RelatedObjectDOI")
                : DOMHelper.selectText(jobsheet,
                        "//ArticleInfo/ArticleRelatedObject/RelatedObjectDOI");
        
        return new ArticleData(articleDOI, articleType, outputMedium, articleRelDOI);
    }
    
    /**
     * Check whether creator work with issue jobsheet.
     * 
     * @return true if article file path contains "/", otherwise - false
     */
    private boolean isIssueJobSheet() {
        // a heuristic way to detect whether the issue jobsheet
        return articleFileRef.contains("/");
    }
    
    private void copyInfoFromJobsheetIntoArticleXML() throws Exception {
        final Document artDoc = getArticleDocument();
        
        final String artId =
                DOMHelper.selectText(jobsheet, "//ContentFiles/File/AplusplusRootFile[@FileRef=\""
                        + articleFileRef + "\"]/../@DiscreteObjectID");
        
        // Getting ArticleInfo from Tag Name
        final NodeList list = isIssueJobSheet()
                ? DOMHelper.selectNodeList(jobsheet,
                        XPATH_DISCREET_ISSUE + artId + "\"]/ArticleInfo")
                : DOMHelper.selectNodeList(jobsheet, "//ArticleInfo");
        
        final Element element = (Element) list.item(0);
        
        // Imports a node from another document to this document, without altering
        // or removing the source node from the original document
        final Node copiedNode = artDoc.importNode(element, true);
        
        final NodeList authHeadlist = artDoc.getElementsByTagName("ArticleHeader");
        final Element authHeader = (Element) authHeadlist.item(0);
        
        // Adds the node to the end of the list of children of this node
        
        artDoc.getDocumentElement().insertBefore(copiedNode, authHeader);
        
        // Create Nodelist for Node Abstract under ArticleHeader Element
        final NodeList abslist = artDoc.getElementsByTagName("Abstract");
        final Element eleNodeAbs = (Element) abslist.item(0);
        
        // Appending AuthorGroup Node
        final NodeList authlist = isIssueJobSheet()
                ? DOMHelper.selectNodeList(jobsheet,
                        XPATH_DISCREET_ISSUE + artId + "\"]/AuthorGroup")
                : DOMHelper.selectNodeList(jobsheet, "//AuthorGroup");
        
        final Element authElement = (Element) authlist.item(0);
        
        // Imports a Author Group node from another Jobsheet document to Article XML document,
        // without altering
        // or removing the source node from the original document
        final Node authNode = artDoc.importNode(authElement, true);
        LOG.debug("ArticleXMLCreator.copyArticleInfoNode(---Author Group Name)"
                + authNode.getNodeName() + "--Element Abstract Node---" + eleNodeAbs.getNodeName());
        
        // Adds the node to the end of the list of children of this node
        
        authHeader.insertBefore(authNode, eleNodeAbs);
        
    }
    
    private Document getArticleDocument() throws Exception {
        if (articleDoc == null) {
            articleDoc = DOMHelper.parse(articleXmlFile);
        }
        return articleDoc;
    }
    
    private static void writeNode(final Document xml) throws Exception {
        
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, UTF_8);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        final DocumentType doctype = xml.getDoctype();
        if (doctype != null) {
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        }
        transformer.transform(new DOMSource(xml), new StreamResult(new FileWriter(fileArticleXML)));
    }
    
    /**
     * @param xml ""
     * @param nodeName ""
     * @throws Exception
     */
    public static final void removeNode(final Document xml, final String nodeName)
            throws Exception {
        final NodeList list = xml.getElementsByTagName(nodeName);
        
        final Element element = (Element) list.item(0);
        element.getParentNode().removeChild(element);
        // Normalize the DOM tree, puts all text nodes in the
        // full depth of the sub-tree underneath this node
        xml.normalize();
        
        writeNode(xml);
    }
    
    /**
     * @param xml ""
     * @param nodeName ""
     * @param sAttributeValue ""
     * @param itemPos ""
     * @throws Exception
     */
    public static final void removeNodeByAttributeValue(final Document xml, final String nodeName,
            final String sAttributeValue, final int itemPos) throws Exception {
        // Normalize the DOM tree, puts all text nodes in the
        NodeList nList = xml.getElementsByTagName(nodeName);
        for (int i = 0; i < nList.getLength(); i++) {
            final Node node = nList.item(i);
            if (node.getNodeType() == Element.ELEMENT_NODE) {
                final Element eElement = (Element) node;
                if (eElement.getAttribute("ID").equals(sAttributeValue)) {
                    node.getParentNode().removeChild(node);
                }
            }
        }
        nList = null;
        // full depth of the sub-tree underneath this node
        xml.normalize();
        
        writeOutFilePutNode(xml, fileOutPutArticleXML);
        
    }
    
    /**
     * @param xml ""
     * @param nodeName ""
     * @param sAttributeName ""
     * @param sAttributeValue ""
     * @throws Exception
     */
    public static final void removeNodeByAttributeValue(final Document xml, final String nodeName,
            final String sAttributeName, final String sAttributeValue) throws Exception {
        // Get the XML element by attribute value
        final String xpath =
                "//" + nodeName + "[@" + sAttributeName + "='" + sAttributeValue + "']";
        final Node artNode = DOMHelper.selectNodeList(xml.getDocumentElement(), xpath).item(0);
        
        // Remove node
        artNode.getParentNode().removeChild(artNode);
        
        xml.normalize();
    }
    
    private static void writeOutFilePutNode(final Document xml,
            final Object fileOutPutArticleXML2) {
        
    }
    
    /**
     * @param xml ""
     * @param nodeName ""
     * @param attName ""
     * @param attValue ""
     * @throws Exception
     */
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
    
    /**
     * @param xml ""
     * @param nodeName ""
     * @param sAttributeName ""
     * @param sAttributeValue ""
     * @param attName ""
     * @param attValue ""
     * @throws Exception
     */
    public static final void updateNodeAttribute(final Document xml, final String nodeName,
            final String sAttributeName, final String sAttributeValue, final String attName,
            final String attValue) throws Exception {
        // Get the XML element by attribute value
        final String xpath =
                "//" + nodeName + "[@" + sAttributeName + "='" + sAttributeValue + "']";
        final Node artNode = DOMHelper.selectNodeList(xml.getDocumentElement(), xpath).item(0);
        
        // Update staff attribute
        final NamedNodeMap attr = artNode.getAttributes();
        final Node nodeAttr = attr.getNamedItem(attName);
        nodeAttr.setTextContent(attValue);
        
        xml.normalize();
    }
    
    /**
     * @param xml ""
     * @param nodeName ""
     * @param attName ""
     * @param attValue ""
     * @param itemPos ""
     * @throws Exception
     */
    public static final void updateNodeAttributeValue(final Document xml, final String nodeName,
            final String attName, final String attValue, int itemPos) throws Exception {
        final NodeList list = xml.getElementsByTagName(nodeName);
        if (list.getLength() == 1) {
            itemPos = 0;
        }
        final Element element = (Element) list.item(itemPos);
        // Get the XML element by tag name directly
        if (element.hasAttributes()) {
            
            final NamedNodeMap attr = element.getAttributes();
            final Node nodeAttr = attr.getNamedItem(attName);
            nodeAttr.setTextContent(attValue);
            
            xml.normalize();
            
            writeOutFilePutNode(xml, fileOutPutArticleXML);
        }
        
    }
    
    public static final void updateSupplementaryMaterial(final File sArticleFileName,
            final String sPackageFileName, final ArrayList<String> aSupportingFile)
            throws Exception {
        
        fileOutPutArticleXML = sArticleFileName;
        final InputStream inputStream = new FileInputStream(fileOutPutArticleXML);
        final Reader reader = new InputStreamReader(inputStream, UTF_8);
        final InputSource is = new InputSource(reader);
        is.setEncoding(UTF_8);
        
        final Document workdoc = createDocumentObject(sArticleFileName);
        
        for (int i = 0; i < aSupportingFile.size(); i++) {
            if (aSupportingFile.contains("115_2015_192_MOESM1_ESM.PDF")) {
                updateNodeAttributeValue(workdoc, "DataObject", FILE_REF,
                        sPackageFileName + "MOESM1_ESM.PDF", 0);
                
            } else {
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM1", 0);
                
            }
            
            if (aSupportingFile.contains("115_2015_192_MOESM2_ESM.PDF")) {
                updateNodeAttributeValue(workdoc, "DataObject", FILE_REF,
                        sPackageFileName + "MOESM2_ESM.PDF", 1);
            } else {
                
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM2", 0);
            }
            
            if (aSupportingFile.contains("115_2015_192_MOESM4_ESM.gif")) {
                
                updateNodeAttributeValue(workdoc, "ImageObject", FILE_REF,
                        sPackageFileName + "MOESM4_ESM.gif", 1);
            } else {
                
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM3", 0);
            }
            
            if (aSupportingFile.contains("115_2015_192_MOESM3_ESM.tif")) {
                updateNodeAttributeValue(workdoc, "ImageObject", FILE_REF,
                        sPackageFileName + "MOESM3_ESM.tif", 0);
            } else {
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM4", 0);
                
            }
            
            if (aSupportingFile.contains("115_2015_192_MOESM5_ESM.mp3")) {
                
                updateNodeAttributeValue(workdoc, "AudioObject", FILE_REF,
                        sPackageFileName + "MOESM5_ESM.mp3", 0);
            } else {
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM5", 0);
            }
            if (aSupportingFile.contains("115_2015_192_MOESM6_ESM.mp3")) {
                
                updateNodeAttributeValue(workdoc, "AudioObject", FILE_REF,
                        sPackageFileName + "MOESM6_ESM.mp3", 1);
            } else {
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM6", 0);
            }
            
            if (aSupportingFile.contains("115_2015_192_MOESM7_ESM.wmv")) {
                updateNodeAttributeValue(workdoc, "VideoObject", FILE_REF,
                        sPackageFileName + "MOESM7_ESM.wmv", 0);
            } else {
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM7", 0);
            }
            
            if (aSupportingFile.contains("115_2015_192_MOESM8_ESM.wmv")) {
                
                updateNodeAttributeValue(workdoc, "VideoObject", FILE_REF,
                        sPackageFileName + "MOESM8_ESM.wmv", 1);
            } else {
                removeNodeByAttributeValue(workdoc, MEDIA_OBJECT, "MOESM8", 0);
                
            }
        }
    }
    
    private static Document createDocumentObject(final File articleFileName) throws Exception {
        Document docArticle = null;
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(false);
            final InputStream inputStream = new FileInputStream(articleFileName);
            final Reader reader = new InputStreamReader(inputStream, UTF_8);
            final InputSource is = new InputSource(reader);
            is.setEncoding(UTF_8);
            
            final DocumentBuilder db = dbf.newDocumentBuilder();
            docArticle = db.parse(is);
        } catch (final Exception exe) {
            throw exe;
        }
        return docArticle;
    }
    
    public void setNodeValue(final Document xml, final String tagName, final String value) {
        
        final NodeList nList = xml.getElementsByTagName(tagName);
        for (int i = 0; i < nList.getLength(); i++) {
            final Node node = nList.item(i);
            
            // Locate the child text node and change its value
            final NodeList childNodes = node.getChildNodes();
            for (int y = 0; y < childNodes.getLength(); y++) {
                final Node data = childNodes.item(y);
                if (data.getNodeType() == Node.TEXT_NODE) {
                    data.setNodeValue(value);
                    return;
                }
            }
        }
    }
    
    public void updateArticleXMLESM(final File sArticleFileName, final String sPackageFileName) {
        
        try {
            final FileReader fr = new FileReader(sArticleFileName);
            String s;
            String totalStr = "";
            try (BufferedReader br = new BufferedReader(fr)) {
                
                while ((s = br.readLine()) != null) {
                    totalStr += s;
                }
                totalStr = totalStr.replaceAll("115_2015_192_", sPackageFileName);
                final FileWriter fw = new FileWriter(sArticleFileName);
                fw.write(totalStr);
                fw.close();
            }
        } catch (final FileNotFoundException e) {
            LOG.error("FileNotFoundException in updateArticleXMLESM()  is " + e.getMessage());
        } catch (final IOException e) {
            LOG.error("IOException in updateArticleXMLESM()  is " + e.getMessage());
        } catch (final Exception exe) {
            LOG.error("Exception in updateArticleXMLESM()  is " + exe.getMessage());
        }
        
    }
    
}