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

import com.hybris.services.entitlements.domain.Grant;

import java.io.Serializable;
import java.util.Date;

/**
 * Default grant comparator. Sorts grant conditions according to grant time.
 */
public class DefaultGrantComparator implements GrantComparator, Serializable
{
	private static final long serialVersionUID = 1745375699879758661L;

	@Override
	public int compare(final Grant o1, final Grant o2)
	{
	    // TODO what if compare metered with non-metered? is it possible?
		final Date d1 = getDateSafe(o1);
		final Date d2 = getDateSafe(o2);
		if (d1 == null && d2 == null)
		{
			return 0;
		}
		if (d1 != null && d2 != null)
		{
			return d1.compareTo(d2);
		}
		else
		{
			return d1 != null ? 1 : -1;
		}
	}

	private Date getDateSafe(final Grant o1)
	{
		return o1 == null ? null : o1.getGrantTime();
	}
}
