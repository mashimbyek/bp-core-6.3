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

import com.hybris.commons.conversion.ConversionException;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.domain.Grant;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-facade-test-spring.xml")
public class GrantDataToModelPopulatorTest
{
	@Autowired
	private GrantDataToModelPopulator grantDataToModelPopulator;


	@Test(expected = ConversionException.class)
	public void shouldConvertGrantDataToModel()
	{
		final GrantData grantData = new GrantData();
		final String user = "user";
		final String grantSource = "test";
		final String grantSourceId = "shouldConvertGrantDataToModel";
		final String entitlementType = "type";

		grantData.setUserId(user);
		grantData.setGrantSource(grantSource);
		grantData.setGrantSourceId(grantSourceId);
		grantData.setGrantTime("wrong value");
		grantData.setEntitlementType(entitlementType);

		Grant grant = Mockito.mock(Grant.class);
		grantDataToModelPopulator.populate(grantData, grant);
	}

}
