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

import com.hybris.services.entitlements.api.EntitlementFacade;
import com.hybris.services.entitlements.condition.AbstractConditionData;
import com.hybris.services.entitlements.conversion.DateTimeConverter;
import com.hybris.services.entitlements.atdd.KeyWordException;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class for keyword implementations.
 */
@Configurable
public abstract class AbstractEntitlementsKeywordLibrary
{

    private static final Logger LOG = LoggerFactory.getLogger(AbstractEntitlementsKeywordLibrary.class);

    private static final String SPLITTER = Pattern.quote("|");

    private static final Pattern CONDITION_REGEXP_PATTERN = Pattern.compile("(\".*\") condition with parameters (\\{.*\\})");

    private static final int CONDITION_TYPE_INDEX = 1;
    private static final int CONDITION_BODY_INDEX = 2;

    private static final String NOW_TIME_TOKEN = "now";

    @Autowired
    private DateTimeConverter dateTimeConverter;

	@Autowired
	@Qualifier("entitlementFacade")
	private EntitlementFacade entitlementFacade;

	public EntitlementFacade getEntitlementFacade()
	{
		return entitlementFacade;
	}

	protected List<String> splitParameters(final String params)
    {
        final List<String> ret = new ArrayList<>();

        for (String str : params.split(SPLITTER))
        {
            ret.add(str.trim());
        }

        return ret;
    }

    protected <T extends AbstractConditionData> T parseCondition(final String condition, final Class<T> dataClass)
    {
        final Matcher matcher = CONDITION_REGEXP_PATTERN.matcher(condition);

        if (!matcher.matches())
        {
            throw new KeyWordException("Error. Wrong condition format");
        }

        try
        {
            final JSONObject jsonObject = new JSONObject(matcher.group(CONDITION_BODY_INDEX));
            final T conditionData = dataClass.newInstance();
            conditionData.setType(matcher.group(CONDITION_TYPE_INDEX).replaceAll("\"", ""));

            for (final Iterator keys = jsonObject.keys(); keys.hasNext();)
            {
                final String key = (String) keys.next();
                String value = jsonObject.getString(key);

                if (hasNowTimeToken(value))
                {
                    value = transformNowTime(value);
                }
                conditionData.setProperty(key, value);
            }

            return conditionData;
        }
        catch (final JSONException e)
        {
            throw new KeyWordException("Error. Wrong condition format. JSON - section");
        }
        catch(final InstantiationException | IllegalAccessException e)
        {
            throw new KeyWordException("Error while parsing condition", e);
        }
    }

    private String transformNowTime(final String value)
    {
        final String trimmedValue = value.toLowerCase().replaceAll("\\s+", "").replace(NOW_TIME_TOKEN, "");
        final Integer delta = Integer.parseInt(trimmedValue);
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, delta);

        return dateTimeConverter.convertDateToString(calendar.getTime());
    }

    private boolean hasNowTimeToken(final String value)
    {
        return value.toLowerCase().replaceAll("\\s+", "").contains(NOW_TIME_TOKEN);
    }

    /**
     * Executes the given callback and expects an exception of the given type with the given message.
     *
     * @param callback callback that should raise the expected exception
     * @param expectedExceptionType type of expected exception, may be null if no exception is expected
     * @param expectedExceptionMessage message of expected exception, may be null if no exception is expected
     * @param <T> return type of the callback
	 * @return value returned by the callback
     */
    protected static <T> T executeAndExpectException(final ExpectExceptionCallback<T> callback, final String expectedExceptionType,
        final String expectedExceptionMessage)
	{
        try
		{
            final T result = callback.execute();

            if (StringUtils.isNotEmpty(expectedExceptionType))
			{
                Assert.fail(String.format("Expected exception of type %s was not raised", expectedExceptionType));
            }

            if (StringUtils.isNotEmpty(expectedExceptionMessage))
			{
                Assert.fail(String.format("Expected exception with message  \"%s\" was not raised", expectedExceptionMessage));
            }

            return result;
        }
		catch (final Exception e)
		{
            LOG.error("Caught exception!", e);

            if (StringUtils.isNotEmpty(expectedExceptionType))
			{
                Assert.assertEquals("Expected exception type does not match", expectedExceptionType, e.getClass().getSimpleName());
            }

            if (StringUtils.isNotEmpty(expectedExceptionMessage))
			{
                Assert.assertEquals("Expected exception message does not match", expectedExceptionMessage, e.getMessage());
            }

			if (StringUtils.isEmpty(expectedExceptionType) && StringUtils.isEmpty(expectedExceptionMessage))
			{
				throw new IllegalArgumentException(e.getMessage(), e);
			}
        }

        // will never happen
        return null;
    }

	protected interface ExpectExceptionCallback<T>
	{
		T execute() throws Exception;
	}

}
