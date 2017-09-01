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

public class InitialStepVisitedStrategy extends AbstractStepVisitedAnalysisStrategy
{

	/**
	 * Check if step is visited strategy.
	 *
	 * @param progressStepId
	 */
	@Override
	public boolean isVisited(final String progressStepId)
	{
		return Boolean.TRUE;
	}
}
