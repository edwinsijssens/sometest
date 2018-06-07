package com.spr.jfluxpackagegenerator.jobsheet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "confEvtName", "confEvtAbbr", "confEvtLoc", "confEvtDate", "confEvtUrl", "sponNote",
		"suppNote" })
@XmlRootElement(name = "SuppInfo")
public class SuppInfo {

	public String getConfEvtAbbr() {
		return confEvtAbbr;
	}

	public void setConfEvtAbbr(final String confEvtAbbr) {
		this.confEvtAbbr = confEvtAbbr;
	}

	public String getConfEvtLoc() {
		return confEvtLoc;
	}

	public void setConfEvtLoc(final String confEvtLoc) {
		this.confEvtLoc = confEvtLoc;
	}

	public String getConfEvtDate() {
		return confEvtDate;
	}

	public void setConfEvtDate(final String confEvtDate) {
		this.confEvtDate = confEvtDate;
	}

	public String getConfEvtUrl() {
		return confEvtUrl;
	}

	public void setConfEvtUrl(final String confEvtUrl) {
		this.confEvtUrl = confEvtUrl;
	}

	public String getSponNote() {
		return sponNote;
	}

	public void setSponNote(final String sponNote) {
		this.sponNote = sponNote;
	}

	public String getSuppNote() {
		return suppNote;
	}

	public void setSuppNote(final String suppNote) {
		this.suppNote = suppNote;
	}

	public String getOutputMedium() {
		return outputMedium;
	}

	public void setOutputMedium(final String outputMedium) {
		this.outputMedium = outputMedium;
	}

	public String getTocLevels() {
		return tocLevels;
	}

	public void setTocLevels(final String tocLevels) {
		this.tocLevels = tocLevels;
	}

	public void setConfEvtName(final String confEvtName) {
		this.confEvtName = confEvtName;
	}

	@XmlElement(name = "confEvtName", required = true)
	protected String confEvtName;
	@XmlElement(name = "confEvtAbbr", required = true)
	protected String confEvtAbbr;
	@XmlElement(name = "confEvtLoc", required = true)
	protected String confEvtLoc;
	@XmlElement(name = "confEvtDate", required = true)
	protected String confEvtDate;
	@XmlElement(name = "confEvtUrl", required = true)
	protected String confEvtUrl;
	@XmlElement(name = "sponNote", required = true)
	protected String sponNote;
	@XmlAttribute(name = "suppNote", required = true)
	protected String suppNote;
	@XmlAttribute(name = "OutputMedium", required = true)
	protected String outputMedium;
	@XmlAttribute(name = "TocLevels", required = true)
	protected String tocLevels;

	/**
	 * Gets the value of the issueIDStart property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConfEvtName() {
		return confEvtName;
	}

}
