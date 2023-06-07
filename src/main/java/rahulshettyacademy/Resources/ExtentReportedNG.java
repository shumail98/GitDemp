package rahulshettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportedNG {
	
	
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation result");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extents = new ExtentReports();
		extents.attachReporter(reporter);
		extents.setSystemInfo("Tester", "Shumail Pasha");
		extents.createTest(path);
		return extents;
	}

}
