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
package de.hybris.platform.chineseprofilefacades.customer.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 *
 */
@UnitTest
public class ChineseCustomerFacadeUnitTest
{
	private static final String ISO_CODE_EN = "en";

	private static final String ISO_CODE_ZH = "zh";

	@Mock
	private UserService userService;

	private CustomerModel customer;

	private ChineseCustomerFacade customerFacade;

	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);

		customer = new CustomerModel();
		customer.setEmailLanguage(ISO_CODE_ZH);

		customerFacade = new ChineseCustomerFacade();
		customerFacade.setUserService(userService);
	}

	@Test
	public void testSaveEmailLanguageForNullUser()
	{
		customerFacade.saveEmailLanguageForCurrentUser(ISO_CODE_EN);
		verify(userService, times(0)).isAnonymousUser(customer);
	}

	@Test
	public void testSaveEmailLanguageForCurrentUser()
	{
		Mockito.doReturn(customer).when(userService).getCurrentUser();
		Mockito.doReturn(false).when(userService).isAnonymousUser(Mockito.any());
		customerFacade.saveEmailLanguageForCurrentUser(ISO_CODE_EN);
		Assert.assertEquals(customer.getEmailLanguage(), ISO_CODE_EN);
	}

	@Test
	public void testSaveEmailLanguageForAnonymousUser()
	{
		Mockito.doReturn(customer).when(userService).getCurrentUser();
		Mockito.doReturn(true).when(userService).isAnonymousUser(Mockito.any());
		customerFacade.saveEmailLanguageForCurrentUser(ISO_CODE_EN);
		Assert.assertEquals(customer.getEmailLanguage(), ISO_CODE_ZH);
	}
}
