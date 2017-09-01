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
package de.hybris.platform.addressservices.address.impl;

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.addressservices.address.daos.AddressDao;
import de.hybris.platform.addressservices.model.CityModel;
import de.hybris.platform.addressservices.model.DistrictModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@UnitTest
public class ChineseAddressServiceTest
{
	private static final String ISO_CODE_EN = "en";

	private static final String ISO_CODE_ZH = "zh";

	@Mock
	private AddressDao chineseAddressDao;

	private ChineseAddressService chineseAddressService;

	private CityModel city;

	private DistrictModel district;

	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);

		chineseAddressService = new ChineseAddressService();
		chineseAddressService.setChineseAddressDao(chineseAddressDao);

		city = new CityModel();
		district = new DistrictModel();
	}

	@Test
	public void testGetEmptyCitiesForRegion()
	{
		given(chineseAddressDao.getCitiesForRegion(Mockito.any())).willReturn(Collections.emptyList());
		List<CityModel> cities = chineseAddressService.getCitiesForRegion(ISO_CODE_ZH);
		Assert.assertTrue(cities.isEmpty());
	}

	@Test
	public void testGetCitiesForRegion()
	{
		List<CityModel> cities = new ArrayList<>();
		cities.add(city);
		Mockito.doReturn(cities).when(chineseAddressDao).getCitiesForRegion(Mockito.any());
		List<CityModel> result = chineseAddressService.getCitiesForRegion(ISO_CODE_EN);
		Assert.assertEquals(cities, result);
	}

	@Test
	public void testGetEmptyDistrictsForCity()
	{
		given(chineseAddressDao.getDistrictsForCity(Mockito.any())).willReturn(Collections.emptyList());
		List<DistrictModel> districts = chineseAddressService.getDistrictsForCity(ISO_CODE_ZH);
		Assert.assertTrue(districts.isEmpty());
	}

	@Test
	public void testGetDistrictsForCity()
	{
		List<DistrictModel> districts = new ArrayList<>();
		districts.add(district);
		Mockito.doReturn(districts).when(chineseAddressDao).getDistrictsForCity(Mockito.any());
		List<DistrictModel> result = chineseAddressService.getDistrictsForCity(ISO_CODE_EN);
		Assert.assertEquals(districts, result);
	}

	@Test
	public void testGetCityForIsocode()
	{
		Mockito.doReturn(city).when(chineseAddressDao).getCityForIsocode(Mockito.any());
		CityModel result = chineseAddressService.getCityForIsocode(ISO_CODE_EN);
		Assert.assertEquals(city, result);
	}

	@Test
	public void testGetDistrictForIsocode()
	{
		Mockito.doReturn(district).when(chineseAddressDao).getDistrictForIsocode(Mockito.any());
		DistrictModel result = chineseAddressService.getDistrictForIsocode(ISO_CODE_EN);
		Assert.assertEquals(district, result);
	}
}
