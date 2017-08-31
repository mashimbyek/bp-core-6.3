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
package de.hybris.platform.timedaccesspromotionsfacades.order;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;


/**
 * Extended Cart facade interface.
 */
public interface ExtendedCartFacade extends CartFacade
{
	/**
	 * Method for adding a product to cart with new entry. Record promotionCode as a new attribute to
	 * CommerceCartParameter
	 *
	 * @param productCode
	 *           code of product to add
	 * @param quantity
	 *           the quantity of the product
	 * @param promotionCode
	 *           code of promotion applied
	 * @return the cart modification data that includes a statusCode and the actual quantity added to the cart
	 * @throws CommerceCartModificationException
	 *            if the cart cannot be modified
	 */
	public CartModificationData addNewEntryToCart(String productCode, long quantity, String promotionCode)
			throws CommerceCartModificationException;
}
