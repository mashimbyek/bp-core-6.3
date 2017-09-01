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
package de.hybris.platform.chinesepaymentservices.order.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class DefaultChineseOrderDaoTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultChineseOrderDao chineseOrderDao;

	@Resource
	private ModelService modelService;

	private AbstractOrderModel order;
	private static String ORDER_CODE = "10001000";
	private static final long MILLI_SECOND = 2 * 3600 * 1000;

	@Before
	public void prepare()
	{
		final CurrencyModel currency = new CurrencyModel();
		currency.setIsocode("en");
		currency.setSymbol("$");

		final UserModel user = new UserModel();
		user.setUid("testuser");

		order = new OrderModel();
		order.setCode(ORDER_CODE);
		order.setCurrency(currency);
		order.setDate(new Date());
		order.setUser(user);
		order.setPaymentStatus(PaymentStatus.NOTPAID);
		order.setStatus(OrderStatus.CREATED);
		modelService.save(order);
	}

	@Test
	public void testFindUnpaidOrders()
	{
		final Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, 1990);
		order.setDate(date.getTime());
		modelService.save(order);
		final List<AbstractOrderModel> orders = chineseOrderDao.findUnpaidOrders(MILLI_SECOND);

		assertEquals(ORDER_CODE, orders.get(0).getCode());
	}

	@Test
	public void testFindOrderByCode()
	{
		AbstractOrderModel result = chineseOrderDao.findOrderByCode(ORDER_CODE);
		assertFalse(result == null);

		ORDER_CODE = "10001001";
		result = chineseOrderDao.findOrderByCode(ORDER_CODE);
		assertNull(result);
	}
}
