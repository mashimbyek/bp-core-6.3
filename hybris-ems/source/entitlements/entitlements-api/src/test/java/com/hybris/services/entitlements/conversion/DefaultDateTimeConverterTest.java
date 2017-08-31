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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Testing conversion date to string and vice versa.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/entitlement-api-spring.xml")
public class DefaultDateTimeConverterTest
{

	@Autowired
	private DateTimeConverter dateTimeConverter;


	@Test
    public void shouldAcceptNullDate()
    {
		assertNull(dateTimeConverter.convertDateToString(null));
    }

	@Test
	public void shouldAcceptNullString() throws ParseException
	{
		assertNull(dateTimeConverter.convertStringToDate(null));
	}

	@Test
	public void shouldConvertStringToDateAndTime() throws ParseException
	{
		final Date date = dateTimeConverter.convertStringToDate("2013-09-03T10:12:07Z");
		assertEquals(2013, 1900 + date.getYear());
		assertEquals(8, date.getMonth());
		assertEquals(2, date.getDay());
		final int timeZoneOffset_min = TimeZone.getDefault().getOffset(date.getTime())/60/1000;
		assertEquals(10, date.getHours() - timeZoneOffset_min/60);
		assertEquals(12, date.getMinutes() - timeZoneOffset_min%60);
		assertEquals(7, date.getSeconds());
	}

	@Test
	public void shouldConvertStringToDateAndTimeUsingTimeZone() throws ParseException
	{
		final Date date = dateTimeConverter.convertStringToDate("2013-09-03T10:45+00:05");
		assertEquals(2013, 1900 + date.getYear());
		assertEquals(8, date.getMonth());
		assertEquals(2, date.getDay());
		final int timeZoneOffset_min = TimeZone.getDefault().getOffset(date.getTime())/60/1000;
		assertEquals(10, date.getHours() - timeZoneOffset_min/60);
		assertEquals(40, date.getMinutes() - timeZoneOffset_min%60);
	}

	@Test
	public void shouldConvertDateToString()
	{
		final Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 45);
		calendar.set(Calendar.SECOND, 07);
		final String date = dateTimeConverter.convertDateToString(calendar.getTime());
		assertEquals("2014-09-30T10:45:07Z", date);
	}

	@Test
	public void shouldConvertDateToStringUsingTimeZone()
	{
		final Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+07:00"));
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 30);
		calendar.set(Calendar.HOUR_OF_DAY, 17);
		calendar.set(Calendar.MINUTE, 45);
		calendar.set(Calendar.SECOND, 07);
		final String date = dateTimeConverter.convertDateToString(calendar.getTime());
		assertEquals("2014-09-30T10:45:07Z", date);
	}
}
