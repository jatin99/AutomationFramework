package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.utilities.ReadConfig;

import utils.ExtentReports.ExtentTestManager;

public class BaseStepDefinitionClass {
	ReadConfig readconfig=new ReadConfig();	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public WebDriver Basedriver;
	public ExtentTestManager extentMgr;
	
	protected WebDriver launchBrowser(String br) {
		System.out.println("==============="+br+"===================");
		WebDriver localdriver=null;
		localdriver = getBrowser(br);		
		localdriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return localdriver;
	}
	
	
	public WebDriver getBrowser(String br) {
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
	
}
