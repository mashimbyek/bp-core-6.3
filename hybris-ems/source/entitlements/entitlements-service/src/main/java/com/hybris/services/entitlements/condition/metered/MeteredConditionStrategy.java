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
package com.hybris.services.entitlements.condition.metered;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.AbstractConditionStrategy;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ActionHandlerFactory;
import com.hybris.services.entitlements.condition.ConditionParameter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.validation.AbstractConversionStrategy;
import com.hybris.services.entitlements.validation.ValidationViolations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of metered condition strategy, which requires a quantity parameter for both granting
 * and checking an entitlement.
 */
public class MeteredConditionStrategy extends AbstractConditionStrategy implements ActionHandlerFactory
{
    /**
     * Condition value.
     */
    public static final String GRANT_PARAMETER_MAX_QUANTITY = "maxQuantity";

    /**
     * If allowOverage is true allow remainingQuantity to exceed maxQuantity.
     */
    public static final String GRANT_PARAMETER_OVERAGE =  "allowOverage";

    /**
     * Execution value.
     */
    public static final String EXECUTION_PARAMETER_QUANTITY = "quantity";

	/**
	 * variables.
	 */
    public static final String PARAMETER_REMAINING_QUANTITY = "remainingQuantity";

    private AbstractConversionStrategy<Integer> limitQuantityConverter;
	private AbstractConversionStrategy<Integer> consumptionQuantityConverter;
	private AbstractConversionStrategy<Integer> remainingQuantityConverter;
    private AbstractConversionStrategy<Boolean> booleanConverter;

    @Override
    protected List<ConditionParameter> getGrantParameters()
    {
        return Arrays.asList(
                new ConditionParameter(GRANT_PARAMETER_MAX_QUANTITY, true, limitQuantityConverter),
                new ConditionParameter(GRANT_PARAMETER_OVERAGE, false, booleanConverter)
        );
    }

    @Override
    protected List<ConditionParameter> getExecutionParameters()
    {
        return Arrays.asList(new ConditionParameter(EXECUTION_PARAMETER_QUANTITY, true, consumptionQuantityConverter));
    }

	@Override
	protected void fillActionHandlers(final Map<String, ActionHandler> container)
	{
		container.put(Actions.CHECK, new CheckHandler());
		container.put(Actions.USE, new UsageHandler());
		container.put(INTERNAL_ACTION_INIT, new InitHandler());
	}

    @Required
    public void setLimitQuantityConverter(final AbstractConversionStrategy<Integer> strategy)
    {
        this.limitQuantityConverter = strategy;
    }

	@Required
	public void setConsumptionQuantityConverter(final AbstractConversionStrategy<Integer> strategy)
	{
		this.consumptionQuantityConverter = strategy;
	}

	@Required
    public void setBooleanConverter(final AbstractConversionStrategy<Boolean> strategy)
    {
        this.booleanConverter = strategy;
    }

	@Required
	public void setRemainingQuantityConverter(final AbstractConversionStrategy<Integer> remainingQuantityConverter)
	{
		this.remainingQuantityConverter = remainingQuantityConverter;
	}

	@Override
	public void validateProperty(final String key, final String value, final ValidationViolations errors)
	{
		super.validateProperty(key, value, errors);
		if (PARAMETER_REMAINING_QUANTITY.equalsIgnoreCase(key))
		{
			remainingQuantityConverter.validate(key, value, errors);
		}
	}

	/**
	 * Check action handler.
	 */
	private class CheckHandler extends ActionHandler
	{
		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			final boolean allowOverage = booleanConverter.convert(condition.getProperty(GRANT_PARAMETER_OVERAGE));
			if (allowOverage)
			{
				return true;
			}
			final int maxQuantity = limitQuantityConverter.convert(condition.getProperty(GRANT_PARAMETER_MAX_QUANTITY));
			final Object userQuantityField = grant.getProperty(PARAMETER_REMAINING_QUANTITY);
			final int remainingQuantity = userQuantityField != null
					? remainingQuantityConverter.convert(userQuantityField)
					: maxQuantity;
			final int quantity = limitQuantityConverter.convert(executionParameterMap.get(EXECUTION_PARAMETER_QUANTITY));

			final int newUsedQuantity = remainingQuantity - quantity;
			return (newUsedQuantity >= 0);
		}
	}

	/**
	 * Use action handler.
	 */
	private class UsageHandler extends ActionHandler
	{
		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			final int maxQuantity = limitQuantityConverter.convert(condition.getProperty(GRANT_PARAMETER_MAX_QUANTITY));
			final Object userQuantityField = grant.getProperty(PARAMETER_REMAINING_QUANTITY);
			final int remainingQuantity = userQuantityField != null
					? remainingQuantityConverter.convert(userQuantityField)
					: maxQuantity;
			final int quantity = limitQuantityConverter.convert(executionParameterMap.get(EXECUTION_PARAMETER_QUANTITY));
			final int newUsedQuantity = remainingQuantity - quantity;
			// No condition stands for unlimited usage, so we check for overage only if a metered condition takes place.
			final boolean allowOverage = booleanConverter.convert(condition.getProperty(GRANT_PARAMETER_OVERAGE));
			if (!allowOverage)
			{
				if (newUsedQuantity < 0)
				{
					return false;
				}
			}
			grant.setProperty(PARAMETER_REMAINING_QUANTITY, Integer.toString(newUsedQuantity));
			return true;
		}
	}

	/**
	 * Init action handler.
	 */
	private class InitHandler extends ActionHandler
	{
		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			if (grant.getProperty(PARAMETER_REMAINING_QUANTITY) == null)
			{
				grant.setProperty(PARAMETER_REMAINING_QUANTITY, condition.getProperty(GRANT_PARAMETER_MAX_QUANTITY));
			}
			return true;
		}
	}
}
