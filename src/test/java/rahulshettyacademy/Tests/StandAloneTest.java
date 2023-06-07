package rahulshettyacademy.Tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		String productName = "IPHONE 13 PRO";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.id("userEmail")).sendKeys("shumailpasha@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shumail*786");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
		
		//filter each product and if we take first product we will check in that scope if that elementis there 
		//here findfirst will find the first element or if doesn't find anything orelse block gets executed 
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		//here button:last-of-type means there was two buttons view and add to cart if want last button of that type we use this
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//we wait for that confirmation below which shows add cart was successfull
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//that loading process happens after clicking we need to ask developer which tag is used 
		//here he told us the tag --> //ng-animating
		//below is taking much time so will use different one
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//we are clicking cart button 
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//now i am checking the elements name present inside the cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		//anymatch will check any matching element present in the cart it will return boolean value here which is true
		//in filter it will take the list of web elements or total webElement and give the web element we can do anything with that 
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//clicking on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//click on idia drop down
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();	
		
		//place the order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		 String text = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		 Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
		
	}

}
