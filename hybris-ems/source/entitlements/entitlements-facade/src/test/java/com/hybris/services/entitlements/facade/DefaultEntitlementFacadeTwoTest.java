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
package com.hybris.services.entitlements.facade;


import com.hybris.kernel.api.PersistenceManager;
import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.api.ExecuteManyData;
import com.hybris.services.entitlements.api.GrantData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.condition.string.StringConditionStrategy;
import com.hybris.services.entitlements.conversion.DateTimeConverter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-facade-spring.xml")
public class DefaultEntitlementFacadeTwoTest
{
	public static final String ENTITLEMENT_TYPE = "Simple entitlement";

	@Autowired
	DateTimeConverter dateTimeConverter;
	@Autowired
	PersistenceManager persistenceManager;
	@Autowired
	@Qualifier("stringConditionExecutor")
	StringConditionStrategy stringConditionStrategy;
	@Autowired
	ApplicationContext context;
	@Autowired
	@Qualifier("defaultEntitlementFacade")
	EntitlementFacade facade;

	private String userId;

	@Before
	public void onInit()
	{
		userId = UUID.randomUUID().toString();
	}

	@Test
	public void createGrantIdIsNotNull() throws ParseException
	{
		final GrantData grantData = new GrantData();
		grantData.setUserId(userId);
		grantData.setEntitlementType(ENTITLEMENT_TYPE);
		grantData.setGrantTime(dateTimeConverter.convertDateToString(new Date()));
		grantData.setGrantSource("order");
		grantData.setGrantSourceId("order#1");

		final GrantData result = facade.createGrant(grantData);
		Assert.notNull(result.getId());
	}

	@Test(expected = ValidationException.class)
	public void createGrantConversionException() throws ParseException
	{
		final GrantData grantData = new GrantData();
		grantData.setUserId(userId);
		grantData.setEntitlementType(ENTITLEMENT_TYPE);
		grantData.setGrantTime("An invalid date");
		grantData.setGrantSource("order");
		grantData.setGrantSourceId("order#1");

		facade.createGrant(grantData);
	}

	@Test
	public void shouldValidateMultiCheck()
	{
		final List<ExecuteManyData> data = new ArrayList<>();
		final ExecuteManyData first = new ExecuteManyData();
		first.setEntitlementType("SMS");
		final List<CriterionData> firstCriteria = new ArrayList<>();
		final CriterionData cr = new CriterionData();
		cr.setType("string");
		firstCriteria.add(cr);
		first.setCriterionDataList(firstCriteria);
		data.add(first);
		final ExecuteManyData second = new ExecuteManyData();
		second.setEntitlementType("video");
		data.add(second);
		final ExecuteManyData third = new ExecuteManyData();
		third.setEntitlementType("audio");
		third.setCriterionDataList(Arrays.asList(new CriterionData()));
		data.add(third);
		try
		{
			facade.execute(Actions.CHECK, userId, data, false);
		}
		catch (final ValidationException e)
		{
			final String message = e.getMessage();
			Assert.hasText(message);
			Assert.isTrue(message.contains("[0].criterionDataList[0].string=null"));
			Assert.isTrue(!message.contains("[1].criterionDataList=null"));
			Assert.isTrue(message.contains("[2].criterionDataList[0].type=null"));
		}
	}
}

