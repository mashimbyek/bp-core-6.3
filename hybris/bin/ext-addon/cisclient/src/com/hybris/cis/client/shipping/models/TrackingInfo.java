/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.cis.client.shipping.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "label")
@XmlAccessorType(XmlAccessType.FIELD)
public class TrackingInfo
{
	/** The carrier name. */
	@XmlElement(name = "vendorName")
	private String vendorName;

	/** The tracking number. */
	@XmlElement(name = "trackingNumber")
	private String trackingNumber;

	/** The tracking URL. */
	@XmlElement(name = "trackingUrl")
	private String trackingUrl;

	public String getVendorName()
	{
		return this.vendorName;
	}

	public void setVendorName(final String vendorName)
	{
		this.vendorName = vendorName;
	}

	public String getTrackingNumber()
	{
		return this.trackingNumber;
	}

	public void setTrackingNumber(final String trackingNumber)
	{
		this.trackingNumber = trackingNumber;
	}

	public String getTrackingUrl()
	{
		return this.trackingUrl;
	}

	public void setTrackingUrl(final String trackingUrl)
	{
		this.trackingUrl = trackingUrl;
	}
}
