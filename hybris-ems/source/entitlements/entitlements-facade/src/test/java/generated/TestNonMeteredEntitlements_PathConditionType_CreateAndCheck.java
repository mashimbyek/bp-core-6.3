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
public class TestNonMeteredEntitlements_PathConditionType_CreateAndCheck
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
			robotSettings.setLogName("TestNonMeteredEntitlements_PathConditionType_CreateAndCheck-log");
			robotSettings.setOutputName("TestNonMeteredEntitlements_PathConditionType_CreateAndCheck-output");
			robotSettings.setReportName("TestNonMeteredEntitlements_PathConditionType_CreateAndCheck-report");

			final RobotTestSuiteFactory robotTestSuiteFactory = new PythonRobotTestSuiteFactory(pythonAware);

			robotTestSuite = robotTestSuiteFactory.parseTestSuite(robotSettings, new File(
			"/opt/bamboo-agent/xml-data/build-dir/HYM-EMS633-CJ/src/entitlements/entitlements-facade/src/test/resources/atdd/suites/NonMeteredEntitlements_PathConditionType_CreateAndCheck.txt"));
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
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No1_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No1_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No2_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No2_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No3_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No3_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No4_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No4_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No5_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No5_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No6_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No6_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No7_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No7_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No8_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No8_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No9_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No9_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No10_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No10_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No11_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No11_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No12_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No12_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No13_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No13_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No14_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No14_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No15_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No15_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No16_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No16_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No17_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No17_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No18_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_No18_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SpecialChars_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SpecialChars_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseDefault()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseDefault";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseFalse()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseFalse";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseTrue()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseTrue";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SeparatorDefault()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SeparatorDefault";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SeparatorSet_No1()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SeparatorSet_No1";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SeparatorSet_No2()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_SeparatorSet_No2";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseTrue_SeparatorSet_True()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseTrue_SeparatorSet_True";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseTrue_SeparatorSet_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithPathCondition_NocaseTrue_SeparatorSet_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithoutCondition_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithoutCondition_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithStringCondition_False()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithStringCondition_False";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithTimeframeCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithTimeframeCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithGeoCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithGeoCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithMeteredCondition()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_CheckWithMeteredCondition";

		final RobotTest robotTest = robotTestSuite.getRobotTest(robotTestName);

		currentRobotTest = robotTestSuite.getRobotTest(robotTestName);
		assertNotNull("Robot test: '" + robotTestName + "' not found", currentRobotTest);

		final RobotTestResult robotTestResult = robotTest.run();
		assertTrue(robotTestResult.getMessage(), robotTestResult.isSuccess());
	}

	@Test
	public void Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_2Users_2Entitlements()
	{
		final String robotTestName = "Test_NonMeteredEntitlements_PathConditionType_CreateAndCheck_2Users_2Entitlements";

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
