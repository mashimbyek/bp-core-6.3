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
package com.hybris.services.entitlements.condition.geo;

import static com.hybris.services.entitlements.validation.GeoPathConversionStrategy.GEO_PATH;
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
public class GeoConditionValidatorTest
{


	@Autowired
	@Qualifier("geoConditionValidator")
	GeoConditionStrategy validator;

	@Test
	public void shouldValidateGeoPathParameter()
	{
		final Map<String, String> map = new HashMap<>();
		map.put(GEO_PATH, "Germany/Bavaria/Munich");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateExecutionParameters(map, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void shouldRejectMultiGeoPathParameter()
	{
		final Map<String, String> map = new HashMap<>();
		map.put(GEO_PATH, "Germany/Bavaria/Munich, Germany/Bremen, USA");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateExecutionParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldRejectParameter()
	{
		final Map<String, String> map = new HashMap<>();
		final ValidationViolations errors = new ValidationViolations();
		validator.validateExecutionParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldValidateConditionParameters()
	{
		final Map<String, String> map = new HashMap<>();
		map.put(GEO_PATH, "Germany/Bavaria/Munich, Germany/Bremen/Bremen, USA/California/San Francisco");
		final ValidationViolations errors = new ValidationViolations();
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
    public void shouldRejectConditionParameterNoCountry()
    {
        final Map<String,String> map = new HashMap<>();
        map.put(GEO_PATH, "/Bavaria/Munich");
        final ValidationViolations errors = new ValidationViolations();
        validator.validateGrantParameters(map, errors);
        assertTrue(errors.hasErrors());
    }

	@Test
	public void shouldRejectConditionParameterNoRegion()
	{
		final Map<String,String> map = new HashMap<>();
        map.put(GEO_PATH, "Germany//Munich");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateGrantParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

    @Test
    public void shouldRejectConditionParameterNoCountryAndRegion()
    {
        final Map<String,String> map = new HashMap<>();
        map.put(GEO_PATH, "//Munich");
        final ValidationViolations errors = new ValidationViolations();
        validator.validateGrantParameters(map, errors);
        assertTrue(errors.hasErrors());
    }

	@Test
	public void shouldRejectLongGeoPath()
	{
		final Map<String,String> map = new HashMap<>();
		map.put(GEO_PATH, "/Germany/Bavaria/Munich/USA");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateGrantParameters(map, errors);
		assertTrue(errors.hasErrors());
	}

    @Test
    public void shouldRejectConditionParameterNoCity()
    {
        final Map<String,String> map = new HashMap<>();
        map.put(GEO_PATH, "Germany/Bavaria/");
        final ValidationViolations errors = new ValidationViolations();
        validator.validateGrantParameters(map, errors);
        assertTrue(errors.hasErrors());
    }

	@Test
	public void shouldValidateMultiSymbolGeoPath()
	{
		final Map<String,String> map = new HashMap<>();
		map.put(GEO_PATH, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890/abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890/abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890");
		final ValidationViolations errors = new ValidationViolations();
		validator.validateGrantParameters(map, errors);
		assertFalse(errors.hasErrors());
	}

    @Test
    public void shouldValidateNullGeoPath()
    {
        final Map<String,String> map = new HashMap<>();
        map.put(GEO_PATH, null);
        final ValidationViolations errors = new ValidationViolations();
        validator.validateGrantParameters(map, errors);
        assertTrue(errors.hasErrors());
    }
}
