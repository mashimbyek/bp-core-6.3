/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package de.hybris.platform.warehousing.asn.strategy.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.warehousing.model.AdvancedShippingNoticeEntryModel;
import de.hybris.platform.warehousing.model.AdvancedShippingNoticeModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.configuration.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Test for delayed release date calculation.
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DelayedReleaseDateStrategyTest
{
	@InjectMocks
	private DelayedReleaseDateStrategy delayedReleaseDateStrategy = new DelayedReleaseDateStrategy();

	@Mock
	private ConfigurationService configurationService;

	@Mock
	private Configuration configuration;

	private Date asnDate;
	private Date delayedDate;
	private AdvancedShippingNoticeEntryModel asnEntry;
	private AdvancedShippingNoticeModel asn;
	private SimpleDateFormat sdf;

	@Before
	public void setUp() throws ParseException
	{
		asnEntry = new AdvancedShippingNoticeEntryModel();
		asn = new AdvancedShippingNoticeModel();
		sdf = new SimpleDateFormat("yyyy-MM-dd");

		when(this.configurationService.getConfiguration()).thenReturn(this.configuration);
		asnDate = sdf.parse("2016-12-19");

		asn.setReleaseDate(asnDate);
		asnEntry.setAsn(asn);
	}

	/**
	 * Should return release date from ASN increased by 3 days, as configuration property value is 3.
	 */
	@Test
	public void shouldGetExpectedDelayedDate() throws ParseException
	{
		//Given
		delayedDate = sdf.parse("2016-12-22");
		// When
		when(this.configuration.getInt(DelayedReleaseDateStrategy.DELAY_DAYS)).thenReturn(3);
		// Then
		Assert.assertEquals(delayedDate, delayedReleaseDateStrategy.getReleaseDateForStockLevel(asnEntry));
	}

	/**
	 * Should return release date same as on ASN, as there was an Exception thrown when trying to retrieve data from
	 * configuration.
	 */
	@Test
	public void shouldGetAsnDate()
	{
		// When
		when(this.configuration.getInt(DelayedReleaseDateStrategy.DELAY_DAYS)).thenThrow(new NumberFormatException());
		// Then
		Assert.assertEquals(asnDate, delayedReleaseDateStrategy.getReleaseDateForStockLevel(asnEntry));
	}
}
