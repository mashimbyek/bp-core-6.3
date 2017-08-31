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
package de.hybris.platform.orderselfserviceaddon.facades;

import de.hybris.platform.orderselfserviceaddon.ordercancel.OrderCancelRecordEntryData;

import java.util.Map;

/**
 * OrderCancelFacade
 */
public interface OrderCancelFacade

{

	/**
	 * Requests complete cancel operation on an Order. Depending on current state, order might be canceled immediately or
	 * the cancellation decision might be delayed until the response from Warehouse arrives.
	 *
	 * @param orderCode
	 * 		The id for the order to cancel
	 * @param cancelEntryQuantity
	 * 		A map of EntryNumber and the canceled quantity
	 * @return OrderCancelRecordEntryData that represents the request and the result of cancel operation.
	 * @throws de.hybris.platform.ordercancel.OrderCancelException
	 */
	OrderCancelRecordEntryData requestOrderCancel(final String orderCode, final Map<Integer, Integer> cancelEntryQuantity)
			throws de.hybris.platform.ordercancel.OrderCancelException;
}
