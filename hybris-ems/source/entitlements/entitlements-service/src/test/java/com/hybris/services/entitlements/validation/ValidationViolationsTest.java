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


import static junit.framework.Assert.assertEquals;

import com.hybris.services.entitlements.condition.geo.GeoConditionStrategy;
import com.hybris.services.entitlements.conversion.PropertiesToMapConverter;
import com.hybris.services.entitlements.service.GrantService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class ValidationViolationsTest
{
	private static final String CONDITION_TYPE = "geo";
	@Autowired
	@Qualifier("geoConditionExecutor")
	private GeoConditionStrategy geoConditionStrategy;
	@Autowired
	private GrantService grantService;

	@Test
	@Transactional
	public void testValidateRightCityGeoLocationForSingleCityCondition()
	{
		ValidationViolations validationViolations0 = new ValidationViolations();
		validationViolations0.pushNestedPath("properties1");
		assertEquals(validationViolations0.getPath(), "properties1");
		validationViolations0.add("field1", "value1", "message1");
		validationViolations0.pushNestedPath("properties2");
		assertEquals(validationViolations0.getPath(), "properties1.properties2");
		validationViolations0.add("field2", "value2", "message2");
		validationViolations0.pushNestedPath("properties3");
		assertEquals(validationViolations0.getPath(), "properties1.properties2.properties3");
		validationViolations0.add("field3", "value3", "message3");
		validationViolations0.popPath();
		assertEquals(validationViolations0.getPath(), "properties1.properties2");
		validationViolations0.add("field4", "value4", "message4");
		validationViolations0.popPath();
		assertEquals(validationViolations0.getPath(), "properties1");
		validationViolations0.add("field5", "value5", "message5");
		validationViolations0.popPath();
		assertEquals(validationViolations0.getPath(), "");
		validationViolations0.popPath();
		assertEquals(validationViolations0.getPath(), "");
		validationViolations0.add("field6", "value6", "message6");
		validationViolations0.add("", "value7", "message7");
		validationViolations0.add("message8");
		ValidationViolations validationViolations = new ValidationViolations();
		System.out.println(validationViolations.toString());
		validationViolations.addAll(validationViolations0);
		System.out.println(validationViolations.toString());
	}
}
