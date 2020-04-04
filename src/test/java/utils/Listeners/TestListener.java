package utils.Listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.testCases.BaseClass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager;


public class TestListener extends BaseClass implements ITestListener {
//	public WebDriver listnerDriver=driver;
	public static ExtentTestManager extentMgr;
	
	public static void logExtentLog4J(LogStatus status,String Description) {
		logger.info(Description);
//		ExtentTestManager.getTest().log(status, Description);
		ExtentTestManager.getTest().log(status, Description);
	}
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Inside onStart method " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Inside onFinish method " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
//        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
//        ExtentTestManager.endTest();
//        extentMgr.getReporter().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("In onTestStart method " +  getTestMethodName(iTestResult) + " start");
        //Start operation for extentreports.
//        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("In onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
        //Extentreports log operation for passed tests.
//        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("In onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
        //Get driver from BaseTest and assign to local webdriver variable.
//        WebDriver webDriver = getDriverInstance();
        ITestContext context = iTestResult.getTestContext();
		WebDriver webDriver = (WebDriver) context.getAttribute("driver");
        if(webDriver!=null) {
	        //Take base64Screenshot screenshot.
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	                getScreenshotAs(OutputType.BASE64);
	        //Extentreports log and screenshot operations for failed tests.
	        ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
        		ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
        }else
            ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",iTestResult.getName());
        
        

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("In onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
        //Extentreports log operation for skipped tests.
//        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
    
}
