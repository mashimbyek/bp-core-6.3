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

package generated;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import com.hybris.atddengine.framework.*;
import com.hybris.atddengine.framework.impl.DefaultPythonProvider;
import com.hybris.atddengine.framework.impl.PythonAware;
import com.hybris.atddengine.framework.impl.PythonRobotTestSuiteFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.commons.lang.StringUtils;


/*
 * This is a generated class. Do not edit, changes will be overridden.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/entitlement-rest-client-test-spring.xml")
@SuppressWarnings("PMD")
public class TestNonMeteredEntitlements_GeoConditionType_CreateAndCheck
{
	public static RobotTestSuite robotTestSuite;

	public static void startSuite() throws IOException
	{
		if (robotTestSuite == null)
		{
			final PythonAware pythonAware = new DefaultPythonProvider(
				"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-rest-client/target/dependencies/atdd/keywords",
				"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-rest-client/src/test/resources/atdd/keywords"
			);
			final RobotSettings robotSettings = new RobotSettings();

			robotSettings.setOutputDir(new File("/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-rest-client/target/atdd/reports"));
			robotSettings.setLogName("TestNonMeteredEntitlements_GeoConditionType_CreateAndCheck-log");
			robotSettings.setOutputName("TestNonMeteredEntitlements_GeoConditionType_CreateAndCheck-output");
			robotSettings.setReportName("TestNonMeteredEntitlements_GeoConditionType_CreateAndCheck-report");

			final RobotTestSuiteFactory robotTestSuiteFactory = new PythonRobotTestSuiteFactory(pythonAware);

			robotTestSuite = robotTestSuiteFactory.parseTestSuite(robotSettings, new File(
			"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-rest-client/src/test/resources/atdd/suites/NonMeteredEntitlements_GeoConditionType_CreateAndCheck.txt"));
        }

        if (!robotTestSuite.isStarted())
        {
        	robotTestSuite.start();
        }

    }

	@AfterClass
	public static void tearDownSuite()
	{
		robotTestSuite.close();
	}

	private RobotTest currentRobotTest;

	@Before
	public void setUp() throws IOException
	{
		startSuite();
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCity_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCity_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CityTrimForCheck()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CityTrimForCheck";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_RegionTrimForCheck()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_RegionTrimForCheck";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CountryTrimForCheck()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CountryTrimForCheck";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCityRegionCountry_TrimForCheck()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCityRegionCountry_TrimForCheck";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CityTrimForGrant()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CityTrimForGrant";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_RegionTrimForGrant()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_RegionTrimForGrant";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CountryTrimForGrant()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityRegionCountry_CountryTrimForGrant";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCityRegionCountry_TrimForGrant()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCityRegionCountry_TrimForGrant";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCity_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCity_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCity_False_No1()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCity_False_No1";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCity_False_No2()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCity_False_No2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCity_False_No3()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCity_False_No3";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCity_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCity_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCityAndRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCityAndRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCityAndRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCityAndRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCityRegionAndCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCityRegionAndCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCityRegionAndCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCityRegionAndCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityInWrongRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCityInWrongRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCityInWrongRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCityInWrongRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckRegionOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckRegionOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckRegionOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckRegionOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongRegionOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongRegionOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMutliCity_CheckWrongRegionOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMutliCity_CheckWrongRegionOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCountryAndRegionOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCountryAndRegionOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCountryAndRegionOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckWrongCountryAndRegionOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCountryOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckCountryOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCountryOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCity_CheckCountryOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCity_CheckWrongCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMutliCity_CheckWrongCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMutliCity_CheckWrongCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckRegion_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckRegion_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckRegion_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckRegion_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCity_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCity_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckCity_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckCity_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegion_False_No1()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegion_False_No1";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegion_False_No2()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegion_False_No2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegion_False_No3()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegion_False_No3";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckWrongRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckWrongRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCityinWrongRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCityinWrongRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMutliRegion_CheckCityInWrongRegion_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMutliRegion_CheckCityInWrongRegion_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegionAndCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongRegionAndCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckWrongRegionAndCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckWrongRegionAndCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCityInWrongRegionAndCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCityInWrongRegionAndCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckCityInWrongRegionAndCountry_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckCityInWrongRegionAndCountry_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCountryOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckCountryOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckCountryOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckCountryOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongCountryOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateRegion_CheckWrongCountryOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckWrongCountryOnly_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiRegion_CheckWrongCountryOnly_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckCity_true()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckCity_true";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckCity_true()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckCity_true";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckRegion_true()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckRegion_true";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckRegion_true()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckRegion_true";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckCountry_true()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckCountry_true";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckCountry_true()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckCountry_true";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCityRegionAndCountry_false()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCityRegionAndCountry_false";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckWrongCityRegionAndCountry_false()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckWrongCityRegionAndCountry_false";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongRegionAndCountry_false()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongRegionAndCountry_false";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckWrongRegionAndCountry_false()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckWrongRegionAndCountry_false";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCountry_false_No1()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCountry_false_No1";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCountry_false_No2()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCountry_false_No2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCountry_false_No3()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateCountry_CheckWrongCountry_false_No3";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckWrongCountry_false()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CreateMultiCountry_CheckWrongCountry_false";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithMeteredCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CheckWithMeteredCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_2Users_2Entitlements()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_2Users_2Entitlements";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Country_Separators_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Country_Separators_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Country_Separators_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Country_Separators_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegion_Separators_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegion_Separators_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegion_Separators_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegion_Separators_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegionAndCity_Separators_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegionAndCity_Separators_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegionAndCity_Separators_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegionAndCity_Separators_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndCity_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndCity_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_City_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_City_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Region_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Region_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegionAndCity()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_CountryAndRegionAndCity";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Country_NoSeparators()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_GeoConditionType_CreateAndCheck_Country_NoSeparators";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_MulitpleEntitlements_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_MulitpleEntitlements_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

}
