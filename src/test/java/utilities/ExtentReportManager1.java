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

public class ExtentReportManager1 implements ITestListener{
	

	public ExtentSparkReporter sparkReporter;  // ui of the reports
	public ExtentReports extent; // populate the common info of the reports
	public ExtentTest test;  // crating the cases entries in the reports and updates status of the methods
	String reportName;
	
	
@Override
public void onStart(ITestContext testContext) {

    System.out.println("Initializing ExtentReports in onStart()");

    // Initialize the ExtentSparkReporter
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    reportName = "Test-Report-" + timeStamp + ".html";

    // Ensure the Reports directory exists
    File reportDir = new File(System.getProperty("user.dir") + "\\Reports\\");
    if (!reportDir.exists()) {
        reportDir.mkdirs();
    }

    sparkReporter = new ExtentSparkReporter(reportDir + "\\" + reportName);
    sparkReporter.config().setDocumentTitle("Automation Report");
    sparkReporter.config().setReportName("Functional Testing");
    sparkReporter.config().setTheme(Theme.DARK);

    // Initialize the ExtentReports object
    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);

    // Adding system information
    extent.setSystemInfo("Computer Name", "localhost");
    extent.setSystemInfo("Environment", "QA");
    extent.setSystemInfo("User Name", System.getProperty("user.name"));

    String browser = testContext.getCurrentXmlTest().getParameter("browser");
    extent.setSystemInfo("Browser", browser);

    String os = testContext.getCurrentXmlTest().getParameter("os");
    extent.setSystemInfo("Operating System", os);

    List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
    if (!includedGroups.isEmpty()) {
        extent.setSystemInfo("Groups", includedGroups.toString());
    }
}


@Override
public void onTestSuccess(ITestResult result) {
    test = extent.createTest(result.getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.PASS, "Test Case PASSED is: " + result.getName());
}

@Override
public void onTestFailure(ITestResult result) {
    test = extent.createTest(result.getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.FAIL, "Test Case FAILED is: " + result.getName());
    test.log(Status.FAIL, "Test Case FAILED due to: " + result.getThrowable().getMessage());

    try {
        String imagePath = new baseClass().captureScreen(result.getName());
        test.addScreenCaptureFromPath(imagePath);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Override
public void onTestSkipped(ITestResult result) {
    test = extent.createTest(result.getName());
    test.assignCategory(result.getMethod().getGroups());
    test.log(Status.SKIP, "Test Case SKIPPED is: " + result.getName());
    test.log(Status.SKIP, "Test Case SKIPPED due to: " + result.getThrowable().getMessage());
}

@Override
public void onFinish(ITestContext testContext) {
    if (extent != null) {
        extent.flush();
        String reportPath = System.getProperty("user.dir") + "\\Reports\\" + reportName;
        File extentReport = new File(reportPath);

        if (extentReport.exists()) {
            try {
                Desktop.getDesktop().browse(extentReport.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Report file not found: " + reportPath);
        }
    } else {
        System.out.println("ExtentReports object is null. Ensure it's initialized properly.");
    }
}

}
	