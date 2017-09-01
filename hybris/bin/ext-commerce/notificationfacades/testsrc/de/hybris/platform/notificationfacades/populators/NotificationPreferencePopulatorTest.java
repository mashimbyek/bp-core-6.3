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
package de.hybris.platform.notificationfacades.populators;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.util.ConverterFactory;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.notificationfacades.data.NotificationPreferenceData;
import de.hybris.platform.notificationservices.service.strategies.SmsNotificationChannelStrategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@UnitTest
public class NotificationPreferencePopulatorTest
{
	private static String TEST_EMAIL_ADDRESS = "testpopulator@gmail.com";
	private static String TEST_MOBILE_NUMBER = "15998983456";
	private AbstractPopulatingConverter<CustomerModel, NotificationPreferenceData> notificationPreferenceConverter;
	private NotificationPreferencePopulator notificationPreferencePopulator;

	@Mock
	private SmsNotificationChannelStrategy smsNotificationChannelStrategy;
	@Mock
	CustomerModel customerModel;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		notificationPreferencePopulator = new NotificationPreferencePopulator();
		notificationPreferenceConverter = new ConverterFactory<CustomerModel, NotificationPreferenceData, NotificationPreferencePopulator>()
				.create(NotificationPreferenceData.class, notificationPreferencePopulator);

		notificationPreferencePopulator.setSmsNotificationChannelStrategy(smsNotificationChannelStrategy);

	}

	@Test
	public void testGetNotificationPreferenceWithEmailAddressAndMobileNumber()
	{
		given(customerModel.getUid()).willReturn(TEST_EMAIL_ADDRESS);
		given(smsNotificationChannelStrategy.getMobilePhoneNumber(customerModel)).willReturn(TEST_MOBILE_NUMBER);

		final NotificationPreferenceData notificationPreferenceData = notificationPreferenceConverter.convert(customerModel);
		Assert.assertEquals(TEST_EMAIL_ADDRESS, notificationPreferenceData.getEmailAddress());
		Assert.assertEquals(TEST_MOBILE_NUMBER, notificationPreferenceData.getMobileNumber());
	}
}
