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
package de.hybris.platform.commerceservices.strategies.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.constants.CommerceServicesConstants;
import de.hybris.platform.commerceservices.order.strategies.impl.DefaultQuoteSellerApproverAutoApprovalStrategy;
import de.hybris.platform.core.enums.QuoteState;
import de.hybris.platform.core.model.order.QuoteModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@UnitTest
public class DefaultQuoteSellerApproverAutoApprovalStrategyTest
{
	@InjectMocks
	private DefaultQuoteSellerApproverAutoApprovalStrategy defaultQuoteSellerApproverAutoApprovalStrategy = new DefaultQuoteSellerApproverAutoApprovalStrategy();
	@Mock
	ConfigurationService configurationService;
	@Mock
	private Configuration configuration;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShouldAutoApproveTheQuote()
	{
		final QuoteModel quoteModel = mock(QuoteModel.class);
		given(quoteModel.getCode()).willReturn("1234");
		given(quoteModel.getState()).willReturn(QuoteState.SELLER_SUBMITTED);
		given(configurationService.getConfiguration()).willReturn(configuration);

		final double[][] testMatrix = new double[][]
		{
		{ 1_000d, 999d, 999.99d, 1_000.01d, 1_001d },
		{ 5_000d, 4_999d, 4_999.99d, 5_000.01d, 5_001d },
		{ 10_000d, 9_999d, 9_999.99d, 10_000.01d, 10_001d },
		{ 75_000d, 74_999d, 74_999.99d, 75_000.01d, 75_001d },
		{ 500_000d, 499_999d, 499_999.99d, 500_000.01d, 500_001d },
		{ 750_000d, 749_999d, 749_999.99d, 750_000.01d, 750_001d },
		{ 1_000_000d, 999_999d, 999_999.99d, 1_000_000.01d, 1_000_001d },
		{ 10_000_000d, 9_999_999d, 9_999_999.99d, 10_000_000.01d, 10_000_001d },
		{ 100_000_000d, 99_999_999d, 99_999_999.99d, 100_000_000.01d, 100_000_001d } };

		for (double[] testRow : testMatrix)
		{
			given(configurationService.getConfiguration().getDouble(CommerceServicesConstants.QUOTE_APPROVAL_THRESHOLD)).willReturn(
					Double.valueOf(testRow[0]));

			given(quoteModel.getSubtotal()).willReturn(Double.valueOf(testRow[1]));
			assertTrue(String.format("Should approve lower value: %.2f", testRow[1]),
					defaultQuoteSellerApproverAutoApprovalStrategy.shouldAutoApproveQuote(quoteModel));

			given(quoteModel.getSubtotal()).willReturn(Double.valueOf(testRow[2]));
			assertTrue(String.format("Should approve lower value (decimal): %.2f", testRow[2]),
					defaultQuoteSellerApproverAutoApprovalStrategy.shouldAutoApproveQuote(quoteModel));

			given(quoteModel.getSubtotal()).willReturn(Double.valueOf(testRow[0]));
			assertFalse(String.format("Should not approve value equal to threshold: %.2f", testRow[0]),
					defaultQuoteSellerApproverAutoApprovalStrategy.shouldAutoApproveQuote(quoteModel));

			given(quoteModel.getSubtotal()).willReturn(Double.valueOf(testRow[3]));
			assertFalse(String.format("Should not approve value greater than threshold (decimal): %.2f", testRow[3]),
					defaultQuoteSellerApproverAutoApprovalStrategy.shouldAutoApproveQuote(quoteModel));

			given(quoteModel.getSubtotal()).willReturn(Double.valueOf(testRow[4]));
			assertFalse(String.format("Should not approve value greater than threshold: %.2f", testRow[4]),
					defaultQuoteSellerApproverAutoApprovalStrategy.shouldAutoApproveQuote(quoteModel));
		}
	}
}
