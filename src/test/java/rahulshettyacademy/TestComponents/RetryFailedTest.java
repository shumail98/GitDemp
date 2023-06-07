package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {

	
	int count = 0;
	int maxTry = 1;
	@Override
	public boolean retry(ITestResult result)
	{
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}
	/*
	 * basically by default this block is set to false that means if the test fail it will not rerun 
	 * we want rerun we need to make that as true 
	 * first time 0<1 true second time count incremented 1<1 false the it stops retrying 
	 * i cannot declare IRetryAnalyzer in testng xml file so which test case is failing and u want to try go to that test case
	 * and give the tag 
	 * 
	 */

}
