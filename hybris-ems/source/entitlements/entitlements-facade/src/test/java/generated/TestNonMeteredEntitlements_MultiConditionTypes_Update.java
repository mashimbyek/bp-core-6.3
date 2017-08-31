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
@ContextConfiguration("/META-INF/entitlement-facade-test-spring.xml")
@SuppressWarnings("PMD")
public class TestNonMeteredEntitlements_MultiConditionTypes_Update
{
	public static RobotTestSuite robotTestSuite;

	public static void startSuite() throws IOException
	{
		if (robotTestSuite == null)
		{
			final PythonAware pythonAware = new DefaultPythonProvider(
				"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/target/dependencies/atdd/keywords",
				"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/src/test/resources/atdd/keywords"
			);
			final RobotSettings robotSettings = new RobotSettings();

			robotSettings.setOutputDir(new File("/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/target/atdd/reports"));
			robotSettings.setLogName("TestNonMeteredEntitlements_MultiConditionTypes_Update-log");
			robotSettings.setOutputName("TestNonMeteredEntitlements_MultiConditionTypes_Update-output");
			robotSettings.setReportName("TestNonMeteredEntitlements_MultiConditionTypes_Update-report");

			final RobotTestSuiteFactory robotTestSuiteFactory = new PythonRobotTestSuiteFactory(pythonAware);

			robotTestSuite = robotTestSuiteFactory.parseTestSuite(robotSettings, new File(
			"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/src/test/resources/atdd/suites/NonMeteredEntitlements_MultiConditionTypes_Update.txt"));
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
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_StringAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_StringAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_StringAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_StringAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_PathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_PathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_GeoAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_GeoAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_GeoAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_GeoAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_GeoAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_GeoAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_StringAndPathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_WithoutCondition_To_StringAndPathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_NewPathAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_NewPathAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_NewPathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_NewPathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_NewPathAndGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_NewPathAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_NewTimeframeAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_NewTimeframeAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_NewTimeframeAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_NewTimeframeAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_NewTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_NewTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_NewGeoAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_NewGeoAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_NewGeoAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_NewGeoAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_NewGeoAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_NewGeoAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_PreviousStringAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_PreviousStringAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_PreviousStringAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_PreviousStringAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_PreviousStringAndGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_PreviousStringAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_PreviousPathAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_PreviousPathAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_PreviousPathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_PreviousPathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_PreviousPathAndGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_PreviousPathAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_PreviousTimeframeAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_PreviousTimeframeAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_PreviousTimeframeAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_PreviousTimeframeAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_PreviousTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_PreviousTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_PreviousGeoAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_PreviousGeoAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_PreviousGeoAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_PreviousGeoAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_PreviousGeoAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_GeoCondition_To_PreviousGeoAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndPathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringCondition_To_NewStringAndPathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_StringAndNewPathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathCondition_To_StringAndNewPathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_StringAndPathAndNewTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeCondition_To_StringAndPathAndNewTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_PreviousStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_PreviousStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_PreviousStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_PreviousStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndStringCondition_To_PreviousPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndStringCondition_To_PreviousPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_PreviousPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_PreviousPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndStringCondition_To_PreviousTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndStringCondition_To_PreviousTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndPathCondition_To_PreviousTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndPathCondition_To_PreviousTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_NewStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_NewStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_NewStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_NewStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndStringCondition_To_NewPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndStringCondition_To_NewPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_NewPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_NewPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndStringCondition_To_NewTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndStringCondition_To_NewTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndPathCondition_To_NewTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_TimeframeAndPathCondition_To_NewTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_PreviousStringAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_PreviousStringAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_PreviousStringAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_PreviousStringAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_PreviousPathAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_PreviousPathAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_PreviousStringAndPreviousPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathCondition_To_PreviousStringAndPreviousPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_PreviousStringAndPreviousTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_PreviousStringAndPreviousTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_PreviousPathAndPreviousTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_PathAndTimeframeCondition_To_PreviousPathAndPreviousTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_NewStringAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_NewStringAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_NewStringAndNewTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndTimeframeCondition_To_NewStringAndNewTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_WithoutCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_WithoutCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_StringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_StringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_PathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_PathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_TimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_TimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_StringAndPathCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_StringAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_StringAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_StringAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_PathAndTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_PathAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_TimeframeAndPathAndStringCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ConditionType_StringAndPathAndTimeframeCondition_To_TimeframeAndPathAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_GrantSource_And_GrantSourceId()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_GrantSource_And_GrantSourceId";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_ClearConditions()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_ClearConditions";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_MultiConditionTypes_Update_DeleteConditionByType()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_MultiConditionTypes_Update_DeleteConditionByType";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

}
