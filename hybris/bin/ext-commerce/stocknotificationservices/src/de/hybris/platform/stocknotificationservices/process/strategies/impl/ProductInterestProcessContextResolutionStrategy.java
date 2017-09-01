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
package de.hybris.platform.stocknotificationservices.process.strategies.impl;

import de.hybris.platform.acceleratorservices.process.strategies.impl.DefaultProcessContextResolutionStrategy;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontProcessModel;
import de.hybris.platform.customerinterestsservices.model.ProductInterestsProcessModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;


/**
 *
 */
public class ProductInterestProcessContextResolutionStrategy extends DefaultProcessContextResolutionStrategy
{
	private static final Logger LOG = Logger.getLogger(ProductInterestProcessContextResolutionStrategy.class);
	private static final String BUSINESS_PROCESS_MUST_NOT_BE_NULL_MSG = "businessProcess must not be null";

	@Override
	public BaseSiteModel getCmsSite(final BusinessProcessModel businessProcess)
	{
		ServicesUtil.validateParameterNotNull(businessProcess, BUSINESS_PROCESS_MUST_NOT_BE_NULL_MSG);

		if (businessProcess instanceof StoreFrontProcessModel)
		{
			return ((StoreFrontProcessModel) businessProcess).getSite();
		}

		if (businessProcess instanceof OrderProcessModel)
		{
			return ((OrderProcessModel) businessProcess).getOrder().getSite();
		}

		if (businessProcess instanceof ConsignmentProcessModel)
		{
			return ((ConsignmentProcessModel) businessProcess).getConsignment().getOrder().getSite();
		}

		if (businessProcess instanceof ProductInterestsProcessModel)
		{
			return ((ProductInterestsProcessModel) businessProcess).getProductInterest().getBaseSite();
		}

		LOG.info("Unsupported BusinessProcess type [" + businessProcess.getClass().getSimpleName() + "] for item ["
				+ businessProcess + "]");
		return null;
	}
}
