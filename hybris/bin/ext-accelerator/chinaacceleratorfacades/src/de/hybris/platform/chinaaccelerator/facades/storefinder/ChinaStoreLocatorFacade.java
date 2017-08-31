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
package de.hybris.platform.chinaaccelerator.facades.storefinder;


import de.hybris.platform.chinaaccelerator.facades.StoreData;
import de.hybris.platform.chinaaccelerator.facades.data.CityData;

import java.util.List;


public interface ChinaStoreLocatorFacade extends de.hybris.platform.commercefacades.storelocator.StoreLocatorFacade
{
	/**
	 * @return the list of CityData which have at least one store
	 */
	List<CityData> getCitiesOnlyWithStores();

	/**
	 * @return the list of CityData no matter how many stores the city has
	 */
	List<CityData> getAllCities();

	/**
	 * @return the list of PointOfServiceData of the given cities
	 */
	List<StoreData> getStoresByCities(long cityId);
}
