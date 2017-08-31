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
package de.hybris.platform.chinesepaymentaddon.jalo;

import de.hybris.platform.chinesepaymentaddon.constants.ChinesepaymentaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ChinesepaymentaddonManager extends GeneratedChinesepaymentaddonManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( ChinesepaymentaddonManager.class.getName() );
	
	public static final ChinesepaymentaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ChinesepaymentaddonManager) em.getExtension(ChinesepaymentaddonConstants.EXTENSIONNAME);
	}
	
}
