//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.19 at 06:11:57 PM MSK 
//


package com.spr.jfluxpackagegenerator.jobsheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;sequence>
 *         &lt;element ref="{}IssueFrontmatterFirstPage"/>
 *         &lt;element ref="{}IssueFrontmatterLastPage"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "issueFrontmatterFirstPage",
    "issueFrontmatterLastPage"
})
@XmlRootElement(name = "IssueFrontmatterInfo")
public class IssueFrontmatterInfo {

    @XmlElement(name = "IssueFrontmatterFirstPage", required = true)
    protected String issueFrontmatterFirstPage;
    @XmlElement(name = "IssueFrontmatterLastPage", required = true)
    protected String issueFrontmatterLastPage;

    /**
     * Gets the value of the issueFrontmatterFirstPage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueFrontmatterFirstPage() {
        return issueFrontmatterFirstPage;
    }

    /**
     * Sets the value of the issueFrontmatterFirstPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueFrontmatterFirstPage(String value) {
        this.issueFrontmatterFirstPage = value;
    }

    /**
     * Gets the value of the issueFrontmatterLastPage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueFrontmatterLastPage() {
        return issueFrontmatterLastPage;
    }

    /**
     * Sets the value of the issueFrontmatterLastPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueFrontmatterLastPage(String value) {
        this.issueFrontmatterLastPage = value;
    }

}
