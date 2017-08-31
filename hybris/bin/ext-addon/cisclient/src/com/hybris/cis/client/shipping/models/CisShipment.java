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

import com.hybris.cis.client.shared.models.AnnotationHashMap;
import com.hybris.cis.client.shared.models.CisAddress;
import com.hybris.cis.client.shared.models.CisAddressType;
import com.hybris.cis.client.shared.models.CisResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "shipment")
@XmlAccessorType(XmlAccessType.FIELD)
public class CisShipment extends CisResult
{
	/** List of addresses. */
	@XmlElementWrapper(name = "addresses")
	@XmlElement(name = "address")
	private final List<CisAddress> addresses = new ArrayList<CisAddress>();

	/**
	 * Package information.
	 */
	@XmlElement(name = "package")
	private CisPackage thePackage; // NOPMD

	/**
	 * Requested service level.
	 * 
	 * @deprecated Use serviceMethod instead
	 */
	@Deprecated
	@XmlElement(name = "serviceLevel")
	private CisShippingServiceLevel serviceLevel;

	/**
	 * Service method to use for the shipment.
	 */
	@XmlElement(name = "serviceMethod")
	private String serviceMethod;

	/** Date used for shipping. */
	@XmlElement(name = "shipDate")
	private Date shipDate;

	/**
	 * Tracking infos.
	 */
	@XmlElementWrapper(name = "trackingInfos")
	@XmlElement(name = "trackingInfo")
	private List<TrackingInfo> trackingInfos;

	/**
	 * Url where the label can be found for this shipment.
	 */
	@XmlElementWrapper(name = "labels")
	@XmlElement(name = "label")
	private List<CisLabel> labels;

	@XmlElement(name = "payorCountryCode")
	private String payorCountryCode;

	/**
	 * Delivery Confirmation.
	 */
	@XmlElement(name = "deliveryConfirmation")
	private CisDeliveryConfirmation deliveryConfirmation;

	/**
	 * Notifications configuration.
	 */
	@XmlElement(name = "notification")
	private CisNotification notification;

	/**
	 * Vendor specific values to pass in the shipment.
	 */
	@XmlElement(name = "vendorParameters")
	private AnnotationHashMap vendorParameters;


	/**
	 * Returns the first address of the given type.
	 * 
	 * @param type The type you're interested in
	 * @return An address or null
	 */
	public CisAddress getAddressByType(final CisAddressType type)
	{
		for (final CisAddress cisAddress : this.getAddresses())
		{
			if (type.equals(cisAddress.getType()))
			{
				return cisAddress;
			}
		}
		return null;
	}

	/**
	 * @deprecated Use serviceMethod instead
	 * @return the serviceLevel
	 */
	@Deprecated
	public CisShippingServiceLevel getServiceLevel()
	{
		return this.serviceLevel;
	}

	/**
	 * @deprecated Use serviceMethod instead
	 * @param serviceLevel the serviceLevel to set
	 */
	@Deprecated
	public void setServiceLevel(final CisShippingServiceLevel serviceLevel)
	{
		this.serviceLevel = serviceLevel;
	}

	public String getServiceMethod()
	{
		return this.serviceMethod;
	}

	public void setServiceMethod(final String serviceMethod)
	{
		this.serviceMethod = serviceMethod;
	}

	public CisPackage getPackage()
	{
		return this.thePackage;
	}

	public void setPackage(final CisPackage cisPackage)
	{
		this.thePackage = cisPackage;
	}

	public Date getShipDate()
	{
		return this.shipDate == null ? null : new Date(this.shipDate.getTime());
	}

	public void setShipDate(final Date shipDate)
	{
		this.shipDate = shipDate == null ? null : new Date(shipDate.getTime());
	}

	public List<TrackingInfo> getTrackingInfos()
	{
		return this.trackingInfos;
	}

	public void setTrackingInfos(final List<TrackingInfo> trackingInfos)
	{
		this.trackingInfos = trackingInfos;
	}

	public String getPayorCountryCode()
	{
		return this.payorCountryCode;
	}

	public void setPayorCountryCode(final String payorCountryCode)
	{
		this.payorCountryCode = payorCountryCode;
	}

	public List<CisLabel> getLabels()
	{
		return this.labels;
	}

	public void setLabels(final List<CisLabel> labels)
	{
		this.labels = labels;
	}

	public AnnotationHashMap getVendorParameters()
	{
		return this.vendorParameters;
	}

	public void setVendorParameters(final AnnotationHashMap vendorParameters)
	{
		this.vendorParameters = vendorParameters;
	}

	public CisNotification getNotification()
	{
		return this.notification;
	}

	public void setNotification(final CisNotification notification)
	{
		this.notification = notification;
	}

	public List<CisAddress> getAddresses()
	{
		return this.addresses;
	}

	public CisDeliveryConfirmation getDeliveryConfirmation()
	{
		return this.deliveryConfirmation;
	}

	public void setDeliveryConfirmation(final CisDeliveryConfirmation deliveryConfirmation)
	{
		this.deliveryConfirmation = deliveryConfirmation;
	}
}
