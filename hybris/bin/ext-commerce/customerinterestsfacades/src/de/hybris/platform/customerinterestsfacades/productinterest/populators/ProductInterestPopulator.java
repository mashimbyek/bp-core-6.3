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


import de.hybris.platform.commercefacades.product.converters.populator.ProductPricePopulator;
import de.hybris.platform.commercefacades.product.converters.populator.ProductStockPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerinterestsfacades.data.ProductInterestData;
import de.hybris.platform.customerinterestsservices.model.ProductInterestModel;
import de.hybris.platform.notificationservices.service.strategies.SmsNotificationChannelStrategy;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


public class ProductInterestPopulator implements Populator<ProductInterestModel, ProductInterestData>
{
	private SmsNotificationChannelStrategy smsNotificationChannelStrategy;
	private AbstractPopulatingConverter<ProductModel, ProductData> productConverter;
	private ProductPricePopulator<ProductModel, ProductData> productPricePopulator;
	private ProductStockPopulator<ProductModel, ProductData> productStockPopulator;

	@Override
	public void populate(final ProductInterestModel source, final ProductInterestData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setExpiryDate(source.getExpiryDate());
		target.setEmailAddress(source.getCustomer().getUid());
		target.setMobileNumber(smsNotificationChannelStrategy.getMobilePhoneNumber(source.getCustomer()));
		target.setEmailNotificationEnabled(source.getEmailEnabled());
		target.setSmsNotificationEnabled(source.getSmsEnabled());
		target.setNotificationType(source.getNotificationType());
		target.setProduct(getProductConverter().convert(source.getProduct()));
		target.setCreationDate(source.getCreationtime());
		getProductPricePopulator().populate(source.getProduct(), target.getProduct());
		getProductStockPopulator().populate(source.getProduct(), target.getProduct());
	}

	public SmsNotificationChannelStrategy getSmsNotificationChannelStrategy()
	{
		return smsNotificationChannelStrategy;
	}


	@Required
	public void setSmsNotificationChannelStrategy(final SmsNotificationChannelStrategy smsNotificationChannelStrategy)
	{
		this.smsNotificationChannelStrategy = smsNotificationChannelStrategy;
	}


	public AbstractPopulatingConverter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	@Required
	public void setProductConverter(final AbstractPopulatingConverter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	public ProductPricePopulator<ProductModel, ProductData> getProductPricePopulator()
	{
		return productPricePopulator;
	}

	@Required
	public void setProductPricePopulator(final ProductPricePopulator<ProductModel, ProductData> productPricePopulator)
	{
		this.productPricePopulator = productPricePopulator;
	}

	public ProductStockPopulator<ProductModel, ProductData> getProductStockPopulator()
	{
		return productStockPopulator;
	}

	@Required
	public void setProductStockPopulator(final ProductStockPopulator<ProductModel, ProductData> productStockPopulator)
	{
		this.productStockPopulator = productStockPopulator;
	}

}
