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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;


@UnitTest
public class ProductInterestReversePopulatorTest
{
	private ProductInterestReversePopulator productInterestReversePopulator;
	@Mock
	private ProductService productService;
	@Mock
	private UserService userService;
	@Mock
	private BaseStoreService baseStoreService;
	@Mock
	private BaseSiteService baseSiteService;

	private final String expiryDay = "customerinterestsservices.expiryDay";

	private ProductInterestData testProductInterestData;


	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		productInterestReversePopulator = new ProductInterestReversePopulator();
		productInterestReversePopulator.setProductService(productService);
		productInterestReversePopulator.setUserService(userService);
		productInterestReversePopulator.setBaseStoreService(baseStoreService);
		productInterestReversePopulator.setBaseSiteService(baseSiteService);

		testProductInterestData = new ProductInterestData();
		testProductInterestData.setEmailAddress("test@gmail.com");
		testProductInterestData.setEmailNotificationEnabled(true);
		testProductInterestData.setMobileNumber("13845714267");
		testProductInterestData.setSmsNotificationEnabled(false);
		testProductInterestData.setNotificationType(NotificationType.NOTIFICATION);
		final ProductData productData = new ProductData();
		productData.setCode("138427");
		testProductInterestData.setProduct(productData);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testReversePopulateProductInteresWithSourceNull()
	{
		final ProductInterestModel productInterestModel = new ProductInterestModel();
		final ProductInterestData productInterestData = null;
		productInterestReversePopulator.populate(productInterestData, productInterestModel);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReversePopulateProductInteresWithTargetNull()
	{
		final ProductInterestModel productInterestModel = null;
		final ProductInterestData productInterestData = new ProductInterestData();
		productInterestReversePopulator.populate(productInterestData, productInterestModel);
	}

	@Test
	public void testPopulateWithExistTarget()
	{
		final ProductInterestModel target = new ProductInterestModel();
		final LocalDate expiryDate = LocalDate.now().plusDays(90);
		final Date date = Date.from(expiryDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		target.setExpiryDate(date);

		productInterestReversePopulator.populate(testProductInterestData, target);

		assertEquals(NotificationType.NOTIFICATION, target.getNotificationType());
		assertEquals(true, target.getEmailEnabled());
		assertEquals(false, target.getSmsEnabled());

	}
}
