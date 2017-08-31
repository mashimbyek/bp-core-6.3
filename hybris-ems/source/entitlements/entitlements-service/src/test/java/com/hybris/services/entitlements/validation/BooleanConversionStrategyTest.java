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


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class BooleanConversionStrategyTest
{
	@Test(expected = IllegalArgumentException.class)
	public void testNonString()
	{
		BooleanConversionStrategy booleanConversionStrategy = new BooleanConversionStrategy();
		booleanConversionStrategy.convert(1);
	}

	@Test
	public void testNonStringValidation()
	{
		BooleanConversionStrategy booleanConversionStrategy = new BooleanConversionStrategy();
		ValidationViolations violations = new ValidationViolations();
		booleanConversionStrategy.validate("field", "1", violations);
		assertTrue(violations.hasErrors());
	}
}
