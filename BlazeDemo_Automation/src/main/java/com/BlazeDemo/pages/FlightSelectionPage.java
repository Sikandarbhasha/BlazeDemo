package com.BlazeDemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.BlazeDemo.genericLibrary.CommonLibrary;

public class FlightSelectionPage extends CommonLibrary {
	WebDriver driver;

	public FlightSelectionPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "")
	private WebElement chooseFight;

	public int getAllRowsSize() {
		List<WebElement> flightTableRows = driver.findElements(By.xpath("//table/tbody/tr/td"));
		return flightTableRows.size();
	}

	public String getFlightPrice(int rowNum) {
		WebElement flightPrice = driver.findElement(By.xpath("//table/tbody/tr[" + rowNum + "]/td[6]"));
		return getText(flightPrice);
	}
	
	

}
