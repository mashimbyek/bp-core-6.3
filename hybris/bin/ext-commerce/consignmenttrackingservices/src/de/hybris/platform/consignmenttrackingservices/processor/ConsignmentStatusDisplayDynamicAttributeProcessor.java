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
package de.hybris.platform.consignmenttrackingservices.processor;

import de.hybris.platform.acceleratorservices.attribute.ConsignmentStatusDisplayDynamicAttributeHandler;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * After initialization of ConsignmentStatusDisplayDynamicAttributeHandler, add new definition entry to original
 * StatusDisplayMap.
 */
public class ConsignmentStatusDisplayDynamicAttributeProcessor implements BeanPostProcessor
{

	private Map<ConsignmentStatus, String> consignmentStatusDisplayMap;

	@Override
	public Object postProcessBeforeInitialization(final Object object, final String param) throws BeansException
	{
		return object;
	}

	@Override
	public Object postProcessAfterInitialization(final Object object, final String param) throws BeansException
	{
		if (object instanceof ConsignmentStatusDisplayDynamicAttributeHandler)
		{
			final ConsignmentStatusDisplayDynamicAttributeHandler attributeHandler = (ConsignmentStatusDisplayDynamicAttributeHandler) object;
			attributeHandler.setStatusDisplayMap(consignmentStatusDisplayMap);
		}
		return object;
	}

	protected Map<ConsignmentStatus, String> getConsignmentStatusDisplayMap()
	{
		return consignmentStatusDisplayMap;
	}

	@Required
	public void setConsignmentStatusDisplayMap(final Map<ConsignmentStatus, String> consignmentStatusDisplayMap)
	{
		this.consignmentStatusDisplayMap = consignmentStatusDisplayMap;
	}
}
