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
package de.hybris.platform.chineseproductsharingaddon.jalo;

import de.hybris.platform.chineseproductsharingaddon.constants.ChineseproductsharingaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ChineseproductsharingaddonManager extends GeneratedChineseproductsharingaddonManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( ChineseproductsharingaddonManager.class.getName() );
	
	public static final ChineseproductsharingaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ChineseproductsharingaddonManager) em.getExtension(ChineseproductsharingaddonConstants.EXTENSIONNAME);
	}
	
}
