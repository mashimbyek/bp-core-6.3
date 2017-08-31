/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 */
package com.hybris.services.entitlements.atdd.keywords;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Logging functionality keywords.
 */
public class LoggingKeywordLibrary
{

	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoggingKeywordLibrary.class);
    private final Map<String, RecordingAppender> recorders = new HashMap<>();

	/**
	 * Register logging appender.
	 *
	 * @param loggerName logger name
	 */
    public void registerNewAppender(final String loggerName)
    {
        if (recorders.get(loggerName) == null)
		{
            final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            final Logger logger = context.getLogger(loggerName);

            final RecordingAppender recorder = new RecordingAppender();
            recorder.start();

            logger.addAppender(recorder);

            recorders.put(loggerName, recorder);
        }
        else
        {
            recorders.get(loggerName).getMessages().clear();
        }
    }

	/**
	 * Check if message is correct.
	 *
	 * @param loggerName logger name
	 * @param messageIndex message position
	 * @param pattern expected pattern
	 */
    public void assertMessageMatches(final String loggerName, final int messageIndex, final String pattern)
	{
        Assert.assertTrue(recorders.get(loggerName).getMessages().get(messageIndex - 1).matches(pattern));
    }

	/**
	 * Check if there are any messages.
	 *
	 * @param loggerName logger name
	 * @param count number of message found in log
	 */
    public void assertHasMessages(final String loggerName, final int count)
	{
        Assert.assertEquals(loggerName + " message count does not match", recorders.get(loggerName).getMessages().size(), count);
    }

	private class RecordingAppender extends AppenderBase<ILoggingEvent>
	{

		private List<String> messages = new ArrayList<>();

		@Override
		protected void append(final ILoggingEvent event)
		{
			this.messages.add(event.getMessage());
		}

		public List<String> getMessages()
		{
			return messages;
		}
	}
}
