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
package de.hybris.platform.warehousingfacade.consignment;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.model.PickUpDeliveryModeModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.warehousing.constants.WarehousingConstants;
import de.hybris.platform.warehousing.enums.DeclineReason;
import de.hybris.platform.warehousing.process.WarehousingBusinessProcessService;
import de.hybris.platform.warehousing.stock.services.WarehouseStockService;
import de.hybris.platform.warehousingfacade.order.data.ConsignmentReallocationData;
import de.hybris.platform.warehousingfacade.order.data.DeclineEntryData;
import de.hybris.platform.warehousingfacade.order.impl.DefaultWarehousingConsignmentFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class DefaultWarehousingConsignmentFacadeTest
{
	private static final String CONSIGNMENT_CODE = "Consignment_Code_1";
	private static final String CAMERA_CODE = "camera";
	private static final String LENS_CODE = "lens";
	private static final String REALLOCATION_WAREHOUSE_CODE = "reallocation_warehouse";
	private static final String GLOBAL_REALLOCATION_WAREHOUSE_CODE = "global_reallocation_warehouse";
	protected static final String CONSIGNMENT_ACTION_EVENT_NAME = "ConsignmentActionEvent";
	protected static final String REALLOCATE_CONSIGNMENT_CHOICE = "reallocateConsignment";

	@InjectMocks
	private DefaultWarehousingConsignmentFacade warehousingConsignmentFacade;

	@Mock
	private OrderModel order;
	@Mock
	private ConsignmentModel consignment;
	@Mock
	private ConsignmentEntryModel cameraConsignmentEntry;
	@Mock
	private ConsignmentEntryModel lensConsignmentEntry;
	@Mock
	private OrderEntryModel cameraEntry;
	@Mock
	private ProductModel camera;
	@Mock
	private OrderEntryModel lensEntry;
	@Mock
	private ProductModel lens;
	@Mock
	private WarehouseModel reallocationWarehouse;
	@Mock
	private WarehouseModel globalReallocationWarehouse;
	@Mock
	private ZoneDeliveryModeModel shippingDeliveryMode;
	@Mock
	private PickUpDeliveryModeModel pickUpDeliveryMode;
	@Mock
	private ConsignmentProcessModel consignmentProcess;
	@Mock
	private ConsignmentReallocationData consignmentReallocationData;
	@Mock
	private DeclineEntryData cameraDeclineEntryData;
	@Mock
	private DeclineEntryData lensDeclineEntryData;
	@Mock
	private WarehousingBusinessProcessService<ConsignmentModel> consignmentBusinessProcessService;
	@Mock
	private GenericDao<ConsignmentModel> consignmentGenericDao;
	@Mock
	private ModelService modelService;
	@Mock
	private WarehouseService warehouseService;
	@Mock
	private WarehouseStockService warehouseStockService;

	private List<ConsignmentStatus> reallocableConsignmentStatusList;

	@Before
	public void setUp()
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(ConsignmentModel.CODE, CONSIGNMENT_CODE);
		when(consignmentGenericDao.find(params)).thenReturn(Collections.singletonList(consignment));
		doNothing().when(modelService).save(any());
		when(reallocationWarehouse.getCode()).thenReturn(REALLOCATION_WAREHOUSE_CODE);
		when(warehouseService.getWarehouseForCode(REALLOCATION_WAREHOUSE_CODE)).thenReturn(reallocationWarehouse);
		when(warehouseStockService.getStockLevelForProductCodeAndWarehouse(LENS_CODE, reallocationWarehouse)).thenReturn(200L);
		when(warehouseStockService.getStockLevelForProductCodeAndWarehouse(CAMERA_CODE, reallocationWarehouse)).thenReturn(200L);
		when(globalReallocationWarehouse.getCode()).thenReturn(GLOBAL_REALLOCATION_WAREHOUSE_CODE);
		when(warehouseService.getWarehouseForCode(GLOBAL_REALLOCATION_WAREHOUSE_CODE)).thenReturn(globalReallocationWarehouse);
		when(warehouseStockService.getStockLevelForProductCodeAndWarehouse(CAMERA_CODE, globalReallocationWarehouse))
				.thenReturn(200L);

		reallocableConsignmentStatusList = new ArrayList<>();
		reallocableConsignmentStatusList.add(ConsignmentStatus.READY);
		warehousingConsignmentFacade.setReallocableConsignmentStatusList(reallocableConsignmentStatusList);

		when(consignment.getCode()).thenReturn(CONSIGNMENT_CODE);
		when(consignmentProcess.getCode()).thenReturn(CONSIGNMENT_CODE + WarehousingConstants.CONSIGNMENT_PROCESS_CODE_SUFFIX);
		when(consignment.getStatus()).thenReturn(ConsignmentStatus.READY);
		when(consignment.getDeliveryMode()).thenReturn(shippingDeliveryMode);
		when(consignment.getConsignmentProcesses()).thenReturn(Collections.singletonList(consignmentProcess));
		when(consignment.getConsignmentEntries()).thenReturn(Sets.newHashSet(cameraConsignmentEntry, lensConsignmentEntry));
		when(cameraConsignmentEntry.getOrderEntry()).thenReturn(cameraEntry);
		when(cameraConsignmentEntry.getQuantityPending()).thenReturn(10L);
		when(cameraConsignmentEntry.getConsignment()).thenReturn(consignment);
		when(cameraEntry.getProduct()).thenReturn(camera);
		when(cameraEntry.getQuantity()).thenReturn(10L);
		when(camera.getCode()).thenReturn(CAMERA_CODE);
		when(lensConsignmentEntry.getOrderEntry()).thenReturn(lensEntry);
		when(lensConsignmentEntry.getQuantityPending()).thenReturn(5L);
		when(lensConsignmentEntry.getConsignment()).thenReturn(consignment);
		when(lensEntry.getProduct()).thenReturn(lens);
		when(lensEntry.getQuantity()).thenReturn(5L);
		when(lens.getCode()).thenReturn(LENS_CODE);
		when(consignmentReallocationData.getDeclineEntries())
				.thenReturn(Lists.newArrayList(cameraDeclineEntryData, lensDeclineEntryData));
		when(cameraDeclineEntryData.getProductCode()).thenReturn(CAMERA_CODE);
		when(cameraDeclineEntryData.getQuantity()).thenReturn(2L);
		when(cameraDeclineEntryData.getReason()).thenReturn(DeclineReason.DAMAGED);
		when(lensDeclineEntryData.getProductCode()).thenReturn(LENS_CODE);
		when(lensDeclineEntryData.getQuantity()).thenReturn(1L);
		when(lensDeclineEntryData.getReason()).thenReturn(DeclineReason.DAMAGED);

	}

	@Test
	public void testReallocateConsignmentMultiEntriesAutoDecline()
	{
		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);

		//Then
		verify(consignmentBusinessProcessService)
				.triggerChoiceEvent(consignment, CONSIGNMENT_ACTION_EVENT_NAME, REALLOCATE_CONSIGNMENT_CHOICE);
	}

	@Test
	public void testReallocateConsignmentMultiEntriesGlobalReallocationWarehouse()
	{
		//Given
		when(consignmentReallocationData.getGlobalReallocationWarehouseCode()).thenReturn(REALLOCATION_WAREHOUSE_CODE);

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);

		//Then
		verify(warehouseService, times(2)).getWarehouseForCode(REALLOCATION_WAREHOUSE_CODE);
		verify(warehouseStockService).getStockLevelForProductCodeAndWarehouse(LENS_CODE, reallocationWarehouse);
		verify(warehouseStockService).getStockLevelForProductCodeAndWarehouse(CAMERA_CODE, reallocationWarehouse);
		verify(consignmentBusinessProcessService)
				.triggerChoiceEvent(consignment, CONSIGNMENT_ACTION_EVENT_NAME, REALLOCATE_CONSIGNMENT_CHOICE);
	}

	@Test
	public void testReallocateConsignmentMultiEntriesAutoAndManualDecline()
	{
		//Given
		when(lensDeclineEntryData.getReallocationWarehouseCode()).thenReturn(REALLOCATION_WAREHOUSE_CODE);

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);

		//Then
		verify(warehouseService).getWarehouseForCode(REALLOCATION_WAREHOUSE_CODE);
		verify(warehouseStockService).getStockLevelForProductCodeAndWarehouse(LENS_CODE, reallocationWarehouse);
		verify(consignmentBusinessProcessService)
				.triggerChoiceEvent(consignment, CONSIGNMENT_ACTION_EVENT_NAME, REALLOCATE_CONSIGNMENT_CHOICE);
	}

	@Test
	public void testReallocateConsignmentMultiEntriesGlobalAndLocalReallocationWarehouse()
	{
		//Given
		when(consignmentReallocationData.getGlobalReallocationWarehouseCode()).thenReturn(GLOBAL_REALLOCATION_WAREHOUSE_CODE);
		when(lensDeclineEntryData.getReallocationWarehouseCode()).thenReturn(REALLOCATION_WAREHOUSE_CODE);

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);

		//Then
		verify(warehouseService).getWarehouseForCode(REALLOCATION_WAREHOUSE_CODE);
		verify(warehouseService).getWarehouseForCode(GLOBAL_REALLOCATION_WAREHOUSE_CODE);
		verify(warehouseStockService).getStockLevelForProductCodeAndWarehouse(LENS_CODE, reallocationWarehouse);
		verify(warehouseStockService).getStockLevelForProductCodeAndWarehouse(CAMERA_CODE, globalReallocationWarehouse);
		verify(consignmentBusinessProcessService)
				.triggerChoiceEvent(consignment, CONSIGNMENT_ACTION_EVENT_NAME, REALLOCATE_CONSIGNMENT_CHOICE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReallocateConsignmentNoDeclineEntriesData()
	{
		//Given
		when(consignmentReallocationData.getDeclineEntries()).thenReturn(Collections.emptyList());

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);
	}

	@Test(expected = IllegalStateException.class)
	public void testReallocateConfirmedConsignment()
	{
		//Given
		when(consignment.getStatus()).thenReturn(ConsignmentStatus.SHIPPED);

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReallocatePickupConsignment()
	{
		//Given
		when(consignment.getDeliveryMode()).thenReturn(pickUpDeliveryMode);

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReallocateConsignmentQtyHigherThanPending()
	{
		//Given
		when(cameraDeclineEntryData.getQuantity()).thenReturn(20L);

		//When
		warehousingConsignmentFacade.reallocateConsignment(CONSIGNMENT_CODE, consignmentReallocationData);
	}
}
