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
 *       &lt;sequence>
 *         &lt;element ref="{}InstitutionalAuthorName"/>
 *         &lt;element ref="{}Contact" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AffiliationIDS" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CorrespondingAffiliationID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "institutionalAuthorName",
    "contact"
})
@XmlRootElement(name = "InstitutionalAuthor")
public class InstitutionalAuthor {

    @XmlElement(name = "InstitutionalAuthorName", required = true)
    protected String institutionalAuthorName;
    @XmlElement(name = "Contact")
    protected Contact contact;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "AffiliationIDS")
    protected String affiliationIDS;
    @XmlAttribute(name = "CorrespondingAffiliationID")
    protected String correspondingAffiliationID;

    /**
     * Gets the value of the institutionalAuthorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitutionalAuthorName() {
        return institutionalAuthorName;
    }

    /**
     * Sets the value of the institutionalAuthorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitutionalAuthorName(String value) {
        this.institutionalAuthorName = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link Contact }
     *     
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contact }
     *     
     */
    public void setContact(Contact value) {
        this.contact = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the affiliationIDS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffiliationIDS() {
        return affiliationIDS;
    }

    /**
     * Sets the value of the affiliationIDS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffiliationIDS(String value) {
        this.affiliationIDS = value;
    }

    /**
     * Gets the value of the correspondingAffiliationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrespondingAffiliationID() {
        return correspondingAffiliationID;
    }

    /**
     * Sets the value of the correspondingAffiliationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrespondingAffiliationID(String value) {
        this.correspondingAffiliationID = value;
    }

}
