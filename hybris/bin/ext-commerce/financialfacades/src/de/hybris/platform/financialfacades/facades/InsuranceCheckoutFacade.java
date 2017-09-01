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

package de.hybris.platform.financialfacades.facades;

public interface InsuranceCheckoutFacade
{

	/**
	 * Checks whether the orderEntryData has valid Form Data or not
	 *
	 * @return boolean
	 */
	boolean orderEntryHasValidFormData();

	/**
	 * Checks whether the cart or session have saved form data.
	 * 
	 * @return boolean
	 */
	boolean hasSavedFormData();
}
