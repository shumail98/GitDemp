package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstratComponents.AbstratComponenets;

public class OrderPage extends AbstratComponenets {

	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	
	
	public boolean verifyOrderDisplay(String productName)
	{
		boolean match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	//anymatch will check any matching element present in the cart it will return boolean value here which is true
	//in filter it will take the list of web elements or total webElement and give the web element we can do anything with that 
	//boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	//Assert.assertTrue(match);
}
