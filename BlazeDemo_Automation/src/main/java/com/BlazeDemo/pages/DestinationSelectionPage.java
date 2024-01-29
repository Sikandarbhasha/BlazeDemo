package com.BlazeDemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.BlazeDemo.genericLibrary.CommonLibrary;
import com.aventstack.extentreports.Status;

public class DestinationSelectionPage extends CommonLibrary {
	WebDriver driver;
	
	public DestinationSelectionPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Welcome to the Simple Travel Agency!']")
	private WebElement pageHeader;
	
	
	@FindBy(how = How.XPATH, using = "//a[text()='destination of the week! The Beach!']")
	private WebElement destinationLink;
	
	@FindBy(how = How.XPATH, using = "//select[@name='fromPort']")
	private WebElement departureCity;
	
	@FindBy(how = How.XPATH, using = "//select[@name='toPort']")
	private WebElement destinationCity;
	
	@FindBy(how = How.XPATH, using = "//input[@type='submit']")
	private WebElement findFlightsButton;
	
	
	public void getPageHeader() {
		getText(pageHeader);
	}
	
	public void clickOnDestinationLink() throws InterruptedException {
		
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).moveToElement(destinationLink).click().perform();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		test.log(Status.INFO, "Navigated to vacation tab");
		logger.info("Navigated to vacation tab");
		
		Assert.assertTrue(driver.getCurrentUrl().contains("vacation"), "URL is not matching");
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(tabs.get(0));
		test.log(Status.INFO, "Navigated back to main tab");
		logger.info("Navigated back to main tab");
	}
	
	public void selectDeparturCity(String text) {
		try {
			Select select =new Select(driver.findElement(By.name("fromPort")));
			select.selectByVisibleText(text);
			test.log(Status.INFO, "Selected the Dropdown by text : " + text);
			logger.info("Selected Dropdown by Text " + text);
		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to Select Dropdown element by text" + e.getMessage());
			logger.error("Unable to Select Dropdown element by text" + e.getMessage());
			test.log(Status.FAIL, "Unable to Select Dropdown element by text" + e.getMessage());
		}
	}
	
	public void selectDestinationCity(String text) {
		try {
			Select select =new Select(driver.findElement(By.name("toPort")));
			select.selectByVisibleText(text);
			test.log(Status.INFO, "Selected the Dropdown by text : " + text);
			logger.info("Selected Dropdown by Text " + text);
		} catch (Exception e) {
			Assert.assertTrue(false, "Unable to Select Dropdown element by text" + e.getMessage());
			logger.error("Unable to Select Dropdown element by text" + e.getMessage());
			test.log(Status.FAIL, "Unable to Select Dropdown element by text" + e.getMessage());
		}
	}
	
	public void clickOnFindFlight() {
		buttonClick(findFlightsButton);
	}
}
