package com.spr.jfluxpackagegenerator.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.spr.jfluxpackagegenerator.jobsheet.JobSheet;

public class JobSheetHelper {
    
    /** XML header. */
    private static final String JOBSHEET_HEADER =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE "
                    + "JobSheet PUBLIC \"-//Springer-Verlag//DTD A++ V2.4//EN\" \""
                    + "http://devel.springer.de/A++/V2.4/DTD/A++V2.4JobSheetV2.4.1.dtd\">";
    
    private JobSheetHelper() {};
    
    /**
     * Marshal jobsheet into output stream and convert to Document.
     * 
     * @param jobsheet jobsheet
     * @return Document, never null
     * @throws Exception
     */
    public static Document convertJobSheetToDocument(final JobSheet jobsheet) throws Exception {
        
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        final JAXBContext jaxbContext = JAXBContext.newInstance(JobSheet.class);
        final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlDeclaration", Boolean.FALSE);
        jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", JOBSHEET_HEADER);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        jaxbMarshaller.marshal(jobsheet, bos);
        
        final ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        final DocumentBuilder db = dbf.newDocumentBuilder();
        final Document doc = db.parse(bis);
        
        bos.close();
        bis.close();
        
        return doc;
        
    }
}
