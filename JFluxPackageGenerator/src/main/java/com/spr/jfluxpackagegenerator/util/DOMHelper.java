package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Utility class for DOM XML operations.
 * 
 * @author Alexey Dergalev
 */
public final class DOMHelper {
    
    private DOMHelper() {
        
    }
    
    /**
     * Parse a DOM tree from a stream using default settings.
     * 
     * @param source The stream.
     * @return The document.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document parse(final InputStream source) throws ParserConfigurationException,
            SAXException, IOException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
    }
    
    /**
     * Parse a DOM tree from a stream using default settings.
     * 
     * @param source The stream.
     * @param validate whether the document be validated
     * @return The document.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document parse(final InputStream source, final boolean validate)
            throws ParserConfigurationException, SAXException, IOException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validate);
        return factory.newDocumentBuilder().parse(source);
    }
    
    /**
     * Parse a DOM tree from a file using default settings.
     * 
     * @param f The file.
     * @param validate whether the document be validated
     * @return The document.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document parse(final File f, final boolean validate)
            throws ParserConfigurationException, SAXException, IOException {
        final FileInputStream in = new FileInputStream(f);
        try {
            return parse(in, validate);
        } finally {
            StreamHelper.close(in);
        }
    }
    
    /**
     * Parse a DOM tree from a file using default settings.
     * 
     * @param f The file.
     * @return The document.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Document parse(final File f) throws ParserConfigurationException, SAXException,
            IOException {
        final FileInputStream in = new FileInputStream(f);
        try {
            return parse(in);
        } finally {
            StreamHelper.close(in);
        }
    }
    
    /**
     * Serialize a document to string.
     * 
     * @param doc node for serialization
     * @return string
     * @throws TransformerException
     */
    public static String serializeDocument(final Document doc) throws TransformerException {
        final Source source = new DOMSource(doc);
        
        final StringWriter writer = new StringWriter();
        final Result result = new StreamResult(writer);
        
        final TransformerFactory factory = TransformerFactory.newInstance();
        final Transformer transformer = factory.newTransformer();
        
        final DocumentType docType = doc.getDoctype();
        if (docType != null) {
            final String publicId = docType.getPublicId();
            final String systemId = docType.getSystemId();
            if (publicId != null) {
                transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, publicId);
            }
            if (systemId != null) {
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemId);
            }
        }
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(source, result);
        
        return writer.toString();
    }
    
    public static void writeDocument(final Document xml, final OutputStream ous) throws Exception {
        
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        final DocumentType doctype = xml.getDoctype();
        if (doctype != null) {
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        }
        transformer.transform(new DOMSource(xml), new StreamResult(ous));
    }
    
    public static void removeBlankNodes(final Node node) {
        final NodeList list = node.getChildNodes();
        
        for (int i = 0; i < list.getLength(); i++) {
            removeBlankNodes(list.item(i));
        }
        final boolean emptyElement =
                node.getNodeType() == Node.ELEMENT_NODE && node.getChildNodes().getLength() == 0;
        final boolean emptyText =
                node.getNodeType() == Node.TEXT_NODE && node.getNodeValue().trim().isEmpty();
        
        if (emptyElement || emptyText) {
            node.getParentNode().removeChild(node);
        }
    }
    
    /**
     * Select a list of nodes using XPath.
     * 
     * @param xpath The XPath expression.
     * @return The resulting node list.
     * @throws TransformerException If the expression is invalid.
     */
    public static NodeList selectNodeList(final Node contextNode, final String xpath)
            throws TransformerException {
        return XPathAPI.selectNodeList(contextNode, xpath);
    }
    
    /**
     * Select a number of text values from the DOM.
     * 
     * @param xpath The XPath that specifies the text values.
     * @return The result nodes of the XPath converted to strings.
     * @throws TransformerException if the XPath is invalid.
     */
    public static List<String> selectTexts(final Node contextNode, final String xpath)
            throws Exception {
        final NodeList resultNodes = XPathAPI.selectNodeList(contextNode, xpath);
        final List<String> texts = new ArrayList<String>();
        for (int i = 0; i < resultNodes.getLength(); ++i) {
            texts.add(getText(resultNodes.item(i)));
        }
        return texts;
    }
    
    /**
     * Get a text value from a node and an XPath expression. The XPath expression is evaluated
     * relative to the given node and the result node is interpreted as a text value.
     * 
     * @param node The node.
     * @param xpath The XPath expression.
     * @return The text value, <code>null</code> if the XPath has no result node.
     * @throws TransformerException If the XPath expression is invalid.
     * @see DOMHelper#getText(Node)
     */
    public static String selectText(final Node node, final String xpath) throws Exception {
        final Node resultNode = XPathAPI.selectSingleNode(node, xpath);
        return (resultNode == null) ? null : getText(resultNode);
    }
    
    /**
     * Get a text value from a node. The result depends on the node type:
     * <dl>
     * <dt>attribute</dt>
     * <dd>The attribute value.</dd>
     * <dt>CDATA section</dt>
     * <dd>The CDATA</dd>
     * <dt>comment</dt>
     * <dd>The comment</dd>
     * <dt>text</dt>
     * <dd>The text value</dd>
     * <dt>element</dt>
     * <dd>Must have only one child which must be a text node. Then the value of this text node is
     * returned</dd>
     * </dl>
     * 
     * @param node The node.
     * @return The text value, may be <code>null</code>.
     */
    public static String getText(final Node node) throws Exception {
        return XPathAPI.eval(node, ".").str();
    }
    
}
