package com.RA.testBase;

import java.io.IOException;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

import com.RA.httpMethods.Request;
import com.RA.util.ConfigProvider;
import com.RA.util.ExcelUtility;
import com.RA.util.Files;
import com.RA.util.TestUtility;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.typesafe.config.Config;

/**
 * supper class of all step def class
 * 
 * @author manish
 *
 */

public class BaseClass {

	public static TestBase testBase = new TestBase();
	public Files file = new Files();
	public Request requestPayload = new Request();
	public ExcelUtility excelUtility = new ExcelUtility();
	public static Properties property;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static Logger log;
	String query = "";

	@BeforeTest
	public void setLog4j() {
		TestUtility.setDateForLog4j();

		System.out.println(System.getProperty("user"));

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/AutomationReport/" + TestUtility.getDate() + ".html");
		extent.addSystemInfo("Host Name", "Windows System");
		extent.addSystemInfo("User Name", "user");
		extent.addSystemInfo("Environment", "Automation Test Report");
		makeQuery();

	}

	public void makeQuery() {
		Config queryConfig = ConfigProvider.getConfig().getConfig("conditions");

		JsonObject conditions = JsonParser.parseString(file.readJson("conditions.json")).getAsJsonObject();
		conditions.getAsJsonObject("eligRulesConfig").getAsJsonObject("conditionConfig").entrySet()
				.forEach(condition -> {
					
					String queryInConfig = queryConfig
							.getString(condition.getKey() + "-" + condition.getValue().toString());
					query = (condition.getValue().toString().isEmpty()) ? ""
							: query + " and " + String.format(queryInConfig, condition.getValue());
					System.out.println(condition.getKey());

				});
		System.out.println(query.substring(5));
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getName()); // To Add Name in Extent Report.
			extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable()); // To Add Errors and
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
		}
		extent.endTest(extentTest); // Ending Test and Ends the Current Test and Prepare to Create HTML Report.

	}

}
