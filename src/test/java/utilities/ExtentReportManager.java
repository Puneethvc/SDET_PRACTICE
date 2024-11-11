 package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.baseClass;

public class ExtentReportManager implements ITestListener{
	

	public ExtentSparkReporter sparkReporter;  // ui of the reports
	public ExtentReports extent; // populate the common info of the reports
	public ExtentTest test;  // crating the cases entries in the reports and updates status of the methods
	String reportName;
	
	
	
	public void onTestStart(ITestContext testContext) {
		
	    System.out.println("Initializing ExtentReports in onTestStart()");
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    reportName = "Test-Report --" + timeStamp + ".html";
	    
	    // Ensure the Reports directory exists
	    File reportDir = new File(System.getProperty("user.dir") + "\\Reports\\");
	    if (!reportDir.exists()) {
	        reportDir.mkdirs();
	    }
	    sparkReporter = new ExtentSparkReporter(".\\Reports\\" + reportName);
	    
	    sparkReporter.config().setDocumentTitle("Automation Report");
	    sparkReporter.config().setReportName("Functional Testing");
	    sparkReporter.config().setTheme(Theme.DARK);

	    extent = new ExtentReports();
	    extent.attachReporter(sparkReporter);

	    extent.setSystemInfo("Computer Name", "localhost");
	    extent.setSystemInfo("Environment", "QA");
	    extent.setSystemInfo("user Name", System.getProperty("user.name"));

	    String browser = testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);

	    String os = testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating system", os);

	    List<String> includedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
	    if (!includedgroups.isEmpty()) {
	        extent.setSystemInfo("Groups", includedgroups.toString());
	    }
	}

	
	
	public void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());  // create a new entity in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "Test Case PASSED is: "+result.getName()); // update the status pass/fail/skip
	}
	
	
	public void onTestFail(ITestResult result) throws IOException {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, "Test case FAILED :" + result.getName());
		test.log(Status.INFO, "Test case FAILED Cause is :"+ result.getThrowable().getMessage());
		
		try {
		String imagepath = new baseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imagepath);
		}
		catch(Exception e) {
			
		}
	}
	
	public void onTestSkip(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case SKIPPED :" + result.getName());
		test.log(Status.INFO, "Test case FAILED Cause is :"+ result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		
	
//		extent.flush();
	
//		String pathOfExtentReport = System.getProperty("user.dir")+"\\Reports\\"+reportName;
//		File extentReport =new File(pathOfExtentReport);
		
//		try {
//			Desktop.getDesktop().browse(extentReport.toURI());
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
		
		if (extent != null) {
	        extent.flush(); // Ensure the report is written to the file

	        String reportPath = System.getProperty("user.dir") + "\\Reports\\" + reportName;
	        File extentReport = new File(reportPath);

	        if (extentReport.exists()) {
	            try {
	                Desktop.getDesktop().browse(extentReport.toURI()); // Opens the report after the test finishes
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Report file not found: " + reportPath);
	        }
	    } else {
	        System.out.println("ExtentReports object is null. Make sure it's initialized properly.");
	    }
	
	}
}
	
	
