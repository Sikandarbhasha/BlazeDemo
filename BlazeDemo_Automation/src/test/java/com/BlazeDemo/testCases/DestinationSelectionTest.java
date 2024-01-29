package com.BlazeDemo.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.BlazeDemo.genericLibrary.Baseclass;
import com.aventstack.extentreports.Status;

public class DestinationSelectionTest extends Baseclass {
	
	@Test
	public void destionationSelectionTest() throws InterruptedException {
		test =extent.createTest("Verification of flight search");
		Assert.assertTrue(true, "Welcome to the Simple Travel Agency!");
		
		dsp.clickOnDestinationLink();
		
		test.log(Status.INFO, "Clicked on : Destination Link");
		logger.info("Clicked on : Destination Link");
		
		dsp.selectDeparturCity("Mexico City");
		Thread.sleep(2000);
		dsp.selectDestinationCity("London");
		dsp.clickOnFindFlight();
	}
	
}
