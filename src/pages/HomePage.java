package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

	public static final Logger log = LogManager.getLogger(BasePage.class.getName());

	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean searchLocalTime(String local) {
		log.info("loggin in home page");
		fillTextJS(By.id("sb_wc_q"), local);
		click(By.xpath("//form[@name='sb_wc']//button"));
		return true;
	}


	public boolean navigate(String location) throws InterruptedException {
		Actions actions = new Actions(driver);
		boolean beResult = false;

		switch (location) {

		case ("custom"): {
			driver.findElement(By.xpath("//*[@id=\"navMenu\"]/li[1]/a[2]")).click();
			break;
		}
		// Go to register form
		case ("register"): {
			WebElement we = driver.findElement(By.xpath("//*[@id=\"navMenu\"]/li[1]/a[2]"));
			actions.moveToElement(we).build().perform();
			actions.click(driver.findElement(By.linkText("Register"))).build().perform();
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//*[@id=\"mpo\"]/div/div/div/div/h2")).isDisplayed())
				beResult = true;
		}
		case ("calendar"): {
			// action for navigate to calendar
			break;
		}
		}
		return beResult;

	}

}
