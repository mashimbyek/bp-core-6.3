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

import de.hybris.platform.financialfacades.strategies.StepVisitedAnalysisStrategy;


public abstract class AbstractStepVisitedAnalysisStrategy implements StepVisitedAnalysisStrategy
{
	public void setVisited(final boolean isVisited, final String progressStepId)
	{/* By default do nothing. */
	}
}
