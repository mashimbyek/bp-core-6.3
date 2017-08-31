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
package com.hybris.services.entitlements.conversion.grant;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-facade-test-spring.xml")
public class GrantModelToDataConverterTest
{
	@Autowired
	private GrantService grantService;

	@Autowired
	private GrantModelToDataConverter grantModelToDataConverter;

	@Autowired
	private DateTimeConverter dateTimeConverter;

	@Test
	@Transactional
	public void shouldConvertGrantModelToData()
	{
		final Grant source = grantService.newGrant();
		final Date timestamp = new Date(System.currentTimeMillis() - 10000);
		final String userId = "userid";
		final String entitlementType = "type";
		final String grantSourceId = "shouldConvertGrantModelToData";
		final String grantSource = "test";

		source.setUserId(userId);
		source.setGrantTime(timestamp);
		source.setEntitlementType(entitlementType);
		source.setGrantSourceId(grantSourceId);
		source.setGrantSource(grantSource);

		final GrantData data = grantModelToDataConverter.convert(source);

		assertEquals(userId, data.getUserId());
		assertEquals(dateTimeConverter.convertDateToString(timestamp), data.getGrantTime());
		assertEquals(entitlementType, data.getEntitlementType());
		assertEquals(grantSourceId, data.getGrantSourceId());
		assertEquals(grantSource, data.getGrantSource());
	}

	@Test
	@Transactional
	public void shouldConvertDynamicProperties()
	{
		final Grant source = grantService.newGrant();
		source.setProperty("test", "passed");
		final GrantData target = grantModelToDataConverter.convert(source);
		assertEquals(1, target.getProperties().size());
		assertEquals("passed", target.getProperty("test"));
	}

	@Test
	@Transactional
	public void shouldSkipNullDynamicProperties()
	{
		final Grant source = grantService.newGrant();
		source.setProperty("test", null);
		final GrantData target = grantModelToDataConverter.convert(source);
		assertTrue(target.getProperties() == null || target.getProperties().isEmpty());
	}
}
