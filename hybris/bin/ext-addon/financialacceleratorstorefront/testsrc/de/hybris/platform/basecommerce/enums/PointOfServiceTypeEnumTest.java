/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package de.hybris.platform.basecommerce.enums;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Assert;
import org.junit.Test;


/**
 * JUnit test suite for {@link PointOfServiceTypeEnum}
 */

@UnitTest
public class PointOfServiceTypeEnumTest
{
	private final String AGENCY_STRING = "AGENCY";

	@Test
	public void testAgencyExists()
	{
		final PointOfServiceTypeEnum agency = PointOfServiceTypeEnum.valueOf(AGENCY_STRING);
		Assert.assertNotNull(agency);
	}
}
