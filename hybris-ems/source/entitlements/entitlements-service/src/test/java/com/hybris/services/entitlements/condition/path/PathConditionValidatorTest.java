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
package com.hybris.services.entitlements.condition.path;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class PathConditionValidatorTest
{
	@Autowired
	@Qualifier("pathConditionValidator")
	PathConditionStrategy validator;

	@Test
	public void shouldValidateFileParameter()
	{
		final Map<String,String> map = new HashMap<>();
		map.put(PathConditionStrategy.EXECUTION_PARAMETER_FILE, "/tv/serials/breaking-bad");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateExecutionParameters(map, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldRejectFileParameter()
	{
		final Map<String,String> map = new HashMap<>();
		final ValidationViolations errors = new ValidationViolations();
		validator.validateExecutionParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldValidateConditionParameters()
	{
		final Map<String,String> map = new HashMap<>();
		map.put(PathConditionStrategy.GRANT_PARAMETER_PATH, "/tv/serials");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateGrantParameters(map, errors);
		assertFalse(errors.hasErrors());
		map.put(PathConditionStrategy.GRANT_PARAMETER_CASE_INSENSITIVE, Boolean.toString(true));
		validator.validateGrantParameters(map, errors);
		assertFalse(errors.hasErrors());
		map.put(PathConditionStrategy.GRANT_PARAMETER_SEPARATOR, "-");
		validator.validateGrantParameters(map, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldRejectConditionParameter()
	{
		final Map<String,String> map = new HashMap<>();
		final ValidationViolations errors = new ValidationViolations();
		validator.validateGrantParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldRejectInvalidDirectory()
	{
		final Map<String,String> map = new HashMap<>();
		map.put(PathConditionStrategy.GRANT_PARAMETER_PATH, "/tv/serials/");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateGrantParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldRejectInvalidNoCase()
	{
		final Map<String,String> map = new HashMap<>();
		final ValidationViolations errors = new ValidationViolations();
		map.put(PathConditionStrategy.GRANT_PARAMETER_PATH, "/tv/serials");
		map.put(PathConditionStrategy.GRANT_PARAMETER_CASE_INSENSITIVE, "x");
		validator.validateGrantParameters(map, errors);
		assertTrue(errors.hasErrors());
	}
}
