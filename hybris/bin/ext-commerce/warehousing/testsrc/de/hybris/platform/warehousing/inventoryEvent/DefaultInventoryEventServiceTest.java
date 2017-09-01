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

package de.hybris.platform.warehousing.inventoryEvent;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.time.TimeService;
import de.hybris.platform.warehousing.inventoryevent.service.impl.DefaultInventoryEventService;
import de.hybris.platform.warehousing.model.IncreaseEventModel;
import de.hybris.platform.warehousing.model.ShrinkageEventModel;
import de.hybris.platform.warehousing.model.WastageEventModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultInventoryEventServiceTest
{
	@Mock
	private IncreaseEventModel increaseEventModel;
	@Mock
	private ShrinkageEventModel shrinkageEventModel;
	@Mock
	private WastageEventModel wastageEventModel;
	@Mock
	private ProductModel productModel;
	@Mock
	private WarehouseModel warehouseModel;
	@Mock
	private Collection<StockLevelModel> stockLevelModels;
	@Mock
	private StockLevelModel stockLevelModel;
	@InjectMocks
	private DefaultInventoryEventService inventoryEventService = new DefaultInventoryEventService();
	@Mock
	private ModelService modelService;
	@Mock
	private TimeService timeService;

	private long quantity = 5;

	@Before
	public void setUp()
	{
		inventoryEventService.setModelService(modelService);

		when(shrinkageEventModel.getStockLevel()).thenReturn(stockLevelModel);
		when(shrinkageEventModel.getQuantity()).thenReturn(quantity);

		when(wastageEventModel.getStockLevel()).thenReturn(stockLevelModel);
		when(wastageEventModel.getQuantity()).thenReturn(quantity);

		when(increaseEventModel.getStockLevel()).thenReturn(stockLevelModel);
		when(increaseEventModel.getQuantity()).thenReturn(quantity);

		when(stockLevelModel.getWarehouse()).thenReturn(warehouseModel);
		when(stockLevelModel.getProduct()).thenReturn(productModel);
	}

	@Test
	public void shouldCreateIncreaseEvent()
	{
		when(modelService.create(IncreaseEventModel.class)).thenReturn(increaseEventModel);
		when(timeService.getCurrentTime()).thenReturn(new Date());
		IncreaseEventModel resultEvent = inventoryEventService.createIncreaseEvent(increaseEventModel);
		verify(modelService, times(1)).save(increaseEventModel);
		assertEquals(quantity, resultEvent.getQuantity());
	}

	@Test
	public void shouldCreateShrinkageEvent()
	{
		when(modelService.create(ShrinkageEventModel.class)).thenReturn(shrinkageEventModel);
		when(timeService.getCurrentTime()).thenReturn(new Date());
		ShrinkageEventModel resultEvent = inventoryEventService.createShrinkageEvent(shrinkageEventModel);
		verify(modelService, times(1)).save(shrinkageEventModel);
		assertEquals(quantity, resultEvent.getQuantity());
	}

	@Test
	public void shouldCreateWastageEvent()
	{
		when(modelService.create(WastageEventModel.class)).thenReturn(wastageEventModel);
		when(timeService.getCurrentTime()).thenReturn(new Date());
		WastageEventModel resultEvent = inventoryEventService.createWastageEvent(wastageEventModel);
		verify(modelService, times(1)).save(wastageEventModel);
		assertEquals(quantity, resultEvent.getQuantity());
	}
}
