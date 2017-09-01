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

import de.hybris.platform.acceleratorfacades.futurestock.FutureStockFacade;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerinterestsfacades.beans.ProductInterestDetail;
import de.hybris.platform.customerinterestsfacades.beans.ProductInterestsOfCustomer;
import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsfacades.productinterest.ProductInterestFacade;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.customerinterestsservices.productinterest.ProductInterestService;
import de.hybris.platform.notificationservices.enums.NotificationType;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;


public class DefaultProductInterestFacade implements ProductInterestFacade
{
	private ProductInterestService productInterestService;
	private Converter<ProductInterestModel, ProductInterestData> productInterestConverter;
	private Converter<ProductInterestData, ProductInterestModel> productInterestReverseConverter;
	private ProductService productService;
	private UserService userService;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private Converter<ProductModel, ProductData> productConverter;
	private Populator<ProductModel, ProductData> productPricePopulator;
	private Populator<ProductModel, ProductData> productStockPopulator;
	private FutureStockFacade futureStockFacade;

	@Override
	public void saveProductInterest(final ProductInterestData productInterest)
	{
		final ProductInterestModel modifiedproductInterest = getProductInterest(productInterest.getProduct().getCode(),
				productInterest.getNotificationType()).orElse(new ProductInterestModel());
		getProductInterestReverseConverter().convert(productInterest, modifiedproductInterest);
		getProductInterestService().saveProductInterest(modifiedproductInterest);
	}

	@Override
	public void removeProductInterest(final ProductInterestData productInterest)
	{
		getProductInterest(productInterest.getProduct().getCode(), productInterest.getNotificationType())
				.ifPresent(x -> getProductInterestService().removeProductInterest(x));
	}

	@Override
	public Optional<ProductInterestData> getProductInterestDataForCurrentCustomer(final String productcode,
			final NotificationType notificationType)
	{
		return getProductInterest(productcode, notificationType)
				.map(productInterestsInterestModel -> getProductInterestConverter().convert(productInterestsInterestModel));
	}

	@Override
	public void removeProductInterestByProduct(final String productCode)
	{
		getProductInterestService().removeProductInterestByProduct(productCode);
	}

	protected Optional<ProductInterestModel> getProductInterest(final String productcode, final NotificationType notificationType)
	{
		final ProductModel product = getProductService().getProductForCode(productcode);
		final BaseStoreModel baseStore = getBaseStoreService().getCurrentBaseStore();
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();
		final BaseSiteModel baseSite = getBaseSiteService().getCurrentBaseSite();
		return getProductInterestService().getProductInterest(product, customer, notificationType, baseStore, baseSite);
	}

	@Override
	public List<ProductInterestsOfCustomer> getProductsByCustomerInterests(final PageableData pageableData)
	{
		final Map<ProductModel, Map<NotificationType, Date>> productModelMap = productInterestService
				.getProductsByCustomerInterests(pageableData);

		final List<ProductInterestsOfCustomer> productInterestsOfCustomerList = new ArrayList<>();
		productModelMap.forEach((productModel, interstTypeMap) -> {
			prepareProductInterestsList(productInterestsOfCustomerList, productModel, interstTypeMap);
		});

		return productInterestsOfCustomerList;
	}

	protected void prepareProductInterestsList(final List<ProductInterestsOfCustomer> productInterestsOfCustomerList,
			final ProductModel productModel, final Map<NotificationType, Date> interstTypeMap)
	{
		final ProductData productData = getProductConverter().convert(productModel);
		getProductPricePopulator().populate(productModel, productData);
		getProductStockPopulator().populate(productModel, productData);
		productData.setFutureStocks(getFutureStockFacade().getFutureAvailability(productData.getCode()));

		final ProductInterestsOfCustomer productInterestsOfCustomer = new ProductInterestsOfCustomer();
		productInterestsOfCustomer.setProductData(productData);
		interstTypeMap.forEach((interestType, creationTime) -> {
			final ProductInterestDetail productInterestDetail = new ProductInterestDetail();
			productInterestDetail.setInterestType(interestType.name());
			productInterestDetail.setDateAdded(creationTime);
			productInterestDetail.setEnabled(Boolean.TRUE);
			productInterestsOfCustomer.getProductInterestDetail().add(productInterestDetail);
		});
		productInterestsOfCustomerList.add(productInterestsOfCustomer);
	}

	@Override
	public int getProductsCountByCustomerInterests(final PageableData pageableData)
	{

		return productInterestService.getProductsCountByCustomerInterests(pageableData);
	}


	protected ProductInterestService getProductInterestService()
	{
		return productInterestService;
	}

	@Required
	public void setProductInterestService(final ProductInterestService productInterestService)
	{
		this.productInterestService = productInterestService;
	}

	protected Converter<ProductInterestModel, ProductInterestData> getProductInterestConverter()
	{
		return productInterestConverter;
	}

	@Required
	public void setProductInterestConverter(final Converter<ProductInterestModel, ProductInterestData> productInterestConverter)
	{
		this.productInterestConverter = productInterestConverter;
	}

	protected Converter<ProductInterestData, ProductInterestModel> getProductInterestReverseConverter()
	{
		return productInterestReverseConverter;
	}

	@Required
	public void setProductInterestReverseConverter(
			final Converter<ProductInterestData, ProductInterestModel> productInterestReverseConverter)
	{
		this.productInterestReverseConverter = productInterestReverseConverter;
	}

	protected ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	protected Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	@Required
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	protected Populator<ProductModel, ProductData> getProductPricePopulator()
	{
		return productPricePopulator;
	}

	@Required
	public void setProductPricePopulator(final Populator<ProductModel, ProductData> productPricePopulator)
	{
		this.productPricePopulator = productPricePopulator;
	}

	protected Populator<ProductModel, ProductData> getProductStockPopulator()
	{
		return productStockPopulator;
	}

	@Required
	public void setProductStockPopulator(final Populator<ProductModel, ProductData> productStockPopulator)
	{
		this.productStockPopulator = productStockPopulator;
	}

	protected FutureStockFacade getFutureStockFacade()
	{
		return futureStockFacade;
	}

	@Required
	public void setFutureStockFacade(final FutureStockFacade futureStockFacade)
	{
		this.futureStockFacade = futureStockFacade;
	}



}
