//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.12 at 07:28:45 PM MSK 
//


package com.spr.jfluxpackagegenerator.jobsheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{}IssueJobSheet"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{}ArticleJobSheet"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="Version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="JobSheetDate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Supplier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "issueJobSheet",
    "articleJobSheet"
})
@XmlRootElement(name = "JobSheet")
public class JobSheet {

    @XmlElement(name = "IssueJobSheet")
    protected IssueJobSheet issueJobSheet;
    @XmlElement(name = "ArticleJobSheet")
    protected ArticleJobSheet articleJobSheet;
    @XmlAttribute(name = "Version")
    protected String version;
    @XmlAttribute(name = "JobSheetDate")
    protected String jobSheetDate;
    @XmlAttribute(name = "Supplier")
    protected String supplier;

    /**
     * Gets the value of the issueJobSheet property.
     * 
     * @return
     *     possible object is
     *     {@link IssueJobSheet }
     *     
     */
    public IssueJobSheet getIssueJobSheet() {
        return issueJobSheet;
    }

    /**
     * Sets the value of the issueJobSheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link IssueJobSheet }
     *     
     */
    public void setIssueJobSheet(IssueJobSheet value) {
        this.issueJobSheet = value;
    }

    /**
     * Gets the value of the articleJobSheet property.
     * 
     * @return
     *     possible object is
     *     {@link ArticleJobSheet }
     *     
     */
    public ArticleJobSheet getArticleJobSheet() {
        return articleJobSheet;
    }

    /**
     * Sets the value of the articleJobSheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleJobSheet }
     *     
     */
    public void setArticleJobSheet(ArticleJobSheet value) {
        this.articleJobSheet = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the jobSheetDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobSheetDate() {
        return jobSheetDate;
    }

    /**
     * Sets the value of the jobSheetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobSheetDate(String value) {
        this.jobSheetDate = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplier(String value) {
        this.supplier = value;
    }

}