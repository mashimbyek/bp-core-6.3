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
package de.hybris.platform.warehousing;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.warehousing.allocation.AllocationService;
import de.hybris.platform.warehousing.data.sourcing.SourcingResults;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;
import de.hybris.platform.warehousing.stock.services.impl.DefaultWarehouseStockService;
import de.hybris.platform.warehousing.util.BaseSourcingIntegrationTest;
import de.hybris.platform.warehousing.util.DeclineEntryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@IntegrationTest
public class ShippingSourcingEndToEndIntegrationTest extends BaseSourcingIntegrationTest
{
	@Resource
	private AllocationService allocationService;
	@Resource
	private InventoryEventService inventoryEventService;
	@Resource
	private CommerceStockService commerceStockService;
	@Resource
	private WarehouseService warehouseService;
	@Resource
	private DefaultWarehouseStockService warehouseStockService;
	@Resource
	private StockService stockService;

	private static final Long CAMERA_QTY = new Long(3);
	private static final Long MEMORYCARD_QTY = new Long(4);
	public static final String CODE_MONTREAL = "montreal";
	public static final String CODE_BOSTON = "boston";
	public static final String NAME_MONTREAL_DOWNTOWN = "montreal-downtown";
	public Map<ConsignmentEntryModel, Long> declineEntryInfo;

	@Before
	public void setup()
	{
		declineEntryInfo = new HashMap<ConsignmentEntryModel, Long>();
	}

	/**
	 * Given an shipping order with 1 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * <p>
	 * Result:<br>
	 * It should source complete from 1 location, Montreal<br>
	 * Decline should works well<br>
	 * <p>
	 */
	@Test
	public void shouldSource1ProductFrom1LocationAndDecline()
	{
		// Given
		stockLevels.Camera(warehouses.Montreal(), 5);
		stockLevels.Camera(warehouses.Boston(), 2);

		// When create consignment
		final OrderModel order = orders.Camera_Shipped(CAMERA_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertTrue(results.isComplete());

		final Collection<ConsignmentModel> consignmentResult = allocationService.createConsignments(order, "con", results);

		//then verify consignment
		assertTrue(consignmentResult.size() == 1);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));
		assertTrue(consignmentResult.stream().allMatch(
				result -> result.getWarehouse().getName().equals(CODE_MONTREAL)));
		assertTrue(consignmentResult.stream().allMatch(
				result -> result.getConsignmentEntries().stream().allMatch(e -> e.getQuantity().longValue() == 3)));

		//then verify the ATP
		assertEquals(Long.valueOf(4),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		assertEquals(Long.valueOf(2),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
		assertEquals(Long.valueOf(2),
				warehouseStockService.getStockLevelForProductCodeAndWarehouse(products.Camera().getCode(), warehouses.Montreal()));

		//when decline the order
		declineEntryInfo.put(consignmentResult.stream().flatMap(consignment -> consignment.getConsignmentEntries().stream())
				.collect(Collectors.toList()).get(0), Long.valueOf(2L));
		allocationService.manualReallocate(DeclineEntryBuilder.aDecline().build_Manual(declineEntryInfo,
				warehouseService.getWarehouseForCode(CODE_BOSTON)));

		//then verify the ATP
		assertEquals(Long.valueOf(4),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		assertEquals(Long.valueOf(0),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Boston()));
		assertEquals(Long.valueOf(4),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
	}

	/**
	 * Given an shipping order with 2 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * entry 2 : {quantity: 4, product: memoryCard}<br>
	 * <p>
	 * Result:<br>
	 * It should source complete from 1 location, Montreal<br>
	 * <p>
	 */
	@Test
	public void shouldSource2ProductFrom1Location()
	{
		// Given
		stockLevels.Camera(warehouses.Montreal(), 5);
		stockLevels.Camera(warehouses.Boston(), 4);
		stockLevels.MemoryCard(warehouses.Montreal(), 5);
		stockLevels.MemoryCard(warehouses.Boston(), 4);

		// When create consignment
		final OrderModel order = orders.CameraAndMemoryCard_Shipped(CAMERA_QTY, MEMORYCARD_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertTrue(results.isComplete());

		final Collection<ConsignmentModel> consignmentResult = allocationService.createConsignments(order, "con", results);

		assertTrue(consignmentResult.size() == 1);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));
		assertTrue(consignmentResult.stream().allMatch(
				result -> result.getWarehouse().getCode().equals(CODE_MONTREAL)));

		assertTrue(consignmentResult.stream().allMatch(
				result -> result.getConsignmentEntries().stream().anyMatch(e -> e.getQuantity().longValue() == 3)));
		assertTrue(consignmentResult.stream().anyMatch(
				result -> result.getConsignmentEntries().stream().anyMatch(e -> e.getQuantity().longValue() == 4)));

		//then verify the ATP
		assertEquals(Long.valueOf(6),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		assertEquals(Long.valueOf(2),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
		assertEquals(Long.valueOf(5),
				commerceStockService.getStockLevelForProductAndBaseStore(products.MemoryCard(), baseStores.NorthAmerica()));
		assertEquals(
				Long.valueOf(1),
				commerceStockService.getStockLevelForProductAndPointOfService(products.MemoryCard(),
						pointsOfService.Montreal_Downtown()));

		//then verify allocation result (note: this will cause ATP twice)
		final Collection<AllocationEventModel> allocationResult = consignmentResult.stream()
				.map(result -> inventoryEventService.createAllocationEvents(result)).flatMap(result -> result.stream())
				.collect(Collectors.toList());
		assertTrue(allocationResult.size() == 2);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));

		assertTrue(allocationResult.stream().allMatch(
				result -> result.getStockLevel().getWarehouse().getCode().equals(CODE_MONTREAL)));
		assertTrue(allocationResult.stream().anyMatch(result -> result.getQuantity() == 3));
		assertTrue(allocationResult.stream().anyMatch(result -> result.getQuantity() == 4));
	}

	/**
	 * ======= assertTrue( allocationResult.stream().allMatch(result ->
	 * result.getStockLevel().getWarehouse().getCode().equals(CODE_MONTREAL)));
	 * assertTrue(allocationResult.stream().anyMatch(result -> result.getQuantity() == 3));
	 * assertTrue(allocationResult.stream().anyMatch(result -> result.getQuantity() == 4)); }
	 * 
	 * /** Given an shipping order with 2 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * entry 2 : {quantity: 4, product: memoryCard}<br>
	 * <p>
	 * Result:<br>
	 * It should source complete from 2 locations<br>
	 * <p>
	 */
	@Test
	public void shouldSource2ProductFrom2Location()
	{
		// Given
		stockLevels.Camera(warehouses.Montreal(), 2);
		stockLevels.Camera(warehouses.Boston(), 2);
		stockLevels.MemoryCard(warehouses.Montreal(), 2);
		stockLevels.MemoryCard(warehouses.Boston(), 4);

		// When create consignment
		final OrderModel order = orders.CameraAndMemoryCard_Shipped(CAMERA_QTY, MEMORYCARD_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertTrue(results.isComplete());
		//verify consignment result
		final Collection<ConsignmentModel> consignmentResult = allocationService.createConsignments(order, "con", results);
		assertTrue(consignmentResult.size() == 2);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));
		assertTrue(consignmentResult.stream().anyMatch(result -> {
			final boolean consignmentEntryMatch = (result.getConsignmentEntries().size() == 2);
			final boolean quantityMatch = result.getConsignmentEntries().stream().allMatch(e -> e.getQuantity().longValue() == 2);
			final boolean warehouseMatch = result.getWarehouse().getCode().equals(CODE_MONTREAL);
			return quantityMatch && warehouseMatch && consignmentEntryMatch;
		}));
		assertTrue(consignmentResult.stream().anyMatch(result -> {
			final boolean consignmentEntryMatch = (result.getConsignmentEntries().size() == 2);
			final boolean quantityMatch = result.getConsignmentEntries().stream().anyMatch(e -> e.getQuantity().longValue() == 1);
			final boolean warehouseMatch = result.getWarehouse().getCode().equals(CODE_BOSTON);
			return quantityMatch && warehouseMatch && consignmentEntryMatch;
		}));

		//then verify the ATP
		assertEquals(Long.valueOf(1),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		assertEquals(Long.valueOf(0),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
		assertEquals(Long.valueOf(2),
				commerceStockService.getStockLevelForProductAndBaseStore(products.MemoryCard(), baseStores.NorthAmerica()));
		assertEquals(
				Long.valueOf(0),
				commerceStockService.getStockLevelForProductAndPointOfService(products.MemoryCard(),
						pointsOfService.Montreal_Downtown()));
	}

	/**
	 * >>>>>>> develop Given an shipping order with 1 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * <p>
	 * Result:<br>
	 * It should source complete from 2 locations<br>
	 * <p>
	 */
	@Test
	public void shouldSource1ProductFrom2Location()
	{
		// Given
		stockLevels.Camera(warehouses.Montreal(), 2);
		stockLevels.Camera(warehouses.Boston(), 2);

		// When create consignment
		final OrderModel order = orders.Camera_Shipped(CAMERA_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertTrue(results.isComplete());
		//verify consignment result
		final Collection<ConsignmentModel> consignmentResult = allocationService.createConsignments(order, "con", results);
		assertTrue(consignmentResult.size() == 2);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));
		assertTrue(consignmentResult.stream().anyMatch(result -> {
			final boolean consignmentEntryMatch = (result.getConsignmentEntries().size() == 1);
			final boolean quantityMatch = result.getConsignmentEntries().stream().allMatch(e -> e.getQuantity().longValue() == 2);
			final boolean warehouseMatch = result.getWarehouse().getCode().equals(CODE_MONTREAL);
			return quantityMatch && warehouseMatch && consignmentEntryMatch;
		}));
		assertTrue(consignmentResult.stream().anyMatch(result -> {
			final boolean consignmentEntryMatch = (result.getConsignmentEntries().size() == 1);
			final boolean quantityMatch = result.getConsignmentEntries().stream().allMatch(e -> e.getQuantity().longValue() == 1);
			final boolean warehouseMatch = result.getWarehouse().getCode().equals(CODE_BOSTON);
			return quantityMatch && warehouseMatch && consignmentEntryMatch;
		}));

		//then verify the ATP
		assertEquals(Long.valueOf(1),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		assertEquals(Long.valueOf(0),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
		assertEquals(Long.valueOf(0),
				warehouseStockService.getStockLevelForProductAndWarehouse(products.Camera(), warehouses.Montreal()));
	}

	/**
	 * Given an shipping order with 1 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * <p>
	 * Result:<br>
	 * It should source complete from warehouse which doesn't belong to POS<br>
	 * <p>
	 */
	@Test
	public void shouldSource1ProductFrom1LocationWithoutPos()
	{
		// Given
		stockLevels.Camera(warehouses.Random(), 5);

		// When create consignment
		final OrderModel order = orders.Camera_Shipped(CAMERA_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertTrue(results.isComplete());

		final Collection<ConsignmentModel> consignmentResult = allocationService.createConsignments(order, "con", results);

		//then verify consignment
		assertTrue(consignmentResult.size() == 1);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));
		assertTrue(consignmentResult.stream().allMatch(result -> !result.getWarehouse().getCode().isEmpty()));
		assertTrue(consignmentResult.stream().allMatch(
				result -> result.getConsignmentEntries().stream().allMatch(e -> e.getQuantity().longValue() == 3)));

		//then verify the ATP
		assertEquals(Long.valueOf(2),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
	}

	/**
	 * Given an shipping order with 1 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * <p>
	 * Result:<br>
	 * verify force out of stock<br>
	 * <p>
	 */
	@Test
	public void shouldSource1Fail_ForceOutOfStock()
	{
		// Given
		stockLevels.Camera(warehouses.Montreal(), 5);
		stockLevels.Camera(warehouses.Boston(), 1);
		stockService.setInStockStatus(products.Camera(), Collections.singleton(warehouses.Montreal()),
				InStockStatus.FORCEOUTOFSTOCK);

		// When create consignment
		final OrderModel order = orders.Camera_Shipped(CAMERA_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertFalse(results.isComplete());

		//then verify the ATP
		assertEquals(Long.valueOf(1),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		assertEquals(Long.valueOf(0),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));

		//when removed force out of stock
		stockService.setInStockStatus(products.Camera(), Collections.singleton(warehouses.Montreal()), InStockStatus.NOTSPECIFIED);

		//verify the ATP
		assertEquals(Long.valueOf(5),
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
	}

	/**
	 * Given an shipping order with 1 entries:<br>
	 * entry 1 : {quantity: 3, product: camera}<br>
	 * <p>
	 * Result:<br>
	 * verify force in of stock<br>
	 * <p>
	 */
	@Test
	public void shouldSourceSuccess_ForceInOfStock()
	{
		// Given
		stockLevels.Camera(warehouses.Montreal(), 1);
		stockLevels.Camera(warehouses.Boston(), 1);
		stockService.setInStockStatus(products.Camera(), Collections.singleton(warehouses.Montreal()), InStockStatus.FORCEINSTOCK);

		//then verify the ATP
		assertEquals(null, commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));

		// When create consignment
		final OrderModel order = orders.Camera_Shipped(CAMERA_QTY);
		final SourcingResults results = sourcingService.sourceOrder(order);

		//check sourcing result
		assertTrue(results.isComplete());

		final Collection<ConsignmentModel> consignmentResult = allocationService.createConsignments(order, "con", results);

		//then verify consignment
		assertTrue(consignmentResult.size() == 1);
		assertTrue(consignmentResult.stream().allMatch(result -> result.getStatus().getCode().equals("READY")));
		assertTrue(consignmentResult.stream().allMatch(result -> !result.getWarehouse().getCode().isEmpty()));
		assertTrue(consignmentResult.stream().allMatch(
				result -> result.getConsignmentEntries().stream().allMatch(e -> e.getQuantity().longValue() == 3)));

		//then verify the ATP
		assertEquals(null, commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
		//0 is correct, not apply for pickup order
		assertEquals(null,
				commerceStockService.getStockLevelForProductAndPointOfService(products.Camera(), pointsOfService.Montreal_Downtown()));
		assertEquals(null, warehouseStockService.getStockLevelForProductAndWarehouse(products.Camera(), warehouses.Montreal()));

		//cancel force in stock
		stockService.setInStockStatus(products.Camera(), Collections.singleton(warehouses.Montreal()), InStockStatus.NOTSPECIFIED);

		//check the ATP
		assertEquals(Long.valueOf(-1),
				commerceStockService.getStockLevelForProductAndBaseStore(products.Camera(), baseStores.NorthAmerica()));
	}
}