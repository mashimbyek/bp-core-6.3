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
package de.hybris.platform.verticalnavigationaddon.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.verticalnavigationaddon.constants.VerticalnavigationaddonConstants;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class VerticalnavigationaddonManager extends GeneratedVerticalnavigationaddonManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( VerticalnavigationaddonManager.class.getName() );
	
	public static final VerticalnavigationaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (VerticalnavigationaddonManager) em.getExtension(VerticalnavigationaddonConstants.EXTENSIONNAME);
	}
	
}
