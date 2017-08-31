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


import static com.hybris.services.entitlements.validation.GeoPathConversionStrategy.GEO_PATH;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import com.hybris.services.entitlements.api.Actions;
import com.hybris.services.entitlements.condition.ActionHandler;
import com.hybris.services.entitlements.domain.Condition;
import com.hybris.services.entitlements.service.GrantService;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-service-test-spring.xml")
public class GeoConditionCheckerTest
{

	private static final String CONDITION_TYPE = "geo";
	@Autowired
	@Qualifier("geoConditionExecutor")
	private GeoConditionStrategy geoConditionStrategy;
	@Autowired
	private GrantService grantService;

	private ActionHandler checkHandler;

	@Before
	public void init()
	{
		checkHandler = geoConditionStrategy.getActionHandler(null);
	}

	@Test
	public void shouldHaveCorrectName()
	{
		assertEquals(CONDITION_TYPE, geoConditionStrategy.getConditionType());
	}

	@Test
	public void shouldImplementCheckHandler()
	{
		assertNotNull(checkHandler);
	}

	/**
	 * Returns TRUE when geoLocations belongs to given geoAreas
	 * @param geoLocation geoPoint or area
	 * @param geoAreas array of geoAreas
	 */
	private void assertGeoTrue(final String geoLocation, final String geoAreas)
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(GEO_PATH, geoLocation);
		final Condition condition = getCondition(geoAreas);
		assertTrue(checkHandler.applicable(condition, checkParams));
		assertTrue(checkHandler.execute(condition, checkParams, null));
	}


	/**
	 * Returns FALSE when geoLocations belongs to given geoAreas
	 * @param geoLocation geoPoint or area
	 * @param geoAreas array of geoAreas
	 */
	private void assertGeoFalse(final String geoLocation, final String geoAreas)
	{
		final Map<String,String> checkParams = new HashMap<>();
		checkParams.put(GEO_PATH, geoLocation);
		final Condition condition = getCondition(geoAreas);
		assertTrue(checkHandler.applicable(condition, checkParams));
		assertFalse(checkHandler.execute(condition, checkParams, null));
	}

	private Condition getCondition(final String geoAreas)
	{
		final Condition data = grantService.newCondition();
		data.setType(CONDITION_TYPE);
		data.setProperty(GEO_PATH, geoAreas);
		return data;
	}

    @Test
    @Transactional
    public void testValidateNullCityGeoLocationForSingleCityCondition()
    {
        assertFalse(geoConditionStrategy.getActionHandler(Actions.CHECK).applicable(getCondition("Germany/Bavaria/Munich"), null));
    }

	@Test
	@Transactional
	public void testValidateRightCityGeoLocationForSingleCityCondition()
	{
		assertGeoTrue("Germany/Bavaria/Munich","Germany/Bavaria/Munich");
	}

	@Test
	@Transactional
	public void testValidateWrongCityGeoLocationForSingleCityCondition()
	{
		assertGeoFalse("Germany/Bavaria/Augsburg","Germany/Bavaria/Munich");
		assertGeoFalse("Germany/Bremen/Bremen","Germany/Bavaria/Munich");
		assertGeoFalse("USA/California/San Francisco","Germany/Bavaria/Munich");
	}

	/**
	 * Checked geoPath is more common than condition geoPath */
	@Test
	@Transactional
	public void testValidateWrongRegionOrCountryGeoLocationForSingleCityCondition()
	{
		assertGeoFalse("Germany/Bavaria","Germany/Bavaria/Munich");
		assertGeoFalse("Germany/Bremen","Germany/Bavaria/Munich");
		assertGeoFalse("USA/California","Germany/Bavaria/Munich");
		assertGeoFalse("Germany","Germany/Bavaria/Munich");
		assertGeoFalse("USA","Germany/Bavaria/Munich");
	}

	@Test
	@Transactional
	public void testValidateRightRegionOrCityGeoLocationForRegionCondition()
	{
		assertGeoTrue("Germany/Bavaria","Germany/Bavaria");
		assertGeoTrue("Germany/Bavaria/Munich","Germany/Bavaria");
		assertGeoTrue("Germany/Bavaria/Augsburg","Germany/Bavaria");
	}


	@Test
	@Transactional
	public void testValidateWrongRegionOrCityGeoLocationForRegionCondition()
	{
		assertGeoFalse("Germany/Bremen","Germany/Bavaria");
		assertGeoFalse("Germany/Bremen/Bremen","Germany/Bavaria");
		assertGeoFalse("USA/California/San Francisco","Germany/Bavaria");
		assertGeoFalse("USA/California","Germany/Bavaria");
	}

	@Test
	@Transactional
	public void testValidateWrongCountryGeoLocationForRegionCondition()
	{
		assertGeoFalse("Germany","Germany/Bavaria");
		assertGeoFalse("USA","Germany/Bavaria");
	}

	@Test
	@Transactional
	public void testValidateRightCountryOrRegionOrCityGeoLocationForCountryCondition()
	{
		assertGeoTrue("Germany/Bavaria","Germany");
		assertGeoTrue("Germany/Bavaria/Munich","Germany");
		assertGeoTrue("Germany/Bavaria/Augsburg","Germany");
		assertGeoTrue("Germany/Bremen","Germany");
		assertGeoTrue("Germany/Bremen/Bremen","Germany");
	}



	@Test
	@Transactional
	public void testValidateWrongCountryOrRegionOrCityGeoLocationForRegionCondition()
	{
		assertGeoFalse("USA/California/San Francisco","Germany");
		assertGeoFalse("USA/California","Germany");
		assertGeoFalse("USA","Germany");
	}

	@Test
	@Transactional
	public void testValidateCityGeoLocationForMultiCityCondition()
	{
		assertGeoTrue("Germany/Bavaria/Munich","Germany/Bavaria/Munich, Germany/Bremen/Bremen, USA/California/San Francisco");
		assertGeoTrue("Germany/Bremen/Bremen","Germany/Bavaria/Munich, Germany/Bremen/Bremen, USA/California/San Francisco");
		assertGeoTrue("USA/California/San Francisco","Germany/Bavaria/Munich, Germany/Bremen/Bremen, USA/California/San Francisco");
		assertGeoFalse("Germany/Bavaria/Augsburg","Germany/Bavaria/Munich, Germany/Bremen/Bremen, USA/California/San Francisco");
	}

	@Test
	@Transactional
	public void testValidateCityGeoLocationForMultiCountryAndRegionAndCityCondition()
	{
		assertGeoTrue("Germany/Bavaria/Munich","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoTrue("Germany/Bremen/Bremen","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoTrue("USA/California/San Francisco","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoTrue("USA/New York/New York","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Russia/Omskaya oblast/Omsk","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Germany/Saxony/Dresden","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Germany/Bavaria/Augsburg","Germany/Bavaria/Munich, Germany/Bremen, USA");
	}

	@Test
	@Transactional
	public void testValidateCityGeoLocationForRepeatedMultiCountryAndRegionAndCityCondition()
	{
		assertGeoTrue("Germany/Bavaria/Munich","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
		assertGeoTrue("Germany/Bremen/Bremen","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
		assertGeoTrue("USA/California/San Francisco","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
		assertGeoTrue("USA/New York/New York","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
		assertGeoTrue("Russia/Omskaya oblast/Omsk","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
		assertGeoTrue("Germany/Bavaria/Augsburg","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
		assertGeoFalse("Germany/Saxony/Dresden","Germany/Bavaria/Munich, Germany/Bremen, Germany/Bavaria, USA, USA/California, Russia");
	}

	@Test
	@Transactional
	public void testValidateRegionGeoLocationForMultiCountryAndRegionAndCityCondition()
	{
		assertGeoFalse("Germany/Bavaria", "Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoTrue("Germany/Bremen", "Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoTrue("USA/California", "Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoTrue("USA/New York", "Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Russia/Omskaya oblast", "Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Germany/Saxony", "Germany/Bavaria/Munich, Germany/Bremen, USA");
	}

	@Test
	@Transactional
	public void testValidateCountryGeoLocationForMultiCountryAndRegionAndCityCondition()
	{
		assertGeoTrue("USA","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Russia","Germany/Bavaria/Munich, Germany/Bremen, USA");
		assertGeoFalse("Germany","Germany/Bavaria/Munich, Germany/Bremen, USA");
	}

	@Test
	@Transactional
	public void testValidateSpecificCases()
	{
		assertGeoFalse("USA/Texas/Russia","Russia");
		assertGeoFalse("England/Yorkshir","England/York");
	}

	@Test
	@Transactional
	public void shouldSkipTrailingSlashesInCondition()
	{
		assertGeoTrue("USA", "USA//");
		assertGeoTrue("USA/Florida", "USA//");
		assertGeoTrue("USA/California", "USA/California/");
		assertGeoTrue("USA/Florida/West Melbourne", "USA/Florida/");
	}

	@Test
	@Transactional
	public void shouldSkipTrailingSlashesInCriterion()
	{
		assertGeoTrue("USA//", "USA");
		assertGeoFalse("USA//", "USA/Florida");
		assertGeoTrue("USA/California/", "USA/California");
	}

	@Test
	@Transactional
	public void shouldSkipLeadingAndTrailingSpaces()
	{
		assertGeoTrue("Deutschland/Bayern /Nürnberg", " Deutschland / Bayern");
	}
}
