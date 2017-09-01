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
package de.hybris.platform.customerinterestsfacades.beans;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the data which will be passed to the web page and shown
 */
public class ProductInterestsOfCustomer
{
	private ProductData productData;
	private final List<ProductInterestDetail> productInterestDetail = new ArrayList<>();

	public ProductData getProductData()
	{
		return productData;
	}

	public void setProductData(final ProductData productData)
	{
		this.productData = productData;
	}

	public List<ProductInterestDetail> getProductInterestDetail()
	{
		return productInterestDetail;
	}

}
