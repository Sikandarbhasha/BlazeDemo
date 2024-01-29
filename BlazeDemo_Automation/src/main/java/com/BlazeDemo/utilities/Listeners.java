package com.BlazeDemo.utilities;

import java.io.IOException;
import java.nio.file.Paths;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.BlazeDemo.genericLibrary.BrowserConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Listeners extends BrowserConfig implements ITestListener {
	public static ExtentHtmlReporter htmlExtentreport;
	public static ExtentReports extent;
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
		}
	}

	public void onTestFailure(ITestResult result) {
		com.BlazeDemo.genericLibrary.CommonLibrary.Capture(result, "ScreenshotsOfFailedTestCases");
		logger.info("Test Case Failed " + result.getInstanceName());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
		try {
			test.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(Paths.get("").toAbsolutePath().toString()
							+ "/ScreenshotsOfFailedTestCases/" + result.getName() + ".png").build());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.ORANGE));
		}
	}

}
