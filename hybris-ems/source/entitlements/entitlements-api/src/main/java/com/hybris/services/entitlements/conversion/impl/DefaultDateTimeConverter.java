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
package com.hybris.services.entitlements.conversion.impl;

import com.hybris.services.entitlements.conversion.DateTimeConverter;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Time conversion utilities.
 */
public final class DefaultDateTimeConverter implements DateTimeConverter
{
	/**
	 * Supported pattern.
	 */
	private static final DateTimeFormatter PARSER = ISODateTimeFormat.dateTimeParser();
	private static final DateTimeFormatter FORMATTER = ISODateTimeFormat.dateTimeNoMillis().withZone(DateTimeZone.UTC);

	@Override
	public String convertDateToString(final Date timestamp)
	{
		if (timestamp == null)
		{
			return null;
		}
		final DateTime dateTime = new DateTime(timestamp);
		return FORMATTER.print(dateTime);
	}

	@Override
	public Date convertStringToDate(final String  timestamp) throws ParseException
	{
		if (timestamp == null)
		{
			return null;
		}
		final DateTime dateTime = PARSER.parseDateTime(timestamp);
		return dateTime.toDate();
	}
}
