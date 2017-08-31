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
 */
package de.hybris.platform.orderselfserviceaddon.facades.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.ordercancel.OrderCancelEntry;
import de.hybris.platform.ordercancel.OrderCancelRequest;
import de.hybris.platform.ordercancel.OrderCancelService;
import de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel;
import de.hybris.platform.orderselfserviceaddon.facades.OrderCancelFacade;
import de.hybris.platform.orderselfserviceaddon.ordercancel.OrderCancelRecordEntryData;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Default implementation for {@link OrderCancelFacade}
 */
public class DefaultOrderCancelFacade implements OrderCancelFacade
{


	private OrderCancelService orderCancelService;
	private UserService userService;
	private OrderService orderService;
	private CustomerAccountService customerAccountService;
	private BaseStoreService baseStoreService;
	private Converter<OrderCancelRecordEntryModel, OrderCancelRecordEntryData> orderCancelRecordEntryConverter;


	@Override
	public OrderCancelRecordEntryData requestOrderCancel(final String orderCode, final Map<Integer, Integer> cancelEntryQuantity)
			throws de.hybris.platform.ordercancel.OrderCancelException
	{

		final OrderModel orderModel = getCustomerAccountService()
				.getOrderForCode(orderCode, getBaseStoreService().getCurrentBaseStore());
		OrderCancelRequest orderCancelRequest = new OrderCancelRequest(orderModel,
				buildOrderCancelEntries(orderModel, cancelEntryQuantity));

		return getOrderCancelRecordEntryConverter()
				.convert(orderCancelService.requestOrderCancel(orderCancelRequest, userService.getCurrentUser()));
	}


	protected AbstractOrderEntryModel getOrderEntryForOrderCodeAndEntryNumber(final OrderModel orderModel, final int entryNumber)
	{
		try
		{
			return getOrderService().getEntryForNumber(orderModel, entryNumber);
		}
		catch (final ModelNotFoundException | UnknownIdentifierException e) //NOSONAR
		{
			return null;
		}
	}

	/**
	 * a helper method that takes build a list of {@link OrderCancelEntry}
	 *
	 * @param orderModel
	 * @param cancelEntryQuantity
	 * 		The map which contains a pair of OrderEntry entrynumber and cancelled quantity
	 * @return list of {@link OrderCancelEntry}
	 */

	protected List<OrderCancelEntry> buildOrderCancelEntries(final OrderModel orderModel,
			final Map<Integer, Integer> cancelEntryQuantity)
	{

		final List<OrderCancelEntry> orderCancelEntries = new ArrayList<>();

		if (!cancelEntryQuantity.isEmpty())
		{
			cancelEntryQuantity.entrySet().stream().filter(entry -> entry.getValue() > 0).forEach(entry ->
			{
				OrderCancelEntry orderCancelEntry = new OrderCancelEntry(
						getOrderEntryForOrderCodeAndEntryNumber(orderModel, entry.getKey()), entry.getValue());
				orderCancelEntries.add(orderCancelEntry);
			});
		}
		return orderCancelEntries;
	}


	protected OrderCancelService getOrderCancelService()
	{
		return orderCancelService;
	}

	@Required
	public void setOrderCancelService(OrderCancelService orderCancelService)
	{
		this.orderCancelService = orderCancelService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	protected OrderService getOrderService()
	{
		return orderService;
	}

	@Required
	public void setOrderService(OrderService orderService)
	{
		this.orderService = orderService;
	}

	protected CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	protected Converter<OrderCancelRecordEntryModel, OrderCancelRecordEntryData> getOrderCancelRecordEntryConverter()
	{
		return orderCancelRecordEntryConverter;
	}

	@Required
	public void setOrderCancelRecordEntryConverter(
			Converter<OrderCancelRecordEntryModel, OrderCancelRecordEntryData> orderCancelRecordEntryConverter)
	{
		this.orderCancelRecordEntryConverter = orderCancelRecordEntryConverter;
	}
}
