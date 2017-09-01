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
package de.hybris.platform.customerinterestsfacades.productinterest.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorfacades.futurestock.FutureStockFacade;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.product.converters.populator.ProductPricePopulator;
import de.hybris.platform.commercefacades.product.converters.populator.ProductStockPopulator;
import de.hybris.platform.commercefacades.product.data.FutureStockData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerinterestsfacades.beans.ProductInterestsOfCustomer;
import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.customerinterestsservices.productinterest.ProductInterestService;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;



@UnitTest
public class DefaultProductInterestFacadeTest
{
	private DefaultProductInterestFacade productInterestFacade;
	private ProductInterestData productInterest;

	@Mock
	private ProductInterestService productInterestService;
	@Mock
	private Converter<ProductInterestModel, ProductInterestData> productInterestConverter;
	@Mock
	private Converter<ProductInterestData, ProductInterestModel> productInterestReverseConverter;
	@Mock
	private ProductService productService;
	@Mock
	private UserService userService;
	@Mock
	private BaseStoreService baseStoreService;
	@Mock
	private BaseSiteService baseSiteService;
	@Mock
	private ProductPricePopulator<ProductModel, ProductData> productPricePopulator;
	@Mock
	private ProductStockPopulator<ProductModel, ProductData> productStockPopulator;
	@Mock
	private FutureStockFacade futureStockFacade;
	@Mock
	private Converter<ProductModel, ProductData> productConverter;

	private ProductModel product;
	private BaseStoreModel baseStore;
	private CustomerModel customer;
	private BaseSiteModel baseSite;
	private ProductData productData;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		productInterest = new ProductInterestData();
		productInterest.setEmailAddress("test@gmail.com");
		productInterest.setEmailNotificationEnabled(true);
		productInterest.setMobileNumber("13578412453");
		productInterest.setSmsNotificationEnabled(true);
		productInterest.setNotificationType(NotificationType.NOTIFICATION);
		productData = new ProductData();

		productData.setCode("13810");
		productInterest.setProduct(productData);

		productInterestFacade = Mockito.spy(new DefaultProductInterestFacade());
		productInterestFacade.setBaseSiteService(baseSiteService);
		productInterestFacade.setBaseStoreService(baseStoreService);
		productInterestFacade.setProductInterestConverter(productInterestConverter);
		productInterestFacade.setProductInterestReverseConverter(productInterestReverseConverter);
		productInterestFacade.setProductInterestService(productInterestService);
		productInterestFacade.setUserService(userService);
		productInterestFacade.setProductService(productService);
		productInterestFacade.setProductConverter(productConverter);
		productInterestFacade.setProductPricePopulator(productPricePopulator);
		productInterestFacade.setProductStockPopulator(productStockPopulator);
		productInterestFacade.setFutureStockFacade(futureStockFacade);


		product = new ProductModel();
		product.setCode("13810");
		Mockito.when(productService.getProductForCode(Mockito.anyString())).thenReturn(product);

		baseStore = new BaseStoreModel();
		Mockito.when(baseStoreService.getCurrentBaseStore()).thenReturn(baseStore);

		customer = new CustomerModel();
		Mockito.when(userService.getCurrentUser()).thenReturn(customer);

		baseSite = new BaseSiteModel();
		Mockito.when(baseSiteService.getCurrentBaseSite()).thenReturn(baseSite);
	}

	@Test
	public void saveProductInterestTest()
	{
		final ProductInterestModel productInterestModel = new ProductInterestModel();
		productInterestModel.setEmailEnabled(Boolean.FALSE);
		productInterestModel.setSmsEnabled(Boolean.FALSE);

		Mockito.when(productInterestFacade.getProductInterest(productInterest.getProduct().getCode(),
				productInterest.getNotificationType())).thenReturn(Optional.of(productInterestModel));

		productInterestFacade.saveProductInterest(productInterest);

		Mockito.verify(productInterestFacade.getProductInterestReverseConverter(), Mockito.times(1)).convert(productInterest,
				productInterestModel);
		Mockito.verify(productInterestFacade.getProductInterestService(), Mockito.times(1))
				.saveProductInterest(productInterestModel);

	}

	@Test
	public void removeProductInterest()
	{
		final ProductInterestModel productInterestModel = new ProductInterestModel();
		productInterestModel.setEmailEnabled(Boolean.FALSE);
		productInterestModel.setSmsEnabled(Boolean.FALSE);

		Mockito.when(productInterestFacade.getProductInterest(productInterest.getProduct().getCode(),
				productInterest.getNotificationType())).thenReturn(Optional.of(productInterestModel));

		productInterestFacade.removeProductInterest(productInterest);

		Mockito.verify(productInterestFacade.getProductInterestService(), Mockito.times(1))
				.removeProductInterest(productInterestModel);
	}

	@Test
	public void getProductsByCustomerInterests()
	{
		final List<FutureStockData> futureStockData = new ArrayList<>();

		Mockito.doNothing().when(productPricePopulator).populate(Mockito.any(), Mockito.any());
		Mockito.doNothing().when(productStockPopulator).populate(Mockito.any(), Mockito.any());
		Mockito.when(futureStockFacade.getFutureAvailability("13810")).thenReturn(futureStockData);
		Mockito.when(productConverter.convert(Mockito.any())).thenReturn(productData);
		final PageableData pageableData = new PageableData();

		final Map<ProductModel, Map<NotificationType, Date>> productModelMap = new HashMap<>();

		final Map<NotificationType, Date> notificationTypeMap = new HashMap<>();

		final Date expectedDate = new Date(20000000000L);
		notificationTypeMap.put(NotificationType.NOTIFICATION, expectedDate);

		productModelMap.put(product, notificationTypeMap);

		Mockito.when(productInterestService.getProductsByCustomerInterests(pageableData)).thenReturn(productModelMap);

		final List<ProductInterestsOfCustomer> productInterestsOfCustomerList = productInterestFacade
				.getProductsByCustomerInterests(pageableData);

		Assert.assertEquals(1, productInterestsOfCustomerList.size());

		final ProductInterestsOfCustomer productInterestsOfCustomer = productInterestsOfCustomerList.get(0);
		final String actualCode = productInterestsOfCustomer.getProductData().getCode();
		final List<FutureStockData> actualFutureStockData = productInterestsOfCustomer.getProductData().getFutureStocks();

		final String actualType = productInterestsOfCustomer.getProductInterestDetail().get(0).getInterestType();
		final Date actualCreationTime = productInterestsOfCustomer.getProductInterestDetail().get(0).getDateAdded();

		Assert.assertEquals("13810", actualCode);
		Assert.assertEquals(futureStockData, actualFutureStockData);
		Assert.assertEquals(NotificationType.NOTIFICATION.getCode(), actualType);
		Assert.assertEquals(expectedDate, actualCreationTime);

	}

	@Test
	public void getProductsCountByCustomerInterests()
	{
		final PageableData pageableData = new PageableData();
		final int expectedCount = 10;
		Mockito.when(productInterestService.getProductsCountByCustomerInterests(pageableData)).thenReturn(expectedCount);

		final int actualCount = productInterestFacade.getProductsCountByCustomerInterests(pageableData);

		Assert.assertEquals(expectedCount, actualCount);
	}

}
