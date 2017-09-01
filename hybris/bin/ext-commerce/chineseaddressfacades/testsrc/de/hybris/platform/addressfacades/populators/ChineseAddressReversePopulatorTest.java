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
package de.hybris.platform.addressfacades.populators;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.AddressModel;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;


@UnitTest
public class ChineseAddressReversePopulatorTest
{
	private static String FIRST_NAME = "Jenny";
	private static String LAST_NAME = "Xu";
	private static String FULL_NAME = "Jenny Xu";
	private ChineseAddressReversePopulator chineseAddressReversePopulator;

	@Before
	public void setUp()
	{
		chineseAddressReversePopulator = new ChineseAddressReversePopulator();
	}

	@Test
	public void testFillInFirstAndLastNameWithFullName()
	{
		final AddressModel addressModel = new AddressModel();
		addressModel.setFullname(FULL_NAME);
		chineseAddressReversePopulator.fillInFirstAndLastName(addressModel);

		Assert.assertEquals(FULL_NAME, addressModel.getFirstname());
		Assert.assertEquals(StringUtils.EMPTY, addressModel.getLastname());
	}

	@Test
	public void testFillInFirstAndLastNameWithNullFullName()
	{
		final AddressModel addressModel = new AddressModel();
		addressModel.setFirstname(FIRST_NAME);
		addressModel.setLastname(LAST_NAME);
		chineseAddressReversePopulator.fillInFirstAndLastName(addressModel);

		Assert.assertEquals(FIRST_NAME, addressModel.getFirstname());
		Assert.assertEquals(LAST_NAME, addressModel.getLastname());
	}

}
