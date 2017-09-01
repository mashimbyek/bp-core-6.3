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

package de.hybris.platform.financialfacades.strategies.impl;

import de.hybris.platform.financialfacades.facades.InsuranceCartFacade;

import org.springframework.beans.factory.annotation.Required;


public class QuoteRecordedStepVisitedStrategy extends AbstractStepVisitedAnalysisStrategy
{
	private InsuranceCartFacade insuranceCartFacade;

	/**
	 * Check if step is visited strategy.
	 */
	@Override
	public boolean isVisited(final String progressStepId)
	{
		return getInsuranceCartFacade().checkStepIsVisitedFromQuoteInCurrentCart(progressStepId);
	}

	/**
	 * Set step isVisited.
	 *
	 * @param progressStepId
	 */
	@Override
	public void setVisited(final boolean isVisited, final String progressStepId)
	{
		if (isVisited == Boolean.TRUE)
		{
			getInsuranceCartFacade().setVisitedStepToQuoteInCurrentCart(progressStepId);
		}
	}

	public InsuranceCartFacade getInsuranceCartFacade()
	{
		return insuranceCartFacade;
	}

	@Required
	public void setInsuranceCartFacade(final InsuranceCartFacade insuranceCartFacade)
	{
		this.insuranceCartFacade = insuranceCartFacade;
	}
}
