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
package de.hybris.platform.chineselogisticfacades.delivery.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.chineselogisticfacades.delivery.data.DeliveryTimeSlotData;
import de.hybris.platform.chineselogisticfacades.delivery.populator.DeliveryTimeSlotPopulator;
import de.hybris.platform.chineselogisticservices.delivery.DeliveryTimeSlotService;
import de.hybris.platform.chineselogisticservices.model.DeliveryTimeSlotModel;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultChineseDeliveryTimeSlotFacadeTest
{

	private static final String TIME_SLOT_CODE = "0001";
	private static final String TIME_SLOT_NAME = "testname";
	private static final String DELIVERY_TIME_SLOT = "any days";

	private DefaultChineseDeliveryTimeSlotFacade facade;
	private CartModel cart;
	private DeliveryTimeSlotPopulator deliveryTimeSlotPopulator;
	private List<DeliveryTimeSlotModel> deliveryTimeSlotModels;

	@Mock
	private DeliveryTimeSlotService deliveryTimeSlotService;
	@Mock
	private DeliveryTimeSlotModel deliveryTimeSlotModel;
	@Mock
	private CartFacade cartFacade;
	@Mock
	private CartService cartService;


	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);

		deliveryTimeSlotPopulator = new DeliveryTimeSlotPopulator();
		facade = new DefaultChineseDeliveryTimeSlotFacade();
		facade.setDeliveryTimeSlotPopulator(deliveryTimeSlotPopulator);
		facade.setDeliveryTimeSlotService(deliveryTimeSlotService);
		facade.setCartFacade(cartFacade);
		facade.setCartService(cartService);

		cart = new CartModel();
		deliveryTimeSlotModels = new ArrayList<>();
		deliveryTimeSlotModels.add(deliveryTimeSlotModel);

		BDDMockito.given(deliveryTimeSlotService.getAllDeliveryTimeSlots()).willReturn(deliveryTimeSlotModels);
		BDDMockito.given(deliveryTimeSlotModel.getCode()).willReturn(TIME_SLOT_CODE);
		BDDMockito.given(deliveryTimeSlotModel.getName()).willReturn(TIME_SLOT_NAME);
		BDDMockito.given(cartFacade.hasSessionCart()).willReturn(true);
		BDDMockito.given(cartService.getSessionCart()).willReturn(cart);
	}

	@Test
	public void testGetAllDeliveryTimeSlots()
	{
		final List<DeliveryTimeSlotData> deliveryTimeSlots = facade.getAllDeliveryTimeSlots();
		Assert.assertEquals(1, deliveryTimeSlots.size());
		Assert.assertEquals(TIME_SLOT_CODE, deliveryTimeSlots.get(0).getCode());
		Assert.assertEquals(TIME_SLOT_NAME, deliveryTimeSlots.get(0).getName());
	}

	@Test
	public void testSetDeliveryTimeSlot()
	{
		Mockito.doNothing().when(deliveryTimeSlotService).setDeliveryTimeSlot(cart, DELIVERY_TIME_SLOT);
		facade.setDeliveryTimeSlot(DELIVERY_TIME_SLOT);
		BDDMockito.verify(deliveryTimeSlotService, Mockito.times(1)).setDeliveryTimeSlot(cart, DELIVERY_TIME_SLOT);
	}
}
