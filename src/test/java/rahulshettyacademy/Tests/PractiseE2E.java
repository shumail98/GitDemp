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

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PractiseE2E {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		
		String myPhone = "IPHONE 13 PRO";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("shumailpasha@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shumail*786");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".offset-md-0"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-md-0")));
		
		WebElement addingcart = products.stream().filter(oneprod->
		oneprod.findElement(By.cssSelector("b")).getText().equals(myPhone)).findFirst().orElse(null);
		
		addingcart.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait till page gets loaded in footer message 
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean verifyCart = cartProduct.stream().anyMatch(s->s.getText().equalsIgnoreCase(myPhone));
		Assert.assertEquals(myPhone,"IPHONE 13 PRO");
		
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		driver.findElement(By.cssSelector(".fa-sign-out")).click();
		
		
		

	}

}
