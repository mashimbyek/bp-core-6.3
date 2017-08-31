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
package com.hybris.services.entitlements.service.impl;

import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.sorting.GrantComparatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class DefaultGrantComparatorFactoryTest
{
	@Autowired
	private GrantComparatorFactory factory;

	@Test(expected = ValidationException.class)
	public void shouldReportOnUnknownComparator()
	{
		factory.getGrantComparator("something");
	}
}
