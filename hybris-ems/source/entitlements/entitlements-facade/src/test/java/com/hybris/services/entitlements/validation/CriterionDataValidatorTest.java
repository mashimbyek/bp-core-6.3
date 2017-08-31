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

import com.hybris.services.entitlements.condition.CriterionData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class CriterionDataValidatorTest
{
	@Autowired
//	@Qualifier("criterionDataValidator")
	private CriterionDataValidator criterionDataValidator;

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWrongValue()
	{
//		final CriterionDataValidator criterionDataValidator = (CriterionDataValidator) context.getBean(CriterionDataValidator.class);
		ValidationViolations validationViolations = new ValidationViolations();
		CriterionData criterionData = new CriterionData();
//		criterionDataValidator.validate(criterionData, validationViolations);
		criterionDataValidator.validate("Wrong Value", validationViolations);
//		assertTrue(validationViolations.hasErrors());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateNull()
	{
//		final CriterionDataValidator criterionDataValidator = (CriterionDataValidator) context.getBean(CriterionDataValidator.class);
		ValidationViolations validationViolations = new ValidationViolations();
		CriterionData criterionData = new CriterionData();
//		criterionDataValidator.validate(criterionData, validationViolations);
		criterionDataValidator.validate(null, validationViolations);
//		assertTrue(validationViolations.hasErrors());
	}

	@Test
	public void testValidateCriterionData()
	{
//		final CriterionDataValidator criterionDataValidator = (CriterionDataValidator) context.getBean(CriterionDataValidator.class);
		ValidationViolations validationViolations = new ValidationViolations();
		CriterionData criterionData = new CriterionData();
		criterionData.setType("metered");
		criterionDataValidator.validate(criterionData, validationViolations);
//		criterionDataValidator.validate("Wrong Value", validationViolations);
//		assertTrue(validationViolations.hasErrors());
	}

	@Test
	public void testValidateCriterionDataUnknownType()
	{
//		final CriterionDataValidator criterionDataValidator = (CriterionDataValidator) context.getBean(CriterionDataValidator.class);
		ValidationViolations validationViolations = new ValidationViolations();
		CriterionData criterionData = new CriterionData();
		criterionData.setType("UnknownType");
		criterionDataValidator.validate(criterionData, validationViolations);
//		criterionDataValidator.validate("Wrong Value", validationViolations);
//		assertTrue(validationViolations.hasErrors());
	}

	@Test
	public void test()
	{
//		final CriterionDataValidator criterionDataValidator = (CriterionDataValidator) context.getBean(CriterionDataValidator.class);
		ValidationViolations validationViolations = new ValidationViolations();
		CriterionData criterionData = new CriterionData();
		criterionData.setType("UnknownType");
		assertTrue(criterionDataValidator.supportsClass(CriterionData.class));
		assertFalse(criterionDataValidator.supportsClass(String.class));
//		criterionDataValidator.validate("Wrong Value", validationViolations);
//		assertTrue(validationViolations.hasErrors());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSupportsClassNull()
	{
		assertFalse(criterionDataValidator.supportsClass(null));
	}

//	@Test(expected = ValidationException.class)
//	public void testNonString()
//	{
//		PositiveIntegerConversionStrategy positiveIntegerConversionStrategy = new PositiveIntegerConversionStrategy();
//		positiveIntegerConversionStrategy.convert(new Integer(1));
//	}
//
//	@Test(expected = ValidationException.class)
//	public void testNull()
//	{
//		PositiveIntegerConversionStrategy positiveIntegerConversionStrategy = new PositiveIntegerConversionStrategy();
//		positiveIntegerConversionStrategy.convert(null);
//	}
//
//	@Test//(expected = ValidationException.class)
//	public void testOne()
//	{
//		PositiveIntegerConversionStrategy positiveIntegerConversionStrategy = new PositiveIntegerConversionStrategy();
//		positiveIntegerConversionStrategy.convert("1");
//	}
//
//	@Test//(expected = ValidationException.class)
//	public void testMoreThanOne()
//	{
//		PositiveIntegerConversionStrategy positiveIntegerConversionStrategy = new PositiveIntegerConversionStrategy();
//		positiveIntegerConversionStrategy.convert("2");
//	}
//
//	@Test(expected = ValidationException.class)
//	public void testNegative()
//	{
//		PositiveIntegerConversionStrategy positiveIntegerConversionStrategy = new PositiveIntegerConversionStrategy();
//		positiveIntegerConversionStrategy.convert("-1");
//	}
}
