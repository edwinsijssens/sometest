package com.spr.jfluxpackagegenerator.util;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Label;

public class ButtonLink extends Label {

	/**
	 * Constructor
	 *
	 * @param caption
	 *            text on the button
	 * @param externalResource
	 *            where link goes
	 * @param width
	 *            added to the constructor to make you remember that you don't
	 *            specify a width the button does not appear (it's 0px wide)
	 */
	public ButtonLink(final String caption, final ExternalResource externalResource, final String width,
			final String align) {
		super("<a href='" + externalResource.getURL() + "' style='text-decoration: none; float: " + align + ";'>" +
		// The following lines are copy pasted from rendered Vaadin v6.1
		// buttons.
				"<div class='v-button' tabindex='0' style='width: " + width + ";'>" + "<span class='v-button-wrap'>"
				+ "<span class='v-button-caption'>" + caption + "<span class='v-button-icon'>" + "</span>" + "</span>"
				+ "</span>" + "</div>" + "</a>", Label.CONTENT_XHTML);
	}
}
