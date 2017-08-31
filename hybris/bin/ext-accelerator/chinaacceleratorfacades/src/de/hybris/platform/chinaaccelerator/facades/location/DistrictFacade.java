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
package de.hybris.platform.chinaaccelerator.facades.location;



import de.hybris.platform.chinaaccelerator.facades.data.DistrictData;

import java.util.List;



public interface DistrictFacade
{
	List<DistrictData> getDistrictsByCityCode(final String cityCode);

	DistrictData getDistrictByCode(final String districtCode);

}
