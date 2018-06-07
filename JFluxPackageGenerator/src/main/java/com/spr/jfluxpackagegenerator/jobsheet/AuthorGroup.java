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
 *         &lt;element ref="{}Author" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}InstitutionalAuthor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}Affiliation" maxOccurs="unbounded" minOccurs="0"/>
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
    "author",
    "institutionalAuthor",
    "affiliation"
})
@XmlRootElement(name = "AuthorGroup")
public class AuthorGroup {

    @XmlElement(name = "Author")
    protected List<Author> author;
    @XmlElement(name = "InstitutionalAuthor")
    protected List<InstitutionalAuthor> institutionalAuthor;
    @XmlElement(name = "Affiliation")
    protected List<Affiliation> affiliation;

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Author }
     * 
     * 
     */
    public List<Author> getAuthor() {
        if (author == null) {
            author = new ArrayList<Author>();
        }
        return this.author;
    }

    /**
     * Gets the value of the institutionalAuthor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the institutionalAuthor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstitutionalAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstitutionalAuthor }
     * 
     * 
     */
    public List<InstitutionalAuthor> getInstitutionalAuthor() {
        if (institutionalAuthor == null) {
            institutionalAuthor = new ArrayList<InstitutionalAuthor>();
        }
        return this.institutionalAuthor;
    }

    /**
     * Gets the value of the affiliation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the affiliation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAffiliation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Affiliation }
     * 
     * 
     */
    public List<Affiliation> getAffiliation() {
        if (affiliation == null) {
            affiliation = new ArrayList<Affiliation>();
        }
        return this.affiliation;
    }

}