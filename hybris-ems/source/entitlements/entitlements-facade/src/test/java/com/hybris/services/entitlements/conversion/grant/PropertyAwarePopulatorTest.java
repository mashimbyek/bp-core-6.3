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

import static junit.framework.Assert.assertNull;

import com.hybris.commons.conversion.Populator;
import com.hybris.commons.dto.PropertyAwareDto;
import com.hybris.kernel.api.PropertyAware;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.service.GrantService;

import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-facade-test-spring.xml")
public class PropertyAwarePopulatorTest
{
	@Autowired
	@Qualifier("propertyAwarePopulator")
	private Populator<PropertyAware, PropertyAwareDto> populator;

	@Autowired
	private GrantService grantService;

	@Test
	@Transactional
	public void shouldWorkWithNullValuesOfLocalizedProperties()
	{
		// Given an instance of PropertyAware descendant
		final Grant source = grantService.newGrant();
		// When the instance has a localized property with null value
		source.setProperty("test", null, Locale.CHINESE);
		final GrantData target = new GrantData();
		// Then population causes NPE
		populator.populate(source, target);
		assertNull(target.getProperty("test", Locale.CHINESE));
	}
}
