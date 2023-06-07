package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.Resources.ExtentReportedNG;

public class Listeners extends BaseTest implements ITestListener {

	//here ITestListener this a interface provided by testng 
	//extents.flush();// this mandatory to write if we don't write this the code will not generate the report 
	ExtentTest test;
	ExtentReports extents = ExtentReportedNG.getReportObject();
	//when we run testng file in parallel it is getting failed because at a time onTestStart is getting executed multiple times so 
	ThreadLocal<ExtentTest> extentsTest = new ThreadLocal<ExtentTest>();//for every entry the thread local will be giving seperate execeptions or unique thread id 
	//unique thread id(errorValidationTest)-->test
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test= extents.createTest(result.getMethod().getMethodName());
		//in below test will store the test and give that unique thread id where as get will extract that thread if that test gets failed 
		extentsTest.set(test);//here extentsTest is varaible of thread local now the set will set for each one seperate 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		//test.log(Status.FAIL, "Test Failed");
		extentsTest.get().fail(result.getThrowable()); //this will give the error message of that perticular test fail message
		
		//step1 is to take screenshot and step2 is attach the screenshot 
		//we aleady declared the method in base class we will be directly calling now 
		
		//now the agenda is to get the driver 
		try {
			driver =  (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);//becuase here driver info in not there so we need to send it from the base class 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentsTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());//here first it is asking file path and next it is asking how to show the message 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extents.flush();// this mandatory to write if we don't write this the code will not generate the report 
	}
}
