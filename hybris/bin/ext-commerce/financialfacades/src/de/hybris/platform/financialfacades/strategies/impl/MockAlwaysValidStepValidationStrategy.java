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

import de.hybris.platform.financialfacades.strategies.StepValidationStrategy;


public class MockAlwaysValidStepValidationStrategy implements StepValidationStrategy
{
	@Override
	public boolean isValid()
	{
		return true;
	}
}
