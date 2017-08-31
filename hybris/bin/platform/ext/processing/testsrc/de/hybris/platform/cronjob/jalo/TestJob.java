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
package de.hybris.platform.cronjob.jalo;

import de.hybris.platform.cronjob.jalo.CronJob.CronJobResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class TestJob extends Job implements TriggerableJob
{
	private Performable performable = null;

	public interface Performable
	{
		CronJobResult perform(CronJob cronJob);
	}

	private static final Logger log = Logger.getLogger(TestJob.class);

	/**
	 * Map
	 */
	private final Map<String, Boolean> cronJobAttributeWritable = new HashMap<String, Boolean>();

	@Override
	protected CronJobResult performCronJob(final CronJob cronJob) throws AbortCronJobException
	{
		if (performable != null)
		{
			return performable.perform(cronJob);
		}
		else
		{
			final long count = ((Long) cronJob.getProperty("count")).longValue();
			log.isDebugEnabled();

			final long start = System.currentTimeMillis();
			for (int i = 0; i < count; i++)
			{
				log.isDebugEnabled();
			}
			final long end = (System.currentTimeMillis() - start);

			log.warn("nested");

			cronJob.setProperty("time", Long.valueOf(end));
			return cronJob.getFinishedResult(true);
		}
	}

	public void setCronJobAttributeWritable(final String attributeQualifier, final boolean writable)
	{
		cronJobAttributeWritable.put(attributeQualifier, writable);
	}

	public void setPerformable(final Performable performable)
	{
		this.performable = performable;
	}

	@Override
	public CronJob newExecution()
	{
		final CronJob cronJob = CronJobManager.getInstance().createCronJob(Collections.singletonMap(CronJob.JOB, this));
		if (cronJobAttributeWritable != null)
		{
			for (final String attributeQualifier : cronJobAttributeWritable.keySet())
			{
				cronJob.getComposedType().getAttributeDescriptor(attributeQualifier)
						.setWritable(cronJobAttributeWritable.get(attributeQualifier));
			}
		}
		return cronJob;
	}

	public static Logger getLog()
	{
		return log;
	}
}
