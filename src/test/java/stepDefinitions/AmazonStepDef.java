package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pageObjects.AmazonPage;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager;

public class AmazonStepDef extends BaseStepDefinitionClass  {
	public static WebDriver stepDefDriver;
	@Given("^User Initializes TC for \"([^\"]*)\"$")
	public void user_Initializes_TC_for(String browser) throws Throwable {
		stepDefDriver=getBrowser(browser);
		ExtentManager.getReporter();
		ExtentTestManager.startTest("Cukes Initialized "+Scenario.class.getName());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Cukes Initialized");
	}

	@Then("^End TC$")
	public void end_TC() throws Throwable {
		stepDefDriver.quit();
		ExtentTestManager.getTest().log(LogStatus.INFO, "Cukes Ends");
		ExtentTestManager.endTest();
	}

	@Given("^user Open \"([^\"]*)\" website$")
	public void user_Open_Amzon_website(String url) throws Throwable {
		try {
			stepDefDriver.manage().window().maximize();
			stepDefDriver.navigate().to(url);
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.ERROR, "User opens website Failed",e);
		}
	}

	@When("^Logins to Amazon website$")
	public void logins_to_Amazon_website() throws Throwable {
		try{
			AmazonPage amz=new AmazonPage(stepDefDriver);
			amz.amazonLogin("Amazon@everythingeshop1.33mail.com", "Amazon123");
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.ERROR, "Logins to Amazon Failed",e);
		}
	}

	@Then("^Search for \"([^\"]*)\"$")
	public void search_for_Key(String sKey) throws Throwable {
		try {
			AmazonPage amz=new AmazonPage(stepDefDriver);
			amz.amazonSearch(sKey);
		}catch(Exception e) {
			ExtentTestManager.getTest().log(LogStatus.ERROR, "Search for keyword Failed",e);
		}
	}
}
