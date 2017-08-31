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

import com.hybris.services.entitlements.service.ConditionTypeFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Default implementation of type-specific items factory.
 */
public class DefaultConditionTypeFactory implements ConditionTypeFactory
{
	private static final Pattern EXECUTOR_REGEXP = Pattern.compile("ConditionExecutor|Executor$");
	private static final Pattern VALIDATOR_REGEXP = Pattern.compile("ConditionValidator|Validator$");
	@Autowired
	private ApplicationContext context;
	private Map<String,ActionHandlerFactory> conditionCheckers;
	private Map<String,GrantValidator> conditionValidators;

	@PostConstruct
	private void init()
	{
		initCheckers();
		initValidators();
	}

	@Override
	public ActionHandlerFactory getExecutor(final String conditionType)
	{
		return conditionCheckers.get(conditionType);
	}

	@Override
	public GrantValidator getValidator(final String conditionType)
	{
		return conditionValidators.get(conditionType);
	}

	@Override
	public Set<String> getKnownTypes()
	{
		return conditionCheckers.keySet();
	}

	private void initCheckers()
	{
		final String[] names = context.getBeanNamesForType(ActionHandlerFactory.class);
		conditionCheckers = new HashMap<>(names.length);
		for (final String beanName : names)
		{
			final String type = EXECUTOR_REGEXP.matcher(beanName).replaceAll("");
			if (!beanName.equals(type))
			{
				final ActionHandlerFactory actionHandlerFactory = (ActionHandlerFactory) context.getBean(beanName);
				conditionCheckers.put(type, actionHandlerFactory);
			}
		}
	}

	private void initValidators()
	{
		final String[] names = context.getBeanNamesForType(GrantValidator.class);
		conditionValidators = new HashMap<>(names.length);
		for (final String beanName : names)
		{
			final String type = VALIDATOR_REGEXP.matcher(beanName).replaceAll("");
			final GrantValidator grantValidator = (GrantValidator) context.getBean(beanName);
			conditionValidators.put(type, grantValidator);
		}
	}
}
