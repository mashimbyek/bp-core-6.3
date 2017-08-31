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
public class TestMixedEntitlements_Update
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
			robotSettings.setLogName("TestMixedEntitlements_Update-log");
			robotSettings.setOutputName("TestMixedEntitlements_Update-output");
			robotSettings.setReportName("TestMixedEntitlements_Update-report");

			final RobotTestSuiteFactory robotTestSuiteFactory = new PythonRobotTestSuiteFactory(pythonAware);

			robotTestSuite = robotTestSuiteFactory.parseTestSuite(robotSettings, new File(
			"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/src/test/resources/atdd/suites/MixedEntitlements_Update.txt"));
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
	public void Test_MixedEntitlements_Update_GrantSource_And_GrantSourceId()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_GrantSource_And_GrantSourceId";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_WithoutConditions()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_WithoutConditions";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_StringCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_StringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_PathCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_PathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_TimeframeCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_TimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_GeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_GeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_MeteredCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_MeteredCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_MeteredAndStringCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_MeteredAndStringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_StringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_StringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndStringCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndStringCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_WithoutConditions()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_WithoutConditions";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_StringCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_StringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_PathCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_PathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_TimeframeCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_TimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_GeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_GeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_MeteredCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_MeteredCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_MeteredAndPathCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_MeteredAndPathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_StringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_StringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndPathCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndPathCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_WithoutConditions()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_WithoutConditions";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_StringCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_StringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_PathCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_PathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_TimeframeCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_TimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_GeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_GeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_MeteredCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_MeteredCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_MeteredAndTimeframeCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_MeteredAndTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_StringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_StringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndTimeframeCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_WithoutConditions()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_WithoutConditions";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_StringCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_StringCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_PathCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_PathCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_TimeframeCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_TimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_GeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_GeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_MeteredCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_MeteredCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_MeteredAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_MeteredAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_StringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_StringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition()
	{
		final String robotTestName = "Test_MixedEntitlements_Update_MeteredAndGeoCondition_To_MeteredAndStringAndPathAndTimeframeAndGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

}
