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
package de.hybris.platform.commerceservices.order.strategies.impl;

import de.hybris.platform.commerceservices.constants.CommerceServicesConstants;
import de.hybris.platform.commerceservices.order.strategies.QuoteSellerApproverAutoApprovalStrategy;
import de.hybris.platform.core.model.order.QuoteModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.springframework.beans.factory.annotation.Required;

import com.google.common.math.DoubleMath;


/**
 * Default implementation of {@link QuoteSellerApproverAutoApprovalStrategy}
 */
public class DefaultQuoteSellerApproverAutoApprovalStrategy implements QuoteSellerApproverAutoApprovalStrategy
{
	private static final double EPSILON = 0.00999d;
	private ConfigurationService configurationService;

	@Override
	public boolean shouldAutoApproveQuote(final QuoteModel quoteModel)
	{
		final double threshold = getConfigurationService().getConfiguration()
				.getDouble(CommerceServicesConstants.QUOTE_APPROVAL_THRESHOLD);
		return (DoubleMath.fuzzyCompare(quoteModel.getSubtotal().doubleValue(), threshold, EPSILON) < 0);
	}

	protected ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	@Required
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

}
