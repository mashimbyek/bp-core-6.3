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
package com.hybris.services.entitlements.condition;

import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.Map;

/**
 * Interface for validation.
 */
public interface GrantValidator
{
	/**
	 * Validation of required parameters when creating a condition.
	 *
	 * @param parameterMap map or parameters
	 * @param errors accumulates found errors
	 */
	void validateGrantParameters(Map<String, String> parameterMap, final ValidationViolations errors);

	/**
	 * Validation of required parameters when checking a condition.
	 *
	 * @param parameterMap map or parameters
	 * @param errors accumulates found errors
	 */
	void validateExecutionParameters(Map<String, String> parameterMap, final ValidationViolations errors);

	/**
	 * Validation of properties when checking a condition.
	 *
	 * @param key the key
	 * @param value the value
	 * @param errors accumulates found errors
	 */
	void validateProperty(final String key, final String value, final ValidationViolations errors);
}
