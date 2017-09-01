/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package de.hybris.platform.ordermanagementfacade.returns.converters.populator;

import java.math.BigDecimal;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnEntryData;
import de.hybris.platform.ordermanagementfacade.returns.data.ReturnRequestData;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


public class OrdermanagementReturnPopulator implements Populator<ReturnRequestModel, ReturnRequestData>
{
	private Converter<ReturnEntryModel, ReturnEntryData> returnEntryConverter;
	private Converter<OrderModel, OrderData> orderConverter;
	private PriceDataFactory priceDataFactory;

	@Override
	public void populate(final ReturnRequestModel source, final ReturnRequestData target) throws ConversionException
	{
		if(source != null && target != null)
		{
			target.setCode(source.getCode());
			target.setRma(source.getRMA());
			target.setStatus(source.getStatus());
			target.setRefundDeliveryCost(source.getRefundDeliveryCost());

			final CurrencyModel currency = source.getOrder().getCurrency();
			target.setDeliveryCost(getPriceDataFactory().create(PriceDataType.BUY, new BigDecimal(source.getOrder().getDeliveryCost()),currency));
			target.setSubTotal(getPriceDataFactory().create(PriceDataType.BUY, source.getSubtotal(),currency));

			BigDecimal total = source.getSubtotal();
			if(source.getRefundDeliveryCost()){
				total = total.add(target.getDeliveryCost().getValue());
			}
			target.setTotal(getPriceDataFactory().create(PriceDataType.BUY, total,currency));

			Assert.notNull(source.getReturnEntries(), "Parameter returnEntries in return cannot be null.");
			target.setEntries(Converters.convertAll(source.getReturnEntries(), getReturnEntryConverter()));
			target.setOrder(getOrderConverter().convert(source.getOrder()));
		}
	}

	protected Converter<ReturnEntryModel, ReturnEntryData> getReturnEntryConverter()
	{
		return returnEntryConverter;
	}

	@Required
	public void setReturnEntryConverter(final Converter<ReturnEntryModel, ReturnEntryData> returnEntryConverter)
	{
		this.returnEntryConverter = returnEntryConverter;
	}

	protected Converter<OrderModel, OrderData> getOrderConverter()
	{
		return orderConverter;
	}

	@Required
	public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	protected PriceDataFactory getPriceDataFactory()
	{
		return priceDataFactory;
	}

	@Required
	public void setPriceDataFactory(PriceDataFactory priceDataFactory)
	{
		this.priceDataFactory = priceDataFactory;
	}
}
