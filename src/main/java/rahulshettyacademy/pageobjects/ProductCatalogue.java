package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import rahulshettyacademy.AbstratComponents.AbstratComponenets;

public class ProductCatalogue extends AbstratComponenets{
	
	
	WebDriver driver;  
	public ProductCatalogue(WebDriver driver)
	{
		//initialize
		//every child need to give driver 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	 //NOTE---->page factory can only be written to driver.findelements onlyyy
	
	@FindBy(css="div.mb-3")
	List<WebElement> products;
	
	//driver.findElement(By.cssSelector(".ng-animating")))
	@FindBy(css=".ng-animating")
	WebElement waitforDisappear;
	
	//here return type is By
	By productsBy = By.cssSelector("div.mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));here we have created abstract method
	//List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3")); and we have return list of product by declaring By productsBy = By.cssSelector("div.mb-3");
	
	public List<WebElement> getproductList()
	{
		waitElementToAppear(productsBy);
		return products;
	}
	
	//finding the product based on product name using streams and filter methods 
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getproductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		//we are getting product from above method
		WebElement prod = getProductByName(productName); //actual product will be here 
		prod.findElement(addToCart).click();
		//now we should wait for the element to get added and page get loaded we need to wait we have alread the code just call that
		waitElementToAppear(toastMessage);
		waitElementToDisappear(waitforDisappear);
	}
	
	
	
}









/*
@FindBy(id="userEmail")
WebElement userEmail;


@FindBy(id="userPassword")
WebElement passwordElement;

@FindBy(id="login")
WebElement submit;

public void loginApplication(String email,String password)
{
	userEmail.sendKeys(email);
	passwordElement.sendKeys(password);
	submit.click();
}

public void goTo()
{
	driver.get("https://rahulshettyacademy.com/client");
}
*/