package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.jcajce.provider.digest.GOST3411.HashMac;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	//public static void main(String[] args) throws InterruptedException {
		
		public String productName = "IPHONE 13 PRO";	
		@Test (dataProvider="getData",groups= {"Purchase"})
		//now we can send the data using hashmap variable map 
		//public void submitOrder(String email , String password,String productName) throws IOException, InterruptedException 
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException 
		//what every u send map will fall in that input basically act like variable 
		{
			ProductCatalogue ProductCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		    ProductCatalogue.getproductList();
			ProductCatalogue.addProductToCart(input.get("productName")); //instead of using product name  we use input.get(product) ProductCatalogue.addProductToCart(productName); 
			CartPage cartPage = ProductCatalogue.goToCartPage();
			boolean match = cartPage.verifyProductDisplay(input.get("productName"));
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = cartPage.clickOnCheckOut(); 
			checkoutPage.selectCountry("india");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder(); 
			String confirmationMessage = confirmationPage.getConfirmationMessage();
			Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
			System.out.println("This is Github change made in github demo project ");
			System.out.println("This is Github change made in github demo project ");
			//this is by me who owns the project 
			System.out.println("This is Github change made in github Gitstuff project ");
			System.out.println("This is Github change made in github Gitstuff project ");
			System.out.println("This is Github change made in github Gitstuff project ");
			System.out.println("This is Github change made in github Gitstuff project ");


		
		}
		
		@Test(dependsOnMethods = {"submitOrder"})
		public void OrderHistory()
		{
			//to verify "IPHONE 13 PRO" is displaying in order page 
			ProductCatalogue ProductCatalogue = landingPage.loginApplication("shumailpasha@gmail.com", "Shumail*786");
			OrderPage orderPage = ProductCatalogue.goToOrdersPage();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

		}
		
		public String getScreenshot(String testCaseName) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
			FileUtils.copyFile(src, dest);
			return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
		}
		
		
		
		//our aim is create a json file and fetch the data which we will be understanding step by step 
		//we have created a json file in rahulshettyacademy.data we willpass this hash map values by creating one more file called utility 
		
		@DataProvider //it is used drive the data and pass the multiple sets of data 
		public Object[][] getData() throws IOException
		{
			
			 
			/*
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("email","shumailpasha@gmail.com");
			map.put("password", "Shumail*786");
			map.put("productName", "IPHONE 13 PRO");
			
			HashMap<String, String> map1 = new HashMap<String,String>();
			map1.put("email","danish@gmail.com");
			map1.put("password", "Shumail*786");
			map1.put("productName", "ZARA COAT 3");
			*/
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)} ,{data.get(1)}}; //it is a two dimentional data array
		}
		
			
			/*
			//instead of that we can directly send hashMap variable map inside that 
			//return new Object[][] {{map} ,{"danish@gmail.com","Shumail*786","ZARA COAT 3"}}; //it is a two dimentional data array 

			/*this is 1st was if u want to send multiple data u can use hash map key value pair 
			//if there are 15 data sets it is very difficult to sent so we use hash map 
			return new Object[][] {{"shumailpasha@gmail.com","Shumail*786","IPHONE 13 PRO"} ,{"danish@gmail.com","Shumail*786","ZARA COAT 3"}}; //it is a two dimentional data array 
			//here first curly braces represent one data set 
			//objeect can accept anything int,string and thing 
			 */
			
			
			/* 1st type of doing 
				@DataProvider	
				public Object[][] getData()
				{
				return new Object[][] {{"shumailpasha@gmail.com","Shumail*786","IPHONE 13 PRO"} ,{"danish@gmail.com","Shumail*786","ZARA COAT 3"}}; //it is a two dimentional data array 
				}
				*/
		
}
