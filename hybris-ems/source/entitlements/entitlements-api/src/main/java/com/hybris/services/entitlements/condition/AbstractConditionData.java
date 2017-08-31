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

import com.hybris.commons.dto.impl.AbstractPropertyAwareDto;

import javax.validation.constraints.NotNull;

/**
 * Base class for grant conditions and criteria.
 */
public class AbstractConditionData extends AbstractPropertyAwareDto
{
	@NotNull
	private String type;

	public String getType()
	{
		return type;
	}

	public void setType(final String type)
	{
		this.type = type;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final ConditionData that = (ConditionData) o;

		if (type != null ? !type.equals(that.getType()) : that.getType() != null)
		{
			return false;
		}

		if (!getProperties().equals(((AbstractPropertyAwareDto) o).getProperties()))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return type != null ? type.hashCode() : 0;
	}
}
