package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public  WebDriver driver ;
	public LandingPage landingPage;//by this u can access this in ur child class StandAlone so that u can remove object creation 
	
	public WebDriver initializeDriver() throws IOException
	{
		//there is class in java which properties class it decides which browser we need to run the test during runtime
		//we have created the property file in "main" with extension.property to read the data from property file
		Properties prop = new Properties();
		//here we will load file which is property file after loading it will extract all key value pairs from the property file 
		//prop.load(null);//the argument here it is accepting is inputstream not the file path 
		//in java there is class which convert A file into input stream objects so that we can send using above 
		//here we have method called system property which dynamically the path of project in any system ur running 
		//--> C:\\Users\\M AS\\eclipse-workspace\\SeleniumFrameworkDesign <--till this it dynamically generate the path 
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
		 +"//src//main//java//rahulshettyacademy//Resources//GlobalData.properties");//it is expecting to send the path of the file
		prop.load(fis);//now we want to extract any value we can extract using attribute value 
		//now if i want to extract browser 
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			//ChromeDriver 
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			//FireFox
		}
		else if(browserName.equals("edge"))
		{
			//Edge
			System.setProperty("webdriver.edge.driver", "edge.exe");
			 driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;	
	}
		@BeforeMethod(alwaysRun=true) 
		//it will run before any method in child class runs here child is standAlone class where as base test in parent 
		public LandingPage launchApplication() throws IOException
		{
			 driver = initializeDriver();
			 landingPage = new LandingPage(driver);
			 landingPage.goTo();
			 return landingPage;
		}
		
		@AfterMethod(alwaysRun=true)  
		public void tearDwon()
		{
			driver.close();
		}
		
		//utils for getting the data from the dataReader class we have added in base class to call directly 
		public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
		{
			//to read the file we have some thing in java called file utils 
			
			//here in below step we are reading json to string 
			String jsonContent = FileUtils.readFileToString(new File(filepath),
					StandardCharsets.UTF_8);
			
			//STEP2 : Some External utilities are there which can convert from jsonContent to hashmap 
			//we are now converting string to hasmap we have Jackson Datbind
			
			ObjectMapper mapper = new JsonMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});
			
			return data;
			//here we are trying to grab 
			//{map,map} from json 
			
		}
		
		//to get screenshot public String getScreenshot(String testCaseName) throws IOException
		public String getScreenshot(String testCaseName , WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
			FileUtils.copyFile(src, dest);
			return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
		}

			
}
