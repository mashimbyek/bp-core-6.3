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

package de.hybris.platform.storefront.checkout.steps;

import de.hybris.platform.financialacceleratorstorefront.checkout.step.InsuranceCheckoutStep;

import org.apache.log4j.Logger;


public class FormCheckoutStep extends InsuranceCheckoutStep
{
	protected static final Logger LOG = Logger.getLogger(FormCheckoutStep.class);
	private String formPageId;

	public String getFormPageId()
	{
		return formPageId;
	}

	public void setFormPageId(final String formPageId)
	{
		this.formPageId = formPageId;
	}
}
