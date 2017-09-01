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
package de.hybris.platform.warehousing.asn.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.warehousing.asn.strategy.AsnReleaseDateStrategy;
import de.hybris.platform.warehousing.asn.strategy.BinSelectionStrategy;
import de.hybris.platform.warehousing.model.AdvancedShippingNoticeEntryModel;
import de.hybris.platform.warehousing.model.AdvancedShippingNoticeModel;
import de.hybris.platform.warehousing.stock.services.WarehouseStockService;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Test for stock level creation based on ASN.
 */
@UnitTest
public class DefaultAsnServiceTest
{

	private static final String PRODUCT1_CODE = "product1";
	private static final int PRODUCT1_AVAILABILITY = 80;

	private static final Date RELEASE_DATE = new Date();
	private static final Date DELAYED_RELEASE_DATE = new Date(RELEASE_DATE.getTime() + 1);

	@InjectMocks
	private final DefaultAsnService asnService = new DefaultAsnService();

	@Mock
	private WarehouseModel warehouse;
	@Mock
	private ModelService modelService;
	@Mock
	private AsnReleaseDateStrategy asnReleaseDateStrategy;
	@Mock
	private BinSelectionStrategy binSelectionStrategy;
	@Mock
	private WarehouseStockService warehouseStockService;

	private AdvancedShippingNoticeModel asn;
	private AdvancedShippingNoticeEntryModel asnEntry;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		asn = new AdvancedShippingNoticeModel();
		asn.setReleaseDate(RELEASE_DATE);
		asn.setWarehouse(warehouse);
		asnEntry = new AdvancedShippingNoticeEntryModel();
		asnEntry.setProductCode(PRODUCT1_CODE);
		asnEntry.setQuantity(PRODUCT1_AVAILABILITY);
		final List<AdvancedShippingNoticeEntryModel> asnEntries = Collections.singletonList(asnEntry);
		asn.setAsnEntries(asnEntries);
		when(asnReleaseDateStrategy.getReleaseDateForStockLevel(any())).thenReturn(DELAYED_RELEASE_DATE);
	}

	/**
	 * Should create stock level based on ASN data.
	 */
	@Test
	public void shouldCreateStockLevelBasedOnAsn()
	{
		//When
		final Map<String, Integer> bins = new HashMap<>();
		bins.put(null, asnEntry.getQuantity());
		when(binSelectionStrategy.getBinsForAsnEntry(any())).thenReturn(bins);
		// Given
		final StockLevelModel createdObject = new StockLevelModel();
		createdObject.setProductCode(PRODUCT1_CODE);
		createdObject.setAvailable(PRODUCT1_AVAILABILITY);
		createdObject.setReleaseDate(DELAYED_RELEASE_DATE);
		createdObject.setWarehouse(warehouse);
		createdObject.setBin(null);

		// When
		when(warehouseStockService.createStockLevel(eq(PRODUCT1_CODE), eq(warehouse), eq(PRODUCT1_AVAILABILITY), eq(InStockStatus.NOTSPECIFIED),
		                eq(DELAYED_RELEASE_DATE), any())).thenReturn(createdObject);
		asnService.processAsn(asn);

		// Then
		verify(modelService, times(1)).save(createdObject);
		assertEquals(asnEntry, createdObject.getAsnEntry());
	}
}
