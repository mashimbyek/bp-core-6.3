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
public class AddUsageTest
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
			robotSettings.setLogName("AddUsageTest-log");
			robotSettings.setOutputName("AddUsageTest-output");
			robotSettings.setReportName("AddUsageTest-report");

			final RobotTestSuiteFactory robotTestSuiteFactory = new PythonRobotTestSuiteFactory(pythonAware);

			robotTestSuite = robotTestSuiteFactory.parseTestSuite(robotSettings, new File(
			"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-rest-client/src/test/resources/atdd/suites/AddUsageTest.txt"));
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
	public void Test_addUsage_NotFoundException()
	{
		final String robotTestName = "Test_addUsage_NotFoundException";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_addUsage_UndefinedProperty()
	{
		final String robotTestName = "Test_addUsage_UndefinedProperty";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_addUsage_positive()
	{
		final String robotTestName = "Test_addUsage_positive";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_addUsage_negative()
	{
		final String robotTestName = "Test_addUsage_negative";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

}
