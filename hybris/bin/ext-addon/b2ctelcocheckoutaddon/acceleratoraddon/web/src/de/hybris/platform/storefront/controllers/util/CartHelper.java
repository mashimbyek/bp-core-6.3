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
package de.hybris.platform.storefront.controllers.util;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;


/**
 * Helper that contains cart data related utility methods
 */
public class CartHelper
{
	private CartHelper()
	{
		// Deliberately left empty.
	}

	public static List<OrderEntryData> removeEmptyEntries(final List<OrderEntryData> allEntries)
	{
		if (CollectionUtils.isEmpty(allEntries))
		{
			return Collections.emptyList();
		}

		final List<OrderEntryData> realEntries = new ArrayList<OrderEntryData>();
		for (final OrderEntryData entry : allEntries)
		{
			if (entry.getProduct() != null)
			{
				realEntries.add(entry);
			}
		}

		if (CollectionUtils.isEmpty(realEntries))
		{
			return Collections.emptyList();
		}
		else
		{
			return realEntries;
		}
	}
}
