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
package de.hybris.platform.commercefacades.order.converters.populator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.data.ConsignmentData;
import de.hybris.platform.commercefacades.order.data.ConsignmentEntryData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;


@UnitTest
public class GroupOrderConsignmentEntryPopulatorTest extends GroupOrderEntryPopulatorTest
{
	public static final String BASE_PRODUCT_CODE = "baseProduct";
	public static final long QTY_1 = 1L, QTY_2 = 2L, QTY_3 = 3L, QTY_4 = 4L, QTY_5 = 5L;
	public static final long SHIPPED_QTY_1 = 1L, SHIPPED_QTY_2 = 1L, SHIPPED_QTY_3 = 1L, SHIPPED_QTY_4 = 1L, SHIPPED_QTY_5 = 1L;

	@InjectMocks
	protected GroupOrderConsignmentEntryPopulator populator = new GroupOrderConsignmentEntryPopulator();

	@Override
	@Before
	public void setUp()
	{
		super.setUp();
		populator.setPriceDataFactory(priceDataFactory);
	}

	@Override
	@Test
	public void shouldGroupEntries()
	{
		final OrderData order = new OrderData();

		final List<ConsignmentData> consignments = new ArrayList();
		order.setConsignments(consignments);

		ConsignmentData consignmentA = new ConsignmentData();
		consignments.add(consignmentA);

		List<ConsignmentEntryData> consignmentEntryListInA = new ArrayList();
		consignmentA.setEntries(consignmentEntryListInA);

		// the first consignment has 3 entires; in which 2 products has the same baseCode, which should be grouped into one item
		consignmentEntryListInA
				.add(createConsignmentEntryData(QTY_1, SHIPPED_QTY_1, createOrderEntry("productCode1", BASE_PRODUCT_CODE, 1L, 1L)));
		consignmentEntryListInA
				.add(createConsignmentEntryData(QTY_2, SHIPPED_QTY_2, createOrderEntry("productCode2", BASE_PRODUCT_CODE, 2L, 5L)));
		consignmentEntryListInA
				.add(createConsignmentEntryData(QTY_3, SHIPPED_QTY_3, createOrderEntry("productCode3", null, 3L, 5L)));

		// the second consignment has 2 entries; 1 has the same baseCode as the products in the first consignment;
		// but this one will not be grouped into the first consignment
		ConsignmentData consignmentB = new ConsignmentData();
		consignments.add(consignmentB);

		List<ConsignmentEntryData> consignmentEntryListInB = new ArrayList();
		consignmentB.setEntries(consignmentEntryListInB);

		consignmentEntryListInB
				.add(createConsignmentEntryData(QTY_3, SHIPPED_QTY_3, createOrderEntry("productCode4", BASE_PRODUCT_CODE, 1L, 1L)));
		consignmentEntryListInB
				.add(createConsignmentEntryData(QTY_3, SHIPPED_QTY_3, createOrderEntry("productCode5", null, 2L, 5L)));

		// un-consignment entries
		final List<OrderEntryData> unconsignedEntries = new ArrayList();
		order.setUnconsignedEntries(unconsignedEntries);

		unconsignedEntries.add(createOrderEntry("productCode1", BASE_PRODUCT_CODE, 1L, 1L));
		unconsignedEntries.add(createOrderEntry("productCode2", BASE_PRODUCT_CODE, 2L, 2L));

		populator.populate(mock(OrderModel.class), order);

		// begin to check consignmentA
		consignmentA = order.getConsignments().get(0);
		consignmentEntryListInA = consignmentA.getEntries();

		// consignmentA has 2 consignment entries
		assertThat(Integer.valueOf(consignmentEntryListInA.size()), is(Integer.valueOf(2)));

		// the ungrouped one is placed before the grouped one
		assertThat(consignmentEntryListInA.get(0).getQuantity(), is(Long.valueOf(QTY_3)));
		assertThat(consignmentEntryListInA.get(0).getShippedQuantity(), is(Long.valueOf(SHIPPED_QTY_3)));

		OrderEntryData orderEntry = consignmentEntryListInA.get(0).getOrderEntry();
		assertNull(orderEntry.getEntries()); // without sub-entries
		assertThat(orderEntry.getProduct().getCode(), is("productCode3"));

		// this is grouped one
		assertThat(consignmentEntryListInA.get(1).getQuantity(), is(Long.valueOf(QTY_1 + QTY_2)));
		assertThat(consignmentEntryListInA.get(1).getShippedQuantity(), is(Long.valueOf(SHIPPED_QTY_1 + SHIPPED_QTY_2)));

		// the grouped order entry has 2 sub entries (grouped)
		orderEntry = consignmentEntryListInA.get(1).getOrderEntry();
		assertThat(Integer.valueOf(orderEntry.getEntries().size()), is(Integer.valueOf(2)));
		assertThat(orderEntry.getProduct().getCode(), is(BASE_PRODUCT_CODE));

		assertThat(orderEntry.getEntries().get(0).getProduct().getCode(), is("productCode1"));
		assertThat(orderEntry.getEntries().get(1).getProduct().getCode(), is("productCode2"));

		// begin to check consignmentB
		consignmentB = order.getConsignments().get(1);
		consignmentEntryListInB = consignmentB.getEntries();

		// consignmentB has 2 consignment entries
		assertThat(Integer.valueOf(consignmentEntryListInB.size()), is(Integer.valueOf(2)));

		// check un-consignment entries
		assertThat(Integer.valueOf(order.getUnconsignedEntries().size()), is(Integer.valueOf(1)));

		orderEntry = order.getUnconsignedEntries().get(0);
		assertThat(Integer.valueOf(orderEntry.getEntries().size()), is(Integer.valueOf(2)));
		assertThat(orderEntry.getProduct().getCode(), is(BASE_PRODUCT_CODE));
	}

	protected ConsignmentEntryData createConsignmentEntryData(final long quantity, final long shippedQuantity,
			final OrderEntryData orderEntry)
	{
		final ConsignmentEntryData entry = new ConsignmentEntryData();
		entry.setOrderEntry(orderEntry);
		entry.setQuantity(Long.valueOf(quantity));
		entry.setShippedQuantity(Long.valueOf(shippedQuantity));

		return entry;
	}
}
