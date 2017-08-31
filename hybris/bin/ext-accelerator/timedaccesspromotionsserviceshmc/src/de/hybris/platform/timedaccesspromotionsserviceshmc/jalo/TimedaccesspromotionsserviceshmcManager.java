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
package de.hybris.platform.timedaccesspromotionsserviceshmc.jalo;

import de.hybris.platform.core.Registry;
import de.hybris.platform.util.JspContext;

import java.util.Map;

import org.apache.log4j.Logger;


public class TimedaccesspromotionsserviceshmcManager extends GeneratedTimedaccesspromotionsserviceshmcManager
{
	private static final Logger LOG = Logger.getLogger(TimedaccesspromotionsserviceshmcManager.class.getName());

	public static TimedaccesspromotionsserviceshmcManager getInstance()
	{
		return (TimedaccesspromotionsserviceshmcManager) Registry.getCurrentTenant().getJaloConnection().getExtensionManager()
				.getExtension("timedaccesspromotionsserviceshmc");
	}

	public TimedaccesspromotionsserviceshmcManager()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("constructor of TimedaccesspromotionsserviceshmcManager called.");
		}
	}

	@Override
	public void init()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("init() of TimedaccesspromotionsserviceshmcManager called. " + getTenant().getTenantID());
		}
	}

	@Override
	public void destroy()
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("destroy() of TimedaccesspromotionsserviceshmcManager called, current tenant: " + getTenant().getTenantID());
		}
	}

	@Override
	public void createEssentialData(final Map<String, String> params, final JspContext jspc)
	{
		//
	}

	@Override
	public void createProjectData(final Map<String, String> params, final JspContext jspc)
	{
		//
	}
}
