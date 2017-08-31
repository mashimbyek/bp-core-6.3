/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.sorting;

import java.util.Set;

/**
 * Provide type-specific implementations.
 */
public interface GrantComparatorFactory
{
	/**
	 * Executor.
	 *
	 * @param grantComparatorType type name
	 * @return object that performs check for given type
	 */
	GrantComparator getGrantComparator(String grantComparatorType);

	/**
	 * Returns knows types.
	 *
	 * @return knows types.
	 */
	Set<String> getKnownTypes();
}
