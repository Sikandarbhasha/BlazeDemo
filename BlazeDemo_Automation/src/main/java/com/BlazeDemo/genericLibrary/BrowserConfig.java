package com.BlazeDemo.genericLibrary;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BrowserConfig {
	
	public static WebDriver driver;
	public String url = "https://blazedemo.com/index.php";
	public static Logger logger  = Logger.getLogger("Log creation");
	
	public static ExtentHtmlReporter htmlExtentreport;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	
	
	@BeforeSuite
	public void setReport() {
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/Properties/log4j.properties");
		
		Date date = new Date();
		String d = date.toString().replace(" ", "_").replace(":", "_");
		htmlExtentreport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReports/extent_"+d+".html");
		htmlExtentreport.config().setDocumentTitle("Blaze Demo project automation report");
		htmlExtentreport.config().setReportName("Blaze Demo project automation report");
		htmlExtentreport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlExtentreport);
		extent.setSystemInfo("Reporter name", "Sikandarbhasha");
		extent.setSystemInfo("OS", "Windows 10");
	}
	
	@BeforeTest
	@Parameters("browser")
	public void setBrowser(String b){
		if(b.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			logger.info("Chrome browser opened");
		}else if(b.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			logger.info("Firefox browser opened");
		}else {
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			logger.info("Edge browser opened");
		}
		driver.navigate().to(url);
		logger.info("Navigated to URL");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		logger.info("Browser closed");
	}
	
	@AfterSuite
	public void flushReport() {
		extent.flush();
	}

}
