package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dp.Data;

public class Utility {

	private static WebDriver driver;

	public static boolean isWebElementExist(By by, WebDriver driver) {

		List<WebElement> weList;
		weList = (List<WebElement>) driver.findElements(by);

		// WebElement wE = driver.findElement(by);

		if (weList.equals(null))
			return false;
		return true;
	}

	public static String getProperty(String url) throws IOException {

		// create file input stream object for the properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\Israel Brener\\eclipse-workspace\\Selenium_AutomationProjectDemo\\datafile.test.properties");
				
		// create object for Properties class
		Properties prop = new Properties();

		// Load properties file
		prop.load(fis);

		return prop.getProperty("url");
	}

	public static WebDriver getWebDriver(String browserName, String baseUrl) {

		System.setProperty("webdriver.chrome.driver", "C:/Program Files/Selenium/chromedriver_win32/chromedriver.exe");
		System.setProperty("webdriver.ie.driver",
				"C:/Program Files/Selenium/IEDriverServer_x64_3.5.1/IEDriverServer.exe");

		if (browserName.equals("chrome"))
			driver = new ChromeDriver();
		else if (browserName.equals("firefox"))
			driver = new InternetExplorerDriver();

		return driver;
	}

	public static void addSnapShotToReport(File file) {
		Reporter.log("<br/><p><img width='612' src='" + file.getAbsolutePath() + "'></P>");
	}

	// method which take print screens
	public static File printScreen(WebDriver driver) throws IOException {

		// Take the image of WebDriver
		File snap = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// method which bring timestamp string
		DateFormat timestamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String strTimeStamp = timestamp.format(new Date());

		// Set string as path of the file
		String imgPath = "C:\\Users\\Israel Brener\\Pictures\\reportNG_SnapShotS\\" + strTimeStamp + "_image.png";

		// Create empty file
		File fImage = new File(imgPath);

		// copy image into our empty file
		FileUtils.copyFile(snap, fImage);

		// returm image
		return fImage;
	}

	@Test(enabled = false, dataProvider = "Details_int", dataProviderClass = Data.class)
	public void printDP(int number1, int number2, int number3) {
		System.out.println("number from dp: " + number1 + ", " + number2 + ", " + number3);
	}

}
