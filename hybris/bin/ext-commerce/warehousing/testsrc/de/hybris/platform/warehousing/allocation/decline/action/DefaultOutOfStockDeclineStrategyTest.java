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
package de.hybris.platform.warehousing.allocation.decline.action;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.warehousing.allocation.decline.action.impl.DefaultOutOfStockDeclineStrategy;
import de.hybris.platform.warehousing.data.allocation.DeclineEntry;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.stock.services.impl.DefaultWarehouseStockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultOutOfStockDeclineStrategyTest
{

	@Mock
	private ConsignmentEntryModel consignmentEntryModel;
	@Mock
	private ConsignmentModel consignmentModel;
	@Mock
	private OrderEntryModel orderEntryModel;
	@Mock
	private ProductModel productModel;
	@Mock
	private WarehouseModel warehouseModel;
	@Mock
	private StockLevelModel stockLevelModel;
	@Mock
	private DeclineEntry manualDeclineEntry;
	@Mock
	private DeclineEntry autoDeclineEntry;
	@Mock
	private InventoryEventService inventoryEventService;
	@Mock
	private DefaultWarehouseStockService warehouseStockService;
	@Mock
	private StockService stockService;

	@InjectMocks
	private DefaultOutOfStockDeclineStrategy defaultOutOfStockStrategy;

	@Before
	public void setup()
	{
		when(manualDeclineEntry.getConsignmentEntry()).thenReturn(consignmentEntryModel);
		when(autoDeclineEntry.getConsignmentEntry()).thenReturn(consignmentEntryModel);
		when(consignmentEntryModel.getOrderEntry()).thenReturn(orderEntryModel);
		when(orderEntryModel.getProduct()).thenReturn(productModel);
		when(consignmentEntryModel.getConsignment()).thenReturn(consignmentModel);
		when(consignmentModel.getWarehouse()).thenReturn(warehouseModel);
		when(warehouseStockService.getStockLevelForProductCodeAndWarehouse(productModel.getCode(), warehouseModel)).thenReturn(1L);
		when(stockService.getStockLevel(productModel, warehouseModel)).thenReturn(stockLevelModel);
	}	


	@Test
	public void shouldExecute()
	{
		// when
		defaultOutOfStockStrategy.execute(manualDeclineEntry);

		// Then
		verify(warehouseStockService,times(1)).getStockLevelForProductCodeAndWarehouse(productModel.getCode(),warehouseModel);
		verify(inventoryEventService,times(1)).createShrinkageEvent(any());
	}

	@Test
	public void shouldExecuteEntries()
	{
		// when
		defaultOutOfStockStrategy.execute(Arrays.asList(manualDeclineEntry,autoDeclineEntry));

		// Then
		verify(warehouseStockService,times(2)).getStockLevelForProductCodeAndWarehouse(productModel.getCode(),warehouseModel);
		verify(inventoryEventService,times(2)).createShrinkageEvent(any());
	}
}
