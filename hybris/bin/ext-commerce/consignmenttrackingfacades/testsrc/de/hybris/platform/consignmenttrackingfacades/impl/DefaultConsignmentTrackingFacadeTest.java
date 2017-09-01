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
package de.hybris.platform.consignmenttrackingfacades.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.data.ConsignmentData;
import de.hybris.platform.consignmenttrackingservices.service.ConsignmentTrackingService;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultConsignmentTrackingFacadeTest
{

	private DefaultConsignmentTrackingFacade consignmentTrackingFacade;

	@Mock
	private ConsignmentTrackingService consignmentTrackingService;

	@Mock
	private Converter<ConsignmentModel, ConsignmentData> consignmentConverter;

	private ConsignmentModel consignmentModel;

	@Before
	public void prepare()
	{

		MockitoAnnotations.initMocks(this);

		consignmentModel = new ConsignmentModel();

		consignmentTrackingFacade = new DefaultConsignmentTrackingFacade();
		consignmentTrackingFacade.setConsignmentConverter(consignmentConverter);
		consignmentTrackingFacade.setConsignmentTrackingService(consignmentTrackingService);
	}

	@Test
	public void test_getConsignmentByCode()
	{
		final ConsignmentData consignmentData = mock(ConsignmentData.class);
		final String orderCode = "10001000";
		final String consignmentCode = "a10001000";

		given(consignmentTrackingService.getConsignmentForCode(orderCode, consignmentCode)).willReturn(
				Optional.of(consignmentModel));
		given(consignmentConverter.convert(consignmentModel)).willReturn(consignmentData);

		final Optional<ConsignmentData> optional = consignmentTrackingFacade.getConsignmentByCode(orderCode, consignmentCode);
		Assert.assertEquals(consignmentData, optional.get());

	}

	@Test
	public void test_getTrackingUrlForConsignment() throws MalformedURLException
	{
		final URL url = new URL("https://www.hybris.com");
		final String orderCode = "10001000";
		final String consignmentCode = "a10001000";

		given(consignmentTrackingService.getConsignmentForCode(orderCode, consignmentCode))
				.willReturn(Optional.of(consignmentModel));
		given(consignmentTrackingService.getTrackingUrlForConsignment(consignmentModel)).willReturn(url);

		final String result = consignmentTrackingFacade.getTrackingUrlForConsignmentCode(orderCode, consignmentCode);
		Assert.assertEquals(url.toString(), result);
	}
}
