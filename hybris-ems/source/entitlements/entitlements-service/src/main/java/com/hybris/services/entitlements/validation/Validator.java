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
package com.hybris.services.entitlements.validation;

/**
 * Interface for definition class-mapped validators.
 * <p>
 *     To add a validator to the system, you need to implement the interface and
 *     then register the implementation in Spring.
 * </p>
 */
public interface Validator
{
	/**
	 * Return true if the implementation is able to validate given class.
	 * <p>
	 *     Note, that if validator was specified by {@link com.hybris.services.entitlements.api.Constraint},
	 *     the method won't be called. This way you can create validators, that are not mapped to any class.
	 * </p>
	 * <p>
	 *     If more than one implementation accepts the class, they all will be applied.
	 * </p>
	 * @param cls class is being checked
	 * @return true if this class can be validated by this implementation
	 */
	boolean supportsClass(Class cls);

	/**
	 * Performs custom checks and fills errors with found violations.
	 * <p>
	 *     Usually you do not need to validate any complex child fields here. Use {@link javax.validation.Valid}
	 *     annotation for fields that you need to validate by their own validators.
	 * </p>
	 * <p>
	 *     When a collection is annotated with Valid, validation will be performed on each item of the
	 *     collection. The same works for maps.
	 * </p>
	 *
	 * @param object object to check
	 * @param errors violation collector
	 */
	void validate(final Object object, final ValidationViolations errors);
}
