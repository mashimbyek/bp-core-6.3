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
package de.hybris.platform.customerinterestsfacades.productinterest.populators;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPricePopulator;
import de.hybris.platform.commercefacades.product.converters.populator.ProductStockPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.notificationservices.service.strategies.SmsNotificationChannelStrategy;


@UnitTest
public class ProductInterestPopulatorTest
{
	private ProductInterestPopulator productInterestPopulator;

	@Mock
	private SmsNotificationChannelStrategy smsNotificationChannelStrategy;
	@Mock
	private AbstractPopulatingConverter<ProductModel, ProductData> productConverter;
	@Mock
	private ProductData productData;
	@Mock
	private ProductPricePopulator<ProductModel, ProductData> productPricePopulator;
	@Mock
	private ProductStockPopulator<ProductModel, ProductData> productStockPopulator;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		productInterestPopulator = new ProductInterestPopulator();
		productInterestPopulator.setProductConverter(productConverter);
		productInterestPopulator.setSmsNotificationChannelStrategy(smsNotificationChannelStrategy);
		productInterestPopulator.setProductPricePopulator(productPricePopulator);
		productInterestPopulator.setProductStockPopulator(productStockPopulator);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPopulateProductInteresWithSourceNull()
	{
		final ProductInterestModel productInterestModel = null;
		final ProductInterestData productInterestData = new ProductInterestData();
		productInterestPopulator.populate(productInterestModel, productInterestData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPopulateProductInteresWithTargetNull()
	{
		final ProductInterestModel productInterestModel = new ProductInterestModel();
		final ProductInterestData productInterestData = null;
		productInterestPopulator.populate(productInterestModel, productInterestData);
	}

	@Test
	public void testPopulateProductInterest()
	{
		final Date date = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		final CustomerModel customer = new CustomerModel();
		customer.setUid("test@gmail.com");
		final ProductInterestModel source = new ProductInterestModel();
		source.setExpiryDate(date);
		source.setCustomer(customer);
		Mockito.when(smsNotificationChannelStrategy.getMobilePhoneNumber(customer)).thenReturn("15912930293");
		Mockito.doNothing().when(productPricePopulator).populate(Mockito.any(), Mockito.any());
		Mockito.doNothing().when(productStockPopulator).populate(Mockito.any(), Mockito.any());
		source.setEmailEnabled(Boolean.TRUE);
		source.setSmsEnabled(Boolean.FALSE);
		source.setNotificationType(NotificationType.NOTIFICATION);
		final ProductModel productModel = new ProductModel();
		source.setProduct(productModel);

		Mockito.when(productConverter.convert(Mockito.any())).thenReturn(productData);

		final ProductInterestData target = new ProductInterestData();

		productInterestPopulator.populate(source, target);

		assertEquals("test@gmail.com", target.getEmailAddress());
		assertEquals(date, target.getExpiryDate());
		assertEquals("15912930293", target.getMobileNumber());
		assertEquals(NotificationType.NOTIFICATION, target.getNotificationType());
		assertTrue(target.isEmailNotificationEnabled());
		assertFalse(target.isSmsNotificationEnabled());
		assertEquals(productData, target.getProduct());
	}
}
