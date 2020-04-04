package com.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.utilities.ReadConfig;

import utils.ExtentReports.ExtentTestManager;

public class BaseClass   {
	ExtentTestManager extentTestManager;
	
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public WebDriver Basedriver;
	public ExtentTestManager extentMgr;
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
//	@BeforeTest
	public void setup(String br,ITestContext context)
	{			
		Basedriver=launchBrowser(br);
		Basedriver.get(baseURL);
		Basedriver.manage().window().maximize();
		context.setAttribute("driver", Basedriver);
	}
	@Parameters("browser")
	protected WebDriver launchBrowser(String br) {
		System.out.println("==============="+br+"===================");
		WebDriver localdriver=null;
		logger = Logger.getLogger("bot");
		PropertyConfigurator.configure("Log4j.properties");		
		localdriver = getBrowser(br);		
		localdriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return localdriver;
	}
	
	
	private WebDriver getBrowser(String br) {
		WebDriver localdriver;
		if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			localdriver = new FirefoxDriver();
			localdriver.manage().window().maximize();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			localdriver = new InternetExplorerDriver();
			localdriver.manage().window().maximize();
		}else if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			localdriver=new ChromeDriver();
			localdriver.manage().window().maximize();
		}else {
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			localdriver = new FirefoxDriver();
			localdriver.manage().window().maximize();
		}
		return localdriver;
	}
	
	@AfterClass
//	@AfterTest
	public void tearDown()
	{
		System.out.println("in the tear down");
		Basedriver.quit();
//		extentMgr.endTest();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	public WebDriver getDriverInstance() {
		return this.Basedriver;
	}
	
	public void setDriverInstance(WebDriver wd) {
		this.Basedriver=wd;
	}

}
