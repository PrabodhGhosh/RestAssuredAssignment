package com.assignment.rest.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	ExtentReports extent;
	
	/* Generate Extent Report
	 * @author: Prabodh G
	 * @return: Extent report
	 */
	public ExtentReports getReportObject()
	{
		String path=null;
		String OS = Utils.getOS();
		if (OS.toLowerCase().contains("windows")) {
		path = System.getProperty("user.dir")+"\\index.html";
		} else
		if (OS.toLowerCase().contains("mac")) {
			path = System.getProperty("user.dir")+"/index.html";
		}
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("API Automation Report");
		reporter.config().setDocumentTitle("Test Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test", "test");
		return extent;
	}

}
