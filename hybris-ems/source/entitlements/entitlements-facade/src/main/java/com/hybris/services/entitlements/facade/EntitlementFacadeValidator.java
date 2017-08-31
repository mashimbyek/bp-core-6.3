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
package com.hybris.services.entitlements.facade;

import com.hybris.services.entitlements.api.exceptions.ValidationException;
import com.hybris.services.entitlements.condition.ConditionData;
import com.hybris.services.entitlements.condition.CriterionData;
import com.hybris.services.entitlements.sorting.ComparatorValidator;
import com.hybris.services.entitlements.validation.ConditionDataListValidator;
import com.hybris.services.entitlements.validation.GrantPropertyValidator;
import com.hybris.services.entitlements.validation.ValidationExecutor;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

/**
 * Validates facade-specific data.
 */
public class EntitlementFacadeValidator
{
	private ValidationExecutor validator;
	private GrantPropertyValidator grantValidator;
	private ConditionDataListValidator conditionDataListValidator;
	private ComparatorValidator comparatorValidator;

	void validateCriteriaAndComparatorType(final List<CriterionData> criteria, final String comparatorType)
	{
		if (criteria == null)
		{
			throw new ValidationException("criteria=null: Must not be null");
		}
		final ValidationViolations errors = new ValidationViolations();
		int idx = 0;
		for (final CriterionData criterion : criteria)
		{
			errors.pushNestedPath("criteria[" + idx + ']');
			idx  += 1;
			try
			{
				errors.addAll(validator.validate(criterion));
			}
			finally
			{
				errors.popPath();
			}
		}
		comparatorValidator.validate(comparatorType, errors);
		reportErrors(errors);
	}

	void validateConditions(final List<ConditionData> conditionDataList)
	{
		final ValidationViolations errors = validator.validate(conditionDataList);
		conditionDataListValidator.validate(conditionDataList, errors);
		reportErrors(errors);
	}


	void validateObject(final Object object)
	{
		final ValidationViolations errors = validator.validate(object);
		reportErrors(errors);
	}

	@Required
	public void setValidator(final ValidationExecutor validator)
	{
		this.validator = validator;
	}

	@Required
	public void setGrantValidator(final GrantPropertyValidator validatorArg)
	{
		grantValidator = validatorArg;
	}

	@Required
	public void setConditionDataListValidator(final ConditionDataListValidator conditionDataListValidator)
	{
		this.conditionDataListValidator = conditionDataListValidator;
	}

	@Required
	public void setComparatorValidator(final ComparatorValidator comparatorValidator)
	{
		this.comparatorValidator = comparatorValidator;
	}

	void validateProperty(final String key, final String value)
	{
		final ValidationViolations errors = new ValidationViolations();
		grantValidator.validateProperty(key, value, errors);
		reportErrors(errors);
	}

	private void reportErrors(final ValidationViolations errors)
	{
		if (errors != null && errors.hasErrors())
		{
			throw new ValidationException(errors.toString());
		}
	}
}
