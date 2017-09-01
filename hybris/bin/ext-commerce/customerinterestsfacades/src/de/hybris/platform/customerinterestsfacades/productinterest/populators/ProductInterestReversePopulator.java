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

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


public class ProductInterestReversePopulator implements Populator<ProductInterestData, ProductInterestModel>
{
	private ProductService productService;
	private UserService userService;
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private CommonI18NService commonI18NService;
	private final String EXPIRY_DAY = "customerinterestsservices.expiryDay";

	@Override
	public void populate(final ProductInterestData source, final ProductInterestModel target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		if (target.getExpiryDate() == null)
		{
			target.setBaseStore(getBaseStoreService().getCurrentBaseStore());
			target.setProduct(getProductService().getProductForCode(source.getProduct().getCode()));
			target.setCustomer((CustomerModel) getUserService().getCurrentUser());
			target.setBaseSite(getBaseSiteService().getCurrentBaseSite());
			target.setLanguage(getCommonI18NService().getCurrentLanguage());

			LocalDate expiryDate = LocalDate.now().plusDays(Integer.valueOf(Config.getParameter(EXPIRY_DAY)));
			Date date = Date.from(expiryDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			target.setExpiryDate(date);
		}
		target.setNotificationType(source.getNotificationType());
		target.setEmailEnabled(source.isEmailNotificationEnabled());
		target.setSmsEnabled(source.isSmsNotificationEnabled());
	}


	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(ProductService productService)
	{
		this.productService = productService;
	}


	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}


	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}


	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}


	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}



}
