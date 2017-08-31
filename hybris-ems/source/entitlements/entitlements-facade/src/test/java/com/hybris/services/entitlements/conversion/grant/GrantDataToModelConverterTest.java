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
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.domain.Grant;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-facade-test-spring.xml")
public class GrantDataToModelConverterTest
{
	@Autowired
	private GrantDataToModelConverter grantDataToModelConverter;

	@Autowired
	private DateTimeConverter dateTimeConverter;

	@Test
	@Transactional
	public void shouldConvertGrantDataToModel()
	{
		final GrantData data = new GrantData();
		final String user = "user";
		final String grantSource = "test";
		final String grantSourceId = "shouldConvertGrantDataToModel";
		final Date timestamp = new Date(1000);
		final String entitlementType = "type";

		data.setUserId(user);
		data.setGrantSource(grantSource);
		data.setGrantSourceId(grantSourceId);
		data.setGrantTime(dateTimeConverter.convertDateToString(timestamp));
		data.setEntitlementType(entitlementType);

		final Grant result = grantDataToModelConverter.convert(data);

		assertNotNull(result);
		assertEquals(user, result.getUserId());
		assertEquals(grantSource, result.getGrantSource());
		assertEquals(grantSourceId, result.getGrantSourceId());
		assertEquals(timestamp, result.getGrantTime());
		assertEquals(entitlementType, result.getEntitlementType());
	}

	@Test
	@Transactional
	public void shouldConvertDynamicProperties()
	{
		final GrantData source = new GrantData();
		source.setProperty("test", "passed");
		final Grant target = grantDataToModelConverter.convert(source);
		assertEquals(1, target.getAllProperties().size());
		assertEquals("passed", target.getProperty("test"));
	}

	@Test
	@Transactional
	public void shouldSkipNullDynamicProperties()
	{
		final GrantData source = new GrantData();
		source.setProperty("test", null);
		final Grant target = grantDataToModelConverter.convert(source);
		assertTrue(target.getAllProperties().isEmpty());
	}
}
