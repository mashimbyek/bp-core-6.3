/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.commercefacades.order.converters.populator;

import de.hybris.platform.commercefacades.quote.data.QuoteData;
import de.hybris.platform.core.model.order.QuoteModel;

import org.springframework.util.Assert;


public class QuotePopulator extends AbstractOrderPopulator<QuoteModel, QuoteData>
{

	@Override
	public void populate(final QuoteModel source, final QuoteData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		addCommon(source, target);
		addDetails(source, target);
		addTotals(source, target);
		addEntries(source, target);
		addPromotions(source, target);
		addComments(source, target);

		target.setHasCart(Boolean.valueOf(source.getCartReference() != null));
	}

	protected void addDetails(final QuoteModel source, final QuoteData target)
	{
		target.setCode(source.getCode());
		target.setVersion(source.getVersion());
		target.setExpirationTime(source.getExpirationTime());
		target.setState(source.getState());
		target.setCreationTime(source.getCreationtime());
		target.setUpdatedTime(source.getModifiedtime());
	}
}
