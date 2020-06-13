package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends HomePage {

	//Class objects
	
	// Constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	public boolean fillRegistartionForm(String sName, String sEmail, String sPassword) {
		// fill fields:
		fillText(By.name("fullname"), sName);
		fillText(By.name("email"), sEmail);
		fillText(By.name("password"), sPassword);
		fillText(By.name("password1"), sPassword);
		
		click(By.xpath("//*[@id=\"create\"]"));
		
		return true;
	}

}
