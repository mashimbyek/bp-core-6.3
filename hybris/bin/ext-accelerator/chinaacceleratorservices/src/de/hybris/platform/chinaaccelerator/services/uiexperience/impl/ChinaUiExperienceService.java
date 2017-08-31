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
package de.hybris.platform.chinaaccelerator.services.uiexperience.impl;

//import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.acceleratorservices.uiexperience.impl.DefaultUiExperienceService;
import de.hybris.platform.commerceservices.enums.UiExperienceLevel;


public class ChinaUiExperienceService extends DefaultUiExperienceService
{

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorservices.uiexperience.impl.DefaultUiExperienceService#getDetectedUiExperienceLevel()
	 */
	@Override
	public UiExperienceLevel getDetectedUiExperienceLevel()
	{
		return UiExperienceLevel.DESKTOP;
	}

}
