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
import com.hybris.services.entitlements.api.NotEmpty;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.conversion.DateTimeConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.NotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class ValidationExecutorTest
{
	public static final String ENTITLEMENT_TYPE = "Simple entitlement";

	@Autowired
	private ValidationExecutor validationExecutor;
	@Autowired
	DateTimeConverter dateTimeConverter;

	@Test
	public void testValidateRightCityGeoLocationForSingleCityCondition() throws IllegalAccessException
	{
		final ValidationViolations validationViolations = validationExecutor.validate(null);
		assertFalse(validationViolations.hasErrors());
	}

	@Test
	public void testString() throws IllegalAccessException
	{
		ValidationViolations validationViolations = validationExecutor.validate("123");
		assertFalse(validationViolations.hasErrors());
	}

	@Test
	public void testGrantValidator()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", "1");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		grantData1.addCondition(condition);
		final ValidationViolations errors = validationExecutor.validate(grantData1);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void testGrantValidatorList()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", "1");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		grantData1.addCondition(condition);

		final List<GrantData> grantDataList = new ArrayList<>();
		grantDataList.add(grantData1);
		grantDataList.add(grantData1);
		final ValidationViolations errors = validationExecutor.validate(grantDataList);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void testGrantValidatorMap()
	{
		final GrantData grantData1 = new GrantData();
		grantData1.setEntitlementType(ENTITLEMENT_TYPE);
		grantData1.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData1.setGrantSource("GrantSource");
		grantData1.setGrantSourceId("GrantSourceId");
		grantData1.setProperty("remainingQuantity", "1");
		final ConditionData condition = new ConditionData();
		condition.setType("string");
		grantData1.addCondition(condition);

		final Map<String, GrantData> grantDataList = new HashMap<>();
		grantDataList.put("1", grantData1);
		grantDataList.put("2", grantData1);
		final ValidationViolations errors = validationExecutor.validate(grantDataList);
		assertTrue(errors.hasErrors());
	}

	@Test
	public void shouldUseNotEmptyAnnotation()
	{
		final NotEmptyTestClass test = new NotEmptyTestClass();
		test.name = "";
		final ValidationViolations errors = validationExecutor.validate(test);
		assertTrue(errors.toString().contains("name=: Must not be empty"));
		test.name = " ";
		assertTrue(validationExecutor.validate(test).toString().isEmpty());
	}

	@Test
	/**
	 * Validator should silently skip fields that are not public and do not have public getters.
	 * Such fields are internal and are not intended for validation.
	 */
	public void shouldSkipInternalFields()
	{
		final ValidationViolations errors = validationExecutor.validate(new ClassWithSomeInternalFields());
		assertFalse(errors.hasErrors());
	}

	@Test
	/**
	 * If a non-public filed has public getter, validator should process the field.
	 */
	public void shouldValidatePrivateFieldsThroughGetters()
	{
		final GrantData object = new GrantData();
		object.setUserId("dzherebjatjew");
		final ValidationViolations errors = validationExecutor.validate(object);
		final String message = errors.toString();
		assertTrue(message.contains("grantSourceId=null"));
		assertFalse(message.contains("userId=null"));
	}

	public static class ClassWithSomeInternalFields
	{
		@NotNull
		private Boolean dirty;

	}

	public static class ClassWithPublicGetters extends ClassWithSomeInternalFields
	{
		@NotNull
		protected Integer count;

		public Integer getCount()
		{
			return count;
		}
	}

	public static class NotEmptyTestClass
	{
		@NotEmpty
		public String name;
	}
}
