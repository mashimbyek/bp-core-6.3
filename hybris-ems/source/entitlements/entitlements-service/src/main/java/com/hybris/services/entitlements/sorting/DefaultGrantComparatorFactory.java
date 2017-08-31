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

import com.hybris.services.entitlements.api.exceptions.ValidationException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * Default implementation of type-specific items factory.
 */
public class DefaultGrantComparatorFactory implements GrantComparatorFactory, ApplicationContextAware
{
	private static final Pattern COMPARATOR_REGEXP = Pattern.compile("GrantComparator|Comparator$");
	private static final String DEFAULT_GRANT_COMPARATOR = "default";

	private ApplicationContext context;

	private Map<String, GrantComparator> stringGrantComparatorMap;

	@PostConstruct
	private void init()
	{
		initGrantComparators();
        Assert.notNull(stringGrantComparatorMap.get(DEFAULT_GRANT_COMPARATOR), "No default grantComparator provided");
	}

	@Override
	public GrantComparator getGrantComparator(final String grantComparatorType)
	{
		final String type = grantComparatorType != null ? grantComparatorType : DEFAULT_GRANT_COMPARATOR;

        final GrantComparator grantComparator = stringGrantComparatorMap.get(type);
        if (grantComparator == null)
        {
            throw new ValidationException(String.format("Unknown grantComparator \"%s\"", type));
        }
        return grantComparator;
	}

	@Override
	public Set<String> getKnownTypes()
	{
		return stringGrantComparatorMap.keySet();
	}

	private void initGrantComparators()
	{
		final String[] names = context.getBeanNamesForType(GrantComparator.class);
		stringGrantComparatorMap = new HashMap<>(names.length);
		for (final String beanName : names)
		{
			final String type = COMPARATOR_REGEXP.matcher(beanName).replaceAll("");
			final GrantComparator grantComparator = (GrantComparator) context.getBean(beanName);
			stringGrantComparatorMap.put(type, grantComparator);
		}
	}

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException
	{
        this.context = applicationContext;
    }
}
