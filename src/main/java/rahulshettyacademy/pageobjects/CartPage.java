package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstratComponents.AbstratComponenets;

public class CartPage extends AbstratComponenets {

	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	@FindBy(css="div[class='cartSection'] h3")
	private List<WebElement> cartProducts;
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	
	
	public boolean verifyProductDisplay(String productName)
	{
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage clickOnCheckOut()
	{
		checkOutEle.click();
		return new CheckoutPage(driver);
		
	}
	
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	//anymatch will check any matching element present in the cart it will return boolean value here which is true
	//in filter it will take the list of web elements or total webElement and give the web element we can do anything with that 
	//boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	//Assert.assertTrue(match);
}
