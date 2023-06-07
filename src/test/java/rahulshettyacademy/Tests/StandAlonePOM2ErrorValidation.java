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
import rahulshettyacademy.TestComponents.RetryFailedTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAlonePOM2ErrorValidation extends BaseTest {

	//public static void main(String[] args) throws InterruptedException {
		
		
		@Test(groups = {"ErrorHandling"},retryAnalyzer=RetryFailedTest.class)
		public void loginErrorValidations() throws IOException, InterruptedException 
		{
			String productName = "IPHONE 13 PRO";
			landingPage.loginApplication("shumailpasha@gmail.com", "Shumail*786");
			//verifying the error message 
			//div[@class='ng-tns-c4-3 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
			Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		}
		
		@Test
		public void productErrorValidations() throws InterruptedException
		{
			String productName = "ZARA COAT 3";
			ProductCatalogue ProductCatalogue = landingPage.loginApplication("danish@gmail.com", "Shumail*786");
			List<WebElement> products = ProductCatalogue.getproductList();
			ProductCatalogue.addProductToCart(productName);//product name will come from ur test case 
			CartPage cartPage = ProductCatalogue.goToCartPage();// we are adding cartpage instead of creating objects for it
			
			//reating error below
			boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
		}

}
