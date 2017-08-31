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
package de.hybris.platform.hybrisanalyticsaddon.strategy;

/**
 * Strategy used to retrieve the token to send in the piwik payload.
 */
public interface RetrieveSessionTokenStrategy
{
	/**
	 *
	 * @return the generated token for piwik payload
	 */
	String getSessionToken();
}
