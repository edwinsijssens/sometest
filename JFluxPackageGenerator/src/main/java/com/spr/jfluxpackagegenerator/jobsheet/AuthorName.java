//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.08.12 at 07:28:45 PM MSK 
//

package com.spr.jfluxpackagegenerator.jobsheet;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Prefix"/>
 *         &lt;element ref="{}GivenName" maxOccurs="3" minOccurs="0"/>
 *         &lt;element ref="{}Particle"/>
 *         &lt;element ref="{}FamilyName"/>
 *         &lt;element ref="{}Suffix"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DisplayOrder" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "prefix", "givenName", "particle", "familyName", "suffix" })
@XmlRootElement(name = "AuthorName")
public class AuthorName implements JobSheetName {
    
    @XmlElement(name = "Prefix", required = true)
    protected String prefix;
    
    @XmlElement(name = "GivenName")
    protected List<String> givenName;
    
    @XmlElement(name = "Particle", required = true)
    protected String particle;
    
    @XmlElement(name = "FamilyName", required = true)
    protected String familyName;
    
    @XmlElement(name = "Suffix", required = true)
    protected String suffix;
    
    @XmlAttribute(name = "DisplayOrder", required = true)
    protected String displayOrder;
    
    /**
     * Gets the value of the prefix property.
     * 
     * @return possible object is {@link String }
     */
    @Override
    public String getPrefix() {
        return prefix;
    }
    
    /**
     * Sets the value of the prefix property.
     * 
     * @param value allowed object is {@link String }
     */
    @Override
    public void setPrefix(final String value) {
        this.prefix = value;
    }
    
    /**
     * Gets the value of the givenName property.
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the givenName property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGivenName().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    @Override
    public List<String> getGivenName() {
        if (givenName == null) {
            givenName = new ArrayList<String>();
        }
        return this.givenName;
    }
    
    /**
     * Gets the value of the particle property.
     * 
     * @return possible object is {@link String }
     */
    @Override
    public String getParticle() {
        return particle;
    }
    
    /**
     * Sets the value of the particle property.
     * 
     * @param value allowed object is {@link String }
     */
    @Override
    public void setParticle(final String value) {
        this.particle = value;
    }
    
    /**
     * Gets the value of the familyName property.
     * 
     * @return possible object is {@link String }
     */
    @Override
    public String getFamilyName() {
        return familyName;
    }
    
    /**
     * Sets the value of the familyName property.
     * 
     * @param value allowed object is {@link String }
     */
    @Override
    public void setFamilyName(final String value) {
        this.familyName = value;
    }
    
    /**
     * Gets the value of the suffix property.
     * 
     * @return possible object is {@link String }
     */
    @Override
    public String getSuffix() {
        return suffix;
    }
    
    /**
     * Sets the value of the suffix property.
     * 
     * @param value allowed object is {@link String }
     */
    @Override
    public void setSuffix(final String value) {
        this.suffix = value;
    }
    
    /**
     * Gets the value of the displayOrder property.
     * 
     * @return possible object is {@link String }
     */
    public String getDisplayOrder() {
        return displayOrder;
    }
    
    /**
     * Sets the value of the displayOrder property.
     * 
     * @param value allowed object is {@link String }
     */
    public void setDisplayOrder(final String value) {
        this.displayOrder = value;
    }
    
}