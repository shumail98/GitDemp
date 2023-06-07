package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstratComponents.AbstratComponenets;

public class ConfirmationPage extends AbstratComponenets{

	WebDriver driver;  
	public ConfirmationPage(WebDriver driver)
	{
		//initialize
		//every child need to give driver 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// String text = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
	// Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
	
	@FindBy(css="h1[class='hero-primary']")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
	 
}
