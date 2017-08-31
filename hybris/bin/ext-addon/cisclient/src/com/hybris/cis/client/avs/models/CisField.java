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
package com.hybris.cis.client.avs.models;



/**
 * An address field.
 */
public enum CisField
{
	/** One of the (or multiple) address lines. */
	ADDRESS_LINES("addressLines"),

	/** The zip/postal code field. */
	ZIP_CODE("zipCode"),

	/** The city field. */
	CITY("city"),

	/** The state field. */
	STATE("state"),

	/** The country field. */
	COUNTRY("country"),

	/** Not clear which field is concerned. */
	UNKNOWN("UNKNOWN");


	private final String fieldId;

	private CisField(final String errorCode)
	{
		this.fieldId = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public String getFieldId()
	{
		return this.fieldId;
	}

}
