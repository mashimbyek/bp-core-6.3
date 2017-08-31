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
package com.hybris.services.entitlements.sorting;

import static junit.framework.Assert.assertNotNull;

import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.service.GrantService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Testing basic functionality of metered condition strategy.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class DefaultGrantComparatorFactoryTest
{

	private static final String CONDTION_TYPE = "metered";
	@Autowired
//	@Qualifier("meteredConditionExecutor")
	private DefaultGrantComparatorFactory defaultGrantComparatorFactory;
	@Autowired
	private GrantService grantService;


	@Test(expected = ValidationException.class)
    public void testNonExisting()
    {
		defaultGrantComparatorFactory.getGrantComparator("non-existing");
    }

	@Test
	public void testNull()
	{
		assertNotNull(defaultGrantComparatorFactory.getGrantComparator(null));
	}

	@Test
	public void testMetered()
	{
		assertNotNull(defaultGrantComparatorFactory.getGrantComparator("default"));
	}
}
