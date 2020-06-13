package tests;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dp.Data;
import pages.HomePage;
import pages.RegistrationPage;
import utility.Utility;

public class TestPageObject {

	// Declaration of class elements
	private WebDriver driver;
	private HomePage hp;
	private RegistrationPage rp;
	
	// Declaration of String for URL of site which going to be tested
	private String baseUrl;

	// Declaration of log object
	private static final Logger log = LogManager.getLogger(TestPageObject.class.getName());

	@Parameters({ "browserName" })
	@BeforeClass
	// optional = default browser
	public void beforeClass(@Optional("chrome") String browserName) throws IOException {

		log.info("before class...");

		// System property report ng for allow add HTML tags in HTML report
		System.setProperty("org.uncommons.reportng.escape-output", "false");

		// System property for set encoding properties
		System.setProperty("file.encoding", "UTF-8");	

		// Get the site URL from property file
		baseUrl = Utility.getProperty("url");

		// Get and load the WebDriver
		driver = Utility.getWebDriver(browserName, baseUrl);
	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("before method...");
		driver.get(baseUrl);
		hp = new HomePage(driver);
		rp = new RegistrationPage(driver);
	}
	
	@Test(priority = 2, enabled = true, dataProvider = "dataFromExcel", dataProviderClass = Data.class)
	public void register(String name, String email, String password) throws InterruptedException, IOException {
		try {
			Assert.assertTrue(hp.navigate("register"), "Could get to register page");
			Assert.assertTrue(rp.fillRegistartionForm(name, email, password));
			Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
		}

		catch (AssertionError ar) {
			log.error("The result failed on assertion error: Please review log file");
			Reporter.log("Test result failed: For Assertion");
			Utility.addSnapShotToReport(Utility.printScreen(driver));
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
		} 
			catch (Exception ex) {
			log.error("Test result failed: For Exception");
			Utility.addSnapShotToReport(Utility.printScreen(driver));
			Reporter.log("Test result failed: For Exception");
			Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
			ex.printStackTrace();
		}
	}
	
	@Test(priority = 2, enabled = false)
	public void searchLocalTime() throws InterruptedException {
		Assert.assertTrue(hp.searchLocalTime("New York City"), "Search local time");
	}

	@Test(priority = 1, enabled = false)
	public void addImageToReportExample() throws InterruptedException, IOException {
		String msg = "This is a sample text to send to Report-ng";
		Reporter.log("<font size='3' color='red'>" + msg);

		log.trace("1 log trace");
		log.debug("2 log debug");
		log.info("3 log info");
		log.warn("4 log warn");
		log.error("5 log error");
		log.fatal("6 log fatal");

		// print screen method

		Utility.addSnapShotToReport(Utility.printScreen(driver));
		Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		log.info("after method...");
		Thread.sleep(2000);
	}

	@AfterClass
	public void afterClass() {
		log.info("after class...");
		driver.quit();
	}

}
