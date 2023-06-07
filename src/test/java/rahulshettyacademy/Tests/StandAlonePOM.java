package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAlonePOM extends BaseTest {

	//public static void main(String[] args) throws InterruptedException {
		
		
		@Test
		public void submitOrder() throws IOException, InterruptedException 
		{
			String productName = "IPHONE 13 PRO";
			//LandingPage landingPage = launchApplication(); i have made public varaible in parent class here it is base class 
			//STEP:1
			//Page object model 
			//LandingPage landingPage = new LandingPage(driver);
		
			//STEP:2
			//very easy to understand u can check LandingPage class
			//landingPage.goTo();
			ProductCatalogue ProductCatalogue = landingPage.loginApplication("shumailpasha@gmail.com", "Shumail*786");
		
			//STEP:3
			//we have used both ProductCatalogue and AbstratComponenets for waiting and storing webelements into list 
			//ProductCatalogue ProductCatalogue = new ProductCatalogue(driver); we are adding landing class only 
			List<WebElement> products = ProductCatalogue.getproductList();
		
		
			//STEP:4  addProductToCart
			//finding the product based on product name,here we have used actions class on product catalogue
			ProductCatalogue.addProductToCart(productName);//product name will come from ur test case 
			//interview question : can u apply page factory for weblement.findElement 
			//Answer --> NO its not possible because return type of weblement.findElement is By we need to store there and retrieve
			//we have used all those wait for visibility of element wait for invisibilty everything is added in .addProduct to cart methods
			
			//STEP:5 we are clicking cart button 
			CartPage cartPage = ProductCatalogue.goToCartPage();// we are adding cartpage instead of creating objects for it 
	
			//Step:6 to check elements are same in cart or not 
			//CartPage cartPage = new CartPage(driver);
			boolean match = cartPage.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			
			//STEP:7 go to checkout 
			CheckoutPage checkoutPage = cartPage.clickOnCheckOut();//it ihas returened from the cartpage 
		
			//STEP8:will be doing actions like selecting the country and placing the order
			checkoutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();//it has the object created there 
			
			//STEP9:String text = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
			String confirmationMessage = confirmationPage.getConfirmationMessage();
			Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
			//driver.close(); called in base class and annoted as @Aftermethod
		
	}

}
