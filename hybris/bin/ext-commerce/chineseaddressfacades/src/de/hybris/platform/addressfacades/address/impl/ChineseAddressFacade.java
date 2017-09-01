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
package de.hybris.platform.addressfacades.address.impl;

import de.hybris.platform.addressfacades.address.AddressFacade;
import de.hybris.platform.addressfacades.data.CityData;
import de.hybris.platform.addressfacades.data.DistrictData;
import de.hybris.platform.addressfacades.populators.CityPopulator;
import de.hybris.platform.addressfacades.populators.DistrictPopulator;
import de.hybris.platform.addressservices.address.AddressService;
import de.hybris.platform.addressservices.model.CityModel;
import de.hybris.platform.addressservices.model.DistrictModel;
import de.hybris.platform.addressservices.strategies.PostcodeValidateStrategy;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Required;


public class ChineseAddressFacade implements AddressFacade
{
	private AddressService chineseAddressService;
	private CityPopulator cityPopulator;
	private DistrictPopulator districtPopulator;
	private PostcodeValidateStrategy postcodeValidateStrategy;

	@Override
	public CityData getCityForIsocode(final String isocode)
	{
		final CityData cityData = new CityData();
		final CityModel cityModel = chineseAddressService.getCityForIsocode(isocode);
		if (cityModel != null)
		{
			cityPopulator.populate(cityModel, cityData);
		}
		return cityData;
	}

	@Override
	public DistrictData getDistrcitForIsocode(final String isocode)
	{
		final DistrictData districtData = new DistrictData();
		final DistrictModel districtModel = chineseAddressService.getDistrictForIsocode(isocode);
		if (districtModel != null)
		{
			districtPopulator.populate(districtModel, districtData);
		}
		return districtData;
	}

	@Override
	public List<CityData> getCitiesForRegion(final String regionCode)
	{
		final List<CityModel> cityModels = chineseAddressService.getCitiesForRegion(regionCode);
		return cityModels.stream().map(cityModel -> {
			final CityData cityData = new CityData();
			cityPopulator.populate(cityModel, cityData);
			return cityData;
		}).sorted((cityData1, cityData2) -> cityData1.getName().compareTo(cityData2.getName())).collect(Collectors.toList());
	}

	@Override
	public List<DistrictData> getDistrictsForCity(final String cityCode)
	{
		final List<DistrictModel> districtModels = chineseAddressService.getDistrictsForCity(cityCode);
		return districtModels.stream().map(districtModel -> {
			final DistrictData districtData = new DistrictData();
			districtPopulator.populate(districtModel, districtData);
			return districtData;
		}).sorted((districtData1, districtData2) -> districtData1.getName().compareTo(districtData2.getName())).collect(Collectors.toList());
	}

	@Override
	public boolean validatePostcode(String postcode)
	{
		return getPostCodeValidateStrategy().validate(postcode);
	}


	protected AddressService getChineseAddressService()
	{
		return chineseAddressService;
	}

	@Required
	public void setChineseAddressService(final AddressService chineseAddressService)
	{
		this.chineseAddressService = chineseAddressService;
	}

	protected CityPopulator getCityPopulator()
	{
		return cityPopulator;
	}

	@Required
	public void setCityPopulator(final CityPopulator cityPopulator)
	{
		this.cityPopulator = cityPopulator;
	}

	protected DistrictPopulator getDistrictPopulator()
	{
		return districtPopulator;
	}

	@Required
	public void setDistrictPopulator(final DistrictPopulator districtPopulator)
	{
		this.districtPopulator = districtPopulator;
	}

	protected PostcodeValidateStrategy getPostCodeValidateStrategy()
	{
		return postcodeValidateStrategy;
	}

	@Required
	public void setPostcodeValidateStrategy(final PostcodeValidateStrategy postcodeValidateStrategy)
	{
		this.postcodeValidateStrategy = postcodeValidateStrategy;
	}

}
