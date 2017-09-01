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

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.customerinterestsservices.productinterest.daos.ProductInterestDao;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


public class DefaultProductInterestDao extends DefaultGenericDao<ProductInterestModel> implements ProductInterestDao
{
	private PagedFlexibleSearchService pagedFlexibleSearchService;

	public DefaultProductInterestDao()
	{
		super(ProductInterestModel._TYPECODE);
	}

	@Override
	public List<ProductInterestModel> findProductInterestsByCustomer(final CustomerModel customerModel,
			final BaseStoreModel baseStore, final BaseSiteModel baseSite)
	{
		final String fsq = "SELECT {" + ProductInterestModel.PK + "} FROM {" + ProductInterestModel._TYPECODE + "} WHERE {"
				+ ProductInterestModel.CUSTOMER + "} = ?customer AND {" + ProductInterestModel.BASESTORE + "} = ?baseStore AND {"
				+ ProductInterestModel.BASESITE + "} = ?baseSite AND {" + ProductInterestModel.EXPIRYDATE + "} > ?today";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
		query.addQueryParameter("customer", customerModel);
		query.addQueryParameter("baseStore", baseStore);
		query.addQueryParameter("baseSite", baseSite);
		query.addQueryParameter("today", new Date());
		final SearchResult<ProductInterestModel> result = getFlexibleSearchService().search(query);
		final List<ProductInterestModel> productInterestModels = result.getResult();
		if (productInterestModels != null && productInterestModels.size() > 0)
		{
			return productInterestModels;
		}
		return Collections.emptyList();
	}

	@Override
	public Optional<ProductInterestModel> findProductInterest(final ProductModel productModel, final CustomerModel customerModel,
			final NotificationType notificationType, final BaseStoreModel baseStore, final BaseSiteModel baseSite)
	{
		final String fsq = "SELECT {" + ProductInterestModel.PK + "} FROM {" + ProductInterestModel._TYPECODE + "} WHERE {"
				+ ProductInterestModel.CUSTOMER + "} = ?customer AND {" + ProductInterestModel.PRODUCT + "} = ?product AND {"
				+ ProductInterestModel.NOTIFICATIONTYPE + "} in ({{ select {pk} from {" + NotificationType._TYPECODE
				+ "} where {code}=?notificationType }}) AND {" + ProductInterestModel.BASESTORE + "} = ?baseStore AND {"
				+ ProductInterestModel.BASESITE + "} = ?baseSite AND {" + ProductInterestModel.EXPIRYDATE + "} > ?today";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
		query.addQueryParameter("customer", customerModel);
		query.addQueryParameter("product", productModel);
		query.addQueryParameter("notificationType", notificationType.name());
		query.addQueryParameter("baseStore", baseStore);
		query.addQueryParameter("baseSite", baseSite);
		query.addQueryParameter("today", new Date());
		final SearchResult<ProductInterestModel> result = getFlexibleSearchService().search(query);
		final List<ProductInterestModel> productInterestModels = result.getResult();
		Assert.isTrue(productInterestModels.size() <= 1);
		if (productInterestModels.size() > 0)
		{
			return Optional.of(productInterestModels.get(0));
		}
		return Optional.empty();
	}

	@Override
	public List<ProductInterestModel> findExpiredProductInterests()
	{
		final String fsq = "SELECT {" + ProductInterestModel.PK + "} FROM {" + ProductInterestModel._TYPECODE + "} WHERE {"
				+ ProductInterestModel.EXPIRYDATE + "} < ?today";
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
		query.addQueryParameter("today", new Date());
		final SearchResult<ProductInterestModel> result = getFlexibleSearchService().search(query);
		final List<ProductInterestModel> productInterestModels = result.getResult();
		if (productInterestModels != null && productInterestModels.size() > 0)
		{
			return productInterestModels;
		}
		return Collections.emptyList();
	}

	@Override
	public Map<String, Map<String, String>> findProductsByCustomerInterests(CustomerModel customerModel,
			BaseStoreModel baseStore, BaseSiteModel baseSite, PageableData pageableData)
	{
		final FlexibleSearchQuery query = buildPagedProductInterestsQuery(customerModel, baseStore, baseSite, pageableData);
		final SearchResult<List<String>> result = getFlexibleSearchService().search(query);


		final List<List<String>> resultList = result.getResult();

		if (resultList == null || resultList.size() == 0)
		{

			return Collections.emptyMap();
		}
		Map<String, Map<String, String>> productPKMap = new LinkedHashMap<>();
		resultList.stream().forEach(productNotification -> {
			fillProductPKMap(productPKMap, productNotification);
		});

		return productPKMap;
	}

	@Override
	public int findProductsCountByCustomerInterests(CustomerModel customerModel, BaseStoreModel baseStore, BaseSiteModel baseSite,
			PageableData pageableData)
	{
		final FlexibleSearchQuery query = buildPagedProductInterestsQuery(customerModel, baseStore, baseSite, pageableData);
		final SearchResult<List<String>> result = getFlexibleSearchService().search(query);
		return result.getTotalCount();
	}

	protected FlexibleSearchQuery buildPagedProductInterestsQuery(CustomerModel customerModel, BaseStoreModel baseStore,
			BaseSiteModel baseSite, PageableData pageableData)
	{
		String fsq = "SELECT {PI:" + ProductInterestModel.PRODUCT + "}" + ",GROUP_CONCAT({PI:"
				+ ProductInterestModel.NOTIFICATIONTYPE + "}),GROUP_CONCAT({PI:" + ProductInterestModel.CREATIONTIME
				+ "}),GROUP_CONCAT({P:" + ProductModel.NAME + "}) AS NAME FROM {" + ProductInterestModel._TYPECODE + " as PI},{"
				+ ProductModel._TYPECODE + " as P}" + " WHERE" + " {P:" + ProductModel.PK + "} = {PI:" + ProductInterestModel.PRODUCT
				+ "} AND {" + ProductInterestModel.CUSTOMER + "} = ?customer AND {" + ProductInterestModel.BASESTORE
				+ "} = ?baseStore AND {" + ProductInterestModel.BASESITE + "} = ?baseSite AND {" + ProductInterestModel.EXPIRYDATE
				+ "} > ?today" + " GROUP BY {PI:" + ProductInterestModel.PRODUCT + "}";

		String sort = pageableData.getSort();
		String orderByString = getOrderByString(sort);
		fsq = fsq + orderByString;
		final FlexibleSearchQuery query = new FlexibleSearchQuery(fsq);
		query.addQueryParameter("customer", customerModel);
		query.addQueryParameter("baseStore", baseStore);
		query.addQueryParameter("baseSite", baseSite);
		query.addQueryParameter("today", new Date());
		query.setNeedTotal(true);
		query.setStart(pageableData.getCurrentPage() * pageableData.getPageSize());
		query.setCount(pageableData.getPageSize());


		query.setResultClassList(Arrays.asList(String.class, String.class, String.class));
		return query;
	}

	protected String getOrderByString(String sort)
	{
		String result = StringUtils.EMPTY;
		switch (sort.trim())
		{
			case "byNameAsc":
				result = " ORDER BY NAME ASC";
				break;
			case "byNameDesc":
				result = " ORDER BY NAME DESC";
				break;
		}
		return result;

	}

	protected void fillProductPKMap(Map<String, Map<String, String>> productNotificationTypesMap, List<String> productNotification)
	{
		String productPK = productNotification.get(0);
		String notificationCollection = productNotification.get(1);
		String creationTimeCollection = productNotification.get(2);
		List<String> notificationTypeList = Arrays.asList(notificationCollection.split(","));
		List<String> creationTimeList = Arrays.asList(creationTimeCollection.split(","));
		Map<String, String> interestCreationMap = new LinkedHashMap<>();

		for (int i = 0; i < notificationTypeList.size(); i++)
		{
			interestCreationMap.put(notificationTypeList.get(i), creationTimeList.get(i));

		}
		productNotificationTypesMap.put(productPK, interestCreationMap);
	}

	protected PagedFlexibleSearchService getPagedFlexibleSearchService()
	{
		return pagedFlexibleSearchService;
	}

	@Required
	public void setPagedFlexibleSearchService(final PagedFlexibleSearchService pagedFlexibleSearchService)
	{
		this.pagedFlexibleSearchService = pagedFlexibleSearchService;
	}

}
