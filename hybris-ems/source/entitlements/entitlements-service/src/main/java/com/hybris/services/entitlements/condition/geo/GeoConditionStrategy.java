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
package com.hybris.services.entitlements.condition.geo;

import static com.hybris.services.entitlements.validation.GeoPathConversionStrategy.DEFAULT_GEO_ELEM_SEPARATOR;
import static com.hybris.services.entitlements.validation.GeoPathConversionStrategy.GEO_PATH;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.AbstractConditionStrategy;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.condition.ConditionParameter;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.domain.Grant;
import com.hybris.services.entitlements.validation.GeoPathConversionStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;


/**
 * Implementation of geolocation condition strategy, which requires a geoPath string in special format for both granting
 * and checking an entitlement.
 */
public class GeoConditionStrategy extends AbstractConditionStrategy
{


	/**
	 * Default path level separator.
	 */
	public static final String DEFAULT_GEO_LEVEL_SEPARATOR = "/";

	private GeoPathConversionStrategy geoPathValidator;

	@Override
	protected List<ConditionParameter> getGrantParameters()
	{
		geoPathValidator.setMultiPath(true);
		return Arrays.asList(new ConditionParameter(GEO_PATH, true, geoPathValidator));
	}

	@Override
	protected List<ConditionParameter> getExecutionParameters()
	{
		geoPathValidator.setMultiPath(false);
		return Arrays.asList(new ConditionParameter(GEO_PATH, true, geoPathValidator));
	}

	@Override
	protected void fillActionHandlers(final Map<String, ActionHandler> container)
	{
		container.put(Actions.CHECK, new CheckHandler());
	}

	@Required
	public void setGeoPathValidator(final GeoPathConversionStrategy geoPathValidatorArg)
	{
		this.geoPathValidator = geoPathValidatorArg;
	}

	/**
	 * Check action handler.
	 */
	private static class CheckHandler extends ActionHandler
	{
		@Override
		public boolean execute(final Condition condition, final Map<String, String> executionParameterMap, final Grant grant)
		{
			final String conditionGeoPath = (String) condition.getProperty(GEO_PATH);
			final String checkedGeoPath = executionParameterMap.get(GEO_PATH);

			return isChecked(checkedGeoPath, conditionGeoPath);
		}

		/**
		 * If the conditionGeoPath string is substring (from first symbol) of criterionGeoPath return true.
		 * <p/>
		 * If the conditionGeoPath string is multiple rule is more complex
		 * For each conditionGeoPath verify condition (conditionGeoPath is substring of criterionGeoPath)
		 * Break and return 'yes' if condition is right
		 *
		 * @param criterionGeoPath string from geo criterion
		 * @param conditionGeoPath string from geo condition
		 * @return true if the criterionGeoPath is checked by conditionGeoPath, otherwise false
		 */
		private boolean isChecked(final String criterionGeoPath, final String conditionGeoPath)
		{
			final String[] geoConditionItems = conditionGeoPath.split(DEFAULT_GEO_ELEM_SEPARATOR);
			final String[] geoCriterionLevels = getPathLevels(criterionGeoPath);

			for (String geoGrantItem : geoConditionItems)
			{
				if (checkOneGrantItem(geoCriterionLevels, geoGrantItem))
				{
					return true;
				}
			}
			return false;
		}

		private boolean checkOneGrantItem(final String[] geoCheckLevels, final String geoGrantItem)
		{
			final String[] geoGrantLevels = getPathLevels(geoGrantItem);

			if (geoCheckLevels.length < geoGrantLevels.length)
			{
				return false;
			}
			else
			{
				for (int i = 0; i < geoGrantLevels.length; i++)
				{
					final String geoCheckLevel = geoCheckLevels[i];
					if (!geoCheckLevel.trim().equalsIgnoreCase(geoGrantLevels[i].trim()))
					{
						return false;
					}
				}
				return true;
			}
		}

		private String[] getPathLevels(final String pathItem)
		{
			return pathItem.replaceAll("/+$", "").split(DEFAULT_GEO_LEVEL_SEPARATOR);
		}

	}


}
