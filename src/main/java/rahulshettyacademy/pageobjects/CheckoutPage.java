package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstratComponents.AbstratComponenets;

public class CheckoutPage extends AbstratComponenets {

	WebDriver driver;  
	public CheckoutPage(WebDriver driver)
	{
		//initialize
		//every child need to give driver 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	//clicking on county 
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();	
	//clicking selectIndia //-->//button[contains(@class,'ta-item')][2]
	@FindBy(xpath="(//button[contains(@class,'ta-item')]) [2]")
	WebElement selectCountry;
	
	//click on placeorder
	//driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) //chose the drop down option
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		waitElementToAppear((By.cssSelector(".ta-results")));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		placeOrder.click();
		return new ConfirmationPage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	
}
