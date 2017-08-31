/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.hybris.cis.client.shipping.models;

import com.hybris.cis.client.shared.models.CisResult;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Container for a list of {@link CisShippingMethod}.
 */
@XmlRootElement(name = "shippingMethodsResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class CisShippingMethods extends CisResult
{

	/**
	 * A list of shipping methods.
	 */
	@XmlElementWrapper(name = "shippingMethods")
	@XmlElement(name = "shippingMethod")
	private List<CisShippingMethod> shippingMethods;

	public List<CisShippingMethod> getShippingMethods()
	{
		return this.shippingMethods;
	}

	public void setShippingMethods(final List<CisShippingMethod> shippingMethods)
	{
		this.shippingMethods = shippingMethods;
	}
}
