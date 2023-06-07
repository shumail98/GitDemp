package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstratComponents.AbstratComponenets;

public class LandingPage extends AbstratComponenets{
	//here AbstratComponenets is parent class landingpage is child class 
	
	
	
	WebDriver driver;//it is local class  driver it is null it doesn't have any Chrome driver and all 
	//it is constructor which helps to initialize the chrome browser from another class 
	//by creating object and sending the parameters to it and meking constructor as parameterized one !
	//now we will create the object of this class in StandAlone class and pass driver there
	//---NOTE:before running anymethod in the class constructor is executed and constructor is have same name as class name 
	//any initialization code will be written here becuase it is a best place to run that 
	public LandingPage(WebDriver driver)//this driver has chromedriver object  
	{
		//u can send the driver from child class to parent class using Super constructor but the condition is u need to use constructor in parent class 
		super(driver);
		this.driver=driver;//it is now refereing the local class driver to actual chromedriver 
		//now if u come out of this method and use driver this will be still null only because chrome driver is inside this scope only 
		PageFactory.initElements(driver, this);//it is pageFactory method which takes argument as driver and other is this which refers to current class driver or chrome driver 
	}
	

	//WebElement userEmail = driver.findElement(By.id("userEmail"));//driver here is null value 
	
	//PageFactory we can reduce the syntax of creating web element using @FindBy()
	//Note-->
	//how @findby() know about driver ? we need to initialize using init() method
	@FindBy(id="userEmail")// At run time this will be created by @findBy()
	WebElement userEmail;
	//Interview Question how @findby know about driver ? using "initElements" methd provided by pagefactory which will take of creating driver 
	//NOTE-->Page Obejct should not hold any data it should have only elements and actions 
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	////div[@class='ng-tns-c4-3 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	//incoreect username or password 
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//it is over hectic creating objects in standalone class so we can create in landing class and use directly 
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue ProductCatalogue = new ProductCatalogue(driver);
		return ProductCatalogue;
	}
	//now u can run stand Alone by calling methods 
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitElementForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
}
