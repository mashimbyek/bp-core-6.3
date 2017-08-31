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
package com.hybris.services.entitlements.validation;

/**
 * Validates geoPath string in special format for both granting
 * and checking an entitlement.
 * <p>
 * Format for checking is GeoPath
 * 'Country/Region/City'.
 * <p>
 * Example: 'Germany/Bavaria/Munich'
 * or 'Country/Region' or 'Country'
 * <p>
 * Format for granting (is a multistring separated by comma ',' where each string has format GeoPath).
 * <p>
 * Example: 'Germany/Bavaria/Munich, Germany/Bremen, USA'
 *
 */
public class GeoPathConversionStrategy extends AbstractConversionStrategy<String>
{

	/**
	 * Default path item separator.
	 */
	public static final String DEFAULT_GEO_ELEM_SEPARATOR = ",";
	/**
	 * Condition value.
	 */
	public static final String GEO_PATH = "geoPath";
	/**
	 * GEO_PATH_FORMAT_ERROR_MESSAGE.
	 */
	public static final String GEO_PATH_FORMAT_ERROR_MESSAGE = "Please specify geoPath as"
			+ " 'Germany/Bavaria/Munich, Germany/Bremen, USA'";

	private boolean multiPath;

	@Override
	public void validate(final String field, final String value, final ValidationViolations errors)
	{
		if(value == null)
		{
			errors.add(field, null, GEO_PATH_FORMAT_ERROR_MESSAGE);
		}
		else
		{
			if(isInvalidFormat(value, isMultiPath()))
			{
				errors.add(field, value, GEO_PATH_FORMAT_ERROR_MESSAGE);
			}
		}
	}

	@Override
	public String convert(final Object value)
	{
		return value.toString();
	}


	/**
	 * This method validates geoPath (each subGeoPath in multiGeoPath) according regular expression.
	 * <p>
	 * The geoPath can contain spaces, latin symbols and digits.
	 * </p>
	 * @param geoPath  string for validation according geoPath format
	 * @return if format is invalid
	 */
	private boolean isInvalidFormat(final String geoPath, final boolean multiGeoPath)
	{
		final String regex = "[^/]+(/[^/]+){0,2}";

		if(multiGeoPath)
		{
			final String [] geoAreas = geoPath.split(DEFAULT_GEO_ELEM_SEPARATOR);
			for(String geoArea : geoAreas)
			{
				final String trimmedGeoArea = geoArea.trim();

				if (trimmedGeoArea.matches(regex))
				{
					continue;
				}
				return true;
			}
			return false;
		}
		else
		{
			final String trimmedGeoArea = geoPath.trim();
			return !trimmedGeoArea.matches(regex);
		}


	}

	public void setMultiPath(final boolean multiPath)
	{
		this.multiPath = multiPath;
	}

	public boolean isMultiPath()
	{
		return multiPath;
	}
}
