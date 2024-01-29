package com.BlazeDemo.genericLibrary;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

import com.BlazeDemo.pages.DestinationSelectionPage;
import com.BlazeDemo.pages.FlightSelectionPage;


public class Baseclass extends BrowserConfig{

	public DestinationSelectionPage dsp;
	public FlightSelectionPage fsp;
	
	@BeforeClass
	public void setClass() {
		dsp = PageFactory.initElements(driver, DestinationSelectionPage.class);
		fsp = PageFactory.initElements(driver, FlightSelectionPage.class);
	}
}
