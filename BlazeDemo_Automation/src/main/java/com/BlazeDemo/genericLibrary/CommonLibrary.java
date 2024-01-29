package com.BlazeDemo.genericLibrary;

import java.io.File;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;


public class CommonLibrary extends BrowserConfig {
	public static WebDriverWait wait;
	public static Alert alt;
	static Random random = new Random();
	public static String output;
	public static String value;

	// Wait Statement to wait still page to be loaded
	public void waitforpageload() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		} catch (Exception e) {
			logger.error("Unable to wait for page to load " + e.getMessage());
			Assert.assertTrue(false, "Unable to wait for page to load " + e.getMessage());
			test.log(Status.FAIL, "Unable to wait for page to load");
		}
	}

	// Wait Statement to wait still Element to be loaded
	public static void waitForElementToBePresent(WebElement element) {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(100));
			wait.until(ExpectedConditions.visibilityOf(element));

		} catch (Exception e) {
			logger.error("Unable to wait for Element To BePresent " + e.getMessage());
			Assert.assertTrue(false, "Unable to wait for Element To BePresent" + e.getMessage());
			test.log(Status.FAIL, "Unable to wait for Element To BePresent");
		}

	}

	// ---Accept alert-----//
	public void acceptAlert() {
		try {
			Thread.sleep(1000);
			alt = driver.switchTo().alert();
			alt.accept();
			logger.info("Alert Accepted");
			test.log(Status.PASS, "Alert Accepted");
		} catch (Exception e) {
			logger.error("Unable to accept Alert Message " + e.getMessage());
			Assert.assertTrue(false, "Unable to accept Alert Message" + e.getMessage());
			test.log(Status.FAIL, "Unable to accept Alert Message");
		}

	}

	// ---Alert Message-----//
	public String getAlertmsg() {
		try {
			Thread.sleep(1000);
			alt = driver.switchTo().alert();
			alt.getText();
			logger.info("Alert Message " + alt.getText());
		    test.log(Status.PASS, "Alert Message is " + alt.getText());
		} catch (Exception e) {
			logger.error("Unable to get Alert Message " + e.getMessage());
			Assert.assertTrue(false, "Unable to get Alert Message" + e.getMessage());
			test.log(Status.FAIL, "Unable to get Alert Message");
		}
		return alt.getText();
	}

	// ---Dismiss alert-----//
	public void dismissAlert() {
		try {
			Thread.sleep(1000);
			alt = driver.switchTo().alert();
			alt.dismiss();
			Thread.sleep(1000);
			logger.info("Alert Dismissed");
		    test.log(Status.PASS, "Alert Dismissed");
		} catch (Exception e) {
			logger.error("Unable to dismiss Alert Message " + e.getMessage());
			Assert.assertTrue(false, "Unable to dismiss Alert Message" + e.getMessage());
			test.log(Status.FAIL, "Unable to dismiss Alert Message");
		}
	}

	// ---Capture Full Screen image----//
	public static void Capture(ITestResult result, String folder) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			 test.log(Status.INFO, "Testcase failed and Waiting to capture the screenshot");
			FileHandler.copy(srcFile, new File("./" + folder + "/" + result.getName() + "failed.png"));
			logger.error("Testcase failed and screenshot taken");
			test.log(Status.INFO, "Testcase failed and screenshot taken");
		} catch (Exception e) {
			logger.error("Unable to Take ScreenShot " + e.getMessage());
			Assert.assertTrue(false, "Unable to Take Element ScreenShot" + e.getMessage());
			test.log(Status.FAIL, "Unable to Take Element ScreenShot");
		}

	}


	public static void moveToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to move to element" + element);
			e.printStackTrace();
		}
	}

	
	// For Button Click
	public static void buttonClick(WebElement element) {
		try {
			waitForElementToBePresent(element);
			String elval = element.getText();
			element.click();
			test.log(Status.PASS, "Clicked on : " + elval);
			logger.info("Clicked on : " + elval);
		} catch (Exception e) {
			Assert.assertTrue(false, "Unable Click on element " + e.getMessage());
			test.log(Status.FAIL, "Unable Click on element " + e.getMessage());
			logger.error("Unable to Click on element " + e.getMessage());
		}
	}

	// For Get Value
	public static String getvalue(WebElement element) {
		waitForElementToBePresent(element);
		try {
			logger.info("Value of element is " + element.getAttribute("value"));
			value = element.getAttribute("value").trim();
			test.log(Status.PASS, "Value of element is " + value);
			return value;
		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to get value of element");
			logger.error("Unable to get value of element");
			test.log(Status.FAIL, "Unable to get value of element");
			return "";
		}
	}

	// For Get Text
	public static String getText(WebElement element) {
		try {
			waitForElementToBePresent(element);
			value = element.getText().trim();
			logger.info("Value of element is " + value);
		} catch (Exception e) {
			Assert.assertTrue(false, "Element not found" + element);
			e.printStackTrace();
			test.log(Status.FAIL, "Element not found " + e.getMessage());
			logger.error("Element not found " + e.getMessage());
		}
		return value;
	}

	// For Get Text
	public static String getvisibleText(WebElement element) {
		try {
			waitForElementToBePresent(element);
			moveToElement(element);

			logger.info("Value of element is " + element.getText().trim());
			value = element.getText().trim();

		} catch (Exception e) {
			Assert.assertTrue(false, "Element not found" + element);
			e.printStackTrace();
		}
		return value;
	}

	public static boolean IsElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			Assert.assertTrue(false, "Element not found" + e.getMessage());
			return false;
		}

	}

	public static String AutoalphaNumericvalue(int range) {
		if (range > 1) {
			StringBuilder sb1 = new StringBuilder();
			char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
			for (int i = 0; i < range; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb1.append(c);
			}
			output = sb1.toString();

		} else {
			Assert.assertTrue(false, "Input Range should be >0");
		}
		return output;
	}

	public static String AutoNumericvalue(int range) {
		if (range > 0) {
			StringBuilder sb1 = new StringBuilder();
			char[] chars = "123456789".toCharArray();
			for (int i = 0; i < range; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb1.append(c);
			}
			output = sb1.toString();
		} else {
			Assert.assertTrue(false, "Input Range should be >0");
		}
		return output;
	}

	public static String Autoalphavalue(int range) {
		if (range > 0) {
			StringBuilder sb1 = new StringBuilder();
			char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			for (int i = 0; i < range; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb1.append(c);
			}
			output = sb1.toString();
		} else {
			Assert.assertTrue(false, "Input Range should be >0");
		}
		return output;
	}

}
