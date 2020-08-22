package com.assignment.rest.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {

	ExtentReporterNG ext = new ExtentReporterNG();
	ExtentReports extent = ext.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	/*Set test name on start of the test in Extent Report
	 * @author: Prabodh G
	 *  
	 */

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}
	
	/* Set test case as PASS on success in Extent Report
	 * @author: Prabodh G
	 *  
	 */

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Case Passed");

	}
	
	/* Set test case as FAIL along with stack trace on failure in Extent Report
	 * @author: Prabodh G
	 *  
	 */

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());

	}
	
	/* Set test case as SKIP on skip in Extent Report
	 * @author: Prabodh G
	 *  
	 */

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip(result.getThrowable());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
	
	/* Flush the Extent Report on Finish of the suite
	 * @author: Prabodh G
	 *  
	 */

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
