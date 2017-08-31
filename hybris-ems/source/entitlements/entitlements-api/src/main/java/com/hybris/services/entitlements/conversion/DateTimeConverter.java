/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.conversion;

import java.text.ParseException;
import java.util.Date;


/**
 * Time conversion.
 */
public interface DateTimeConverter
{
	/**
	 * Convert a date to it's string representation.
	 *
	 * @param timestamp date
	 * @return string representation
	 */
	String convertDateToString(final Date timestamp);

	/**
	 * Convert a string to date.
	 *
	 * @param timestamp string representation of a date
	 * @return parsed date
	 * @throws ParseException if timestamp has invalid format
	 */
	Date convertStringToDate(final String  timestamp) throws ParseException;
}
