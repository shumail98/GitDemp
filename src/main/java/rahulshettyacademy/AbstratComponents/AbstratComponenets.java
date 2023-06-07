package rahulshettyacademy.AbstratComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstratComponenets {
	
	WebDriver driver;
	public AbstratComponenets(WebDriver driver) {
	this.driver = driver; //now it got connected 
	PageFactory.initElements(driver, this);
	}
	
	//now i am clicking on cart List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
	//@FindBy(css="div[class='cartSection'] h3")
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader ;
	
	//checking my cart 
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader ;
	
	//here in this class and package we will be using the most re-useable compenents in test 
	//here By is only returing the locator where as driver.findElement(by.id(""))will return webElement
	public void waitElementToAppear(By findBy) //By is method and findby is a variable
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	//the code here is for visibility 	
	}
	
	//i have created other method because i cannot give findBy because we have created with the help of pagefactory the whole webElement is there alread
	//there is method visibilty of webElement
	public void waitElementForWebElementToAppear(WebElement findBy) //By is method and findby is a variable
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy)); //method for webElement 

	//the code here is for visibility 	
	}
	
	
	
	
	
	//now the below code is for invisibilty 
	public void waitElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
		//below steps it is taking 4 second of wait as it is waiting for another element to be visible 
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//below we have driver.findElement which has a written type as WebElement 
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	//now we are clicking on cart button which is same for all carts 
	public CartPage goToCartPage()
	{ 
		CartPage cartPage = new CartPage(driver); //instead on writing frequently in standalone class we have written here and we will remove that 
		cartHeader.click();
		return cartPage;
	}
	
	public OrderPage goToOrdersPage()
	{ 
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver); //instead on writing frequently in standalone class we have written here and we will remove that 
		return orderPage;
	}
	
	
	
}
