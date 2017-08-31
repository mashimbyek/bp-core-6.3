/*
 *  
 * [y] hybris Platform
 *  
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *  
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 *  
 */
package de.hybris.platform.chinesestoreaddon.jalo;

import de.hybris.platform.chinesestoreaddon.constants.ChinesestoreaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ChinesestoreaddonManager extends GeneratedChinesestoreaddonManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( ChinesestoreaddonManager.class.getName() );
	
	public static final ChinesestoreaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ChinesestoreaddonManager) em.getExtension(ChinesestoreaddonConstants.EXTENSIONNAME);
	}
	
}
