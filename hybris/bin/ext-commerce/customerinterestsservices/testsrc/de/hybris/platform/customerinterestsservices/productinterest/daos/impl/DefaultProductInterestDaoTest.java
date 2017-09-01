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
package de.hybris.platform.customerinterestsservices.productinterest.daos.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.customerinterestsservices.productinterest.daos.ProductInterestDao;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 *
 */
@IntegrationTest
public class DefaultProductInterestDaoTest extends ServicelayerTransactionalTest
{
	@Resource
	private ProductInterestDao productInterestDao;

	@Resource
	private UserService userService;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource
	private ProductService productService;

	@Resource
	private ModelService modelService;

	@Before
	public void prepare() throws ImpExException
	{
		importCsv("/customerinterestsservices/test/impex/customerinterestsservices-test-data.impex", "utf-8");
	}

	@Test
	public void test_Find_Product_Interests_By_Customer()
	{
		final CustomerModel customer = (CustomerModel) userService.getUserForUID("dummyuser@dummy.com");
		final BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID("testSite");
		final BaseStoreModel baseStore = baseStoreService.getBaseStoreForUid("testStore1");

		final List<ProductInterestModel> productInterests = productInterestDao.findProductInterestsByCustomer(customer, baseStore,
				baseSite);

		Assert.assertEquals(7, productInterests.size());
	}

	@Test
	public void test_Find_Product_Interests()
	{
		final CustomerModel customer = (CustomerModel) userService.getUserForUID("dummyuser@dummy.com");
		final BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID("testSite");
		final BaseStoreModel baseStore = baseStoreService.getBaseStoreForUid("testStore1");
		final ProductModel product = productService.getProductForCode("product1");

		final Optional<ProductInterestModel> productInterests = productInterestDao.findProductInterest(product, customer,
				NotificationType.NOTIFICATION, baseStore, baseSite);

		Assert.assertTrue(productInterests.isPresent());
	}

	@Test
	public void test_Find_Expired_Product_Interests()
	{
		final List<ProductInterestModel> productInterests = productInterestDao.findExpiredProductInterests();

		Assert.assertEquals(1, productInterests.size());
	}

	@Test
	public void test_Find_Products_By_Customer_Interests()
	{
		PageableData pageableData = createPageableData(0, 5, "byNameAsc");
		final CustomerModel customer = (CustomerModel) userService.getUserForUID("dummyuser@dummy.com");
		final BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID("testSite");
		final BaseStoreModel baseStore = baseStoreService.getBaseStoreForUid("testStore1");

		Map<String, Map<String, String>> productPKMap = productInterestDao.findProductsByCustomerInterests(customer, baseStore,
				baseSite, pageableData);

		Assert.assertEquals(5, productPKMap.size());
		String firstProductPk = productPKMap.keySet().iterator().next();

		ProductModel firstProduct = (ProductModel) modelService.get(PK.parse(firstProductPk));
		Assert.assertEquals("testProduct1", firstProduct.getName());
	}

	@Test
	public void test_Find_Products_Count_By_Customer_Interests()
	{
		PageableData pageableData = createPageableData(0, 5, "byNameAsc");
		final CustomerModel customer = (CustomerModel) userService.getUserForUID("dummyuser@dummy.com");
		final BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID("testSite");
		final BaseStoreModel baseStore = baseStoreService.getBaseStoreForUid("testStore1");

		int count = productInterestDao.findProductsCountByCustomerInterests(customer, baseStore, baseSite, pageableData);

		Assert.assertEquals(7, count);
	}

	protected PageableData createPageableData(final int pageNumber, final int pageSize, final String sortCode)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setSort(sortCode);
		pageableData.setPageSize(pageSize);
		return pageableData;
	}


}
