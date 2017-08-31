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
package com.hybris.services.entitlements.validation;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.hybris.services.entitlements.api.GrantData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class GrantPropertyValidatorTest
{
	@Autowired
	private GrantPropertyValidator grantPropertyValidator;

	@Test
	public void shouldRejectNullValues()
	{
		GrantData grantData = new GrantData();
		grantData.setProperty("test", null);
		ValidationViolations validationViolations = new ValidationViolations();
		grantPropertyValidator.validate(grantData, validationViolations);
		assertTrue(validationViolations.hasErrors());
		final String report = validationViolations.toString();
		assertTrue(report.contains("test=null: Must not be null"));
	}

	@Test
	public void shouldRejectNullPropertyName()
	{
		GrantData grantData = new GrantData();
		grantData.setProperty(null, "test");
		ValidationViolations validationViolations = new ValidationViolations();
		grantPropertyValidator.validate(grantData, validationViolations);
		assertTrue(validationViolations.hasErrors());
		final String report = validationViolations.toString();
		assertTrue(report.contains("Property name must not be null"));
	}

	@Test
	public void test()
	{
		GrantData grantData = new GrantData();
		ValidationViolations validationViolations = new ValidationViolations();
		grantPropertyValidator.validate(grantData, validationViolations);
		assertFalse(validationViolations.hasErrors());
	}
}
