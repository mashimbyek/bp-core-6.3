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
package de.hybris.platform.consignmenttrackingmockaddon.jalo;

import de.hybris.platform.consignmenttrackingmockaddon.constants.ConsignmenttrackingmockaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ConsignmenttrackingmockaddonManager extends GeneratedConsignmenttrackingmockaddonManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( ConsignmenttrackingmockaddonManager.class.getName() );
	
	public static final ConsignmenttrackingmockaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ConsignmenttrackingmockaddonManager) em.getExtension(ConsignmenttrackingmockaddonConstants.EXTENSIONNAME);
	}
	
}
