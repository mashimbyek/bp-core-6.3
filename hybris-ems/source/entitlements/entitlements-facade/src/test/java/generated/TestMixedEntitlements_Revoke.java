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
public class TestMixedEntitlements_Revoke
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
			robotSettings.setLogName("TestMixedEntitlements_Revoke-log");
			robotSettings.setOutputName("TestMixedEntitlements_Revoke-output");
			robotSettings.setReportName("TestMixedEntitlements_Revoke-report");

			final RobotTestSuiteFactory robotTestSuiteFactory = new PythonRobotTestSuiteFactory(pythonAware);

			robotTestSuite = robotTestSuiteFactory.parseTestSuite(robotSettings, new File(
			"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/src/test/resources/atdd/suites/MixedEntitlements_Revoke.txt"));
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
	public void Test_MixedEntitlements_Revoke_MeteredAndStringConditionType()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndStringConditionType";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndStringConditionType_x2()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndStringConditionType_x2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_x2_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_x2_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_Multi()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringConditionType_Multi";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndStringConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndStringConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndStringConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndStringConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndStringConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndStringConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndStringConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndStringConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndStringConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndStringConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndStringConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndStringConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndPathConditionType()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndPathConditionType";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndPathConditionType_x2()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndPathConditionType_x2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_x2_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_x2_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_Multi()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndPathConditionType_Multi";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndPathConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndPathConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndPathConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndPathConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndPathConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndPathConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndPathConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndPathConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndPathConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndPathConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndPathConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndPathConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndTimeConditionType()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndTimeConditionType";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndTimeConditionType_x2()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndTimeConditionType_x2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_x2_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_x2_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_Multi()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndTimeConditionType_Multi";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndTimeConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndTimeConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndTimeConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndTimeConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndTimeConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndTimeConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndTimeConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndTimeConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndTimeConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndTimeConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndTimeConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndTimeConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndGeoConditionType()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndGeoConditionType";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndGeoConditionType_x2()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndGeoConditionType_x2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_x2_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_x2_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_Multi()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndGeoConditionType_Multi";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndStringAndPathAndTimeAndGeoConditionType()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndStringAndPathAndTimeAndGeoConditionType";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_Revoke_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2()
	{
		final String robotTestName = "Test_MixedEntitlements_Revoke_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_1User()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_1User";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_Multi()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserId_MeteredAndStringAndPathAndTimeAndGeoConditionType_Multi";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementType_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSource_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndEntitlementTypeAndGrantSourceId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSource_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceAndGrantSourceId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users()
	{
		final String robotTestName = "Test_MixedEntitlements_RevokeByUserIdAndGrantSourceId_MeteredAndStringAndPathAndTimeAndGeoConditionType_x2_2Users";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

}
