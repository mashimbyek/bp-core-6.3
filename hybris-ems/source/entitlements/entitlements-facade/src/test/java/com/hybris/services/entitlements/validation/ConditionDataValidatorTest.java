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

import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.conversion.DateTimeConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class ConditionDataValidatorTest
{
	@Autowired
	DateTimeConverter dateTimeConverter;

	@Autowired
	private ConditionDataValidator conditionDataValidator;

	@Test
	public void testGrantValidatorList()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		final ValidationViolations validationViolations = new ValidationViolations();
		conditionDataValidator.validate(condition, validationViolations);
		assertTrue(validationViolations.hasErrors());
	}

	@Test
	public void testValidateRightCityGeoLocationForSingleCityCondition() throws IllegalAccessException
	{
		final ValidationViolations validationViolations = new ValidationViolations();
		conditionDataValidator.validate(null, validationViolations);
		assertFalse(validationViolations.hasErrors());
	}

	@Test
	public void testGrantValidatorList2()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("non-exist");
		final ValidationViolations validationViolations = new ValidationViolations();
		conditionDataValidator.validate(condition, validationViolations);
		assertTrue(validationViolations.hasErrors());
	}

	@Test
	public void shouldRejectNullPropertyName()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("string", "1");
		condition.setProperty(null, "test");
		final ValidationViolations validationViolations = new ValidationViolations();
		conditionDataValidator.validate(condition, validationViolations);
		assertTrue(validationViolations.hasErrors());
		final String errors = validationViolations.toString();
		assertTrue(errors.contains("<null>=test: Property name must not be null"));
	}

	@Test
	public void shouldRejectNullPropertyValue()
	{
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		condition.setProperty("string", "1");
		condition.setProperty("test", null);
		final ValidationViolations validationViolations = new ValidationViolations();
		conditionDataValidator.validate(condition, validationViolations);
		assertFalse(validationViolations.hasErrors());
		final String errors = validationViolations.toString();
		assertFalse(errors.contains("test=null: Must not be null"));
	}
}
