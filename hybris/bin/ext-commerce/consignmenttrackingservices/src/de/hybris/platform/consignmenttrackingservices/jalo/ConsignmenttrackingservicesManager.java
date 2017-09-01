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
package de.hybris.platform.consignmenttrackingservices.jalo;

import de.hybris.platform.consignmenttrackingservices.constants.ConsignmenttrackingservicesConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class ConsignmenttrackingservicesManager extends GeneratedConsignmenttrackingservicesManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( ConsignmenttrackingservicesManager.class.getName() );
	
	public static final ConsignmenttrackingservicesManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (ConsignmenttrackingservicesManager) em.getExtension(ConsignmenttrackingservicesConstants.EXTENSIONNAME);
	}
	
}
