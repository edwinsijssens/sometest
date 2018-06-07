package com.spr.jfluxpackagegenerator.jobsheet.builder;

import com.spr.jfluxpackagegenerator.jobsheet.JournalInfo;
import com.spr.jfluxpackagegenerator.jobsheet.ProductionInfo;
import com.spr.jfluxpackagegenerator.jobsheet.PublisherInfo;
import com.spr.jfluxpackagegenerator.jobsheet.SuppInfo;

/**
 * Interface for ArticleJobSheet and IssueJobSheet, to put sub-elements
 * 
 * @author Alexey Dergalev
 */
public interface JobSheetDirectChild {

	/**
	 * Sets the value of the publisherInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link PublisherInfo }
	 */
	void setPublisherInfo(PublisherInfo value);

	/**
	 * Sets the value of the journalInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link JournalInfo }
	 */
	void setJournalInfo(JournalInfo value);

	/**
	 * Sets the value of the productionInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link ProductionInfo }
	 */
	void setProductionInfo(ProductionInfo value);

	void setSuppInfo(SuppInfo value);

}
