package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.utilities.GenericUtils;

import utils.ExtentReports.ExtentTestManager;

//Constructor

public class AmazonPage extends GenericUtils {
	private WebDriver driver;

	public AmazonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Hello, Sign in')]")
	private WebElement eleSignin;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement eleEmail;

	@FindBy(id = "continue")
	private WebElement eleContinue;

	@FindBy(id = "ap_password")
	private WebElement elePassword;

	@FindBy(id = "signInSubmit")
	private WebElement eleSignInButton;

	@FindBy(xpath = "//input[@name='field-keywords']")
	private WebElement eleKeywords;

	@FindBy(xpath = "//input[@value='Go']")
	private WebElement eleGo;

	public void amazonLogin() {
		clickSimply(eleSignin);
		sendKeysSimple(eleEmail, "Amazon@everythingeshop1.33mail.com");
		if (driver.findElement(By.id("continue")).isDisplayed())
			clickSimply(eleContinue);
		sendKeysSimple(elePassword, "Amazon123");
		clickSimply(eleSignInButton);
		captureScreenAndAttachExtent(driver);
	}
	
	public void amazonLogin(String sUser,String sPwd) {
		clickSimply(eleSignin);
		sendKeysSimple(eleEmail,sUser);
		if (driver.findElement(By.id("continue")).isDisplayed())
			clickSimply(eleContinue);
		sendKeysSimple(elePassword, sPwd);
		clickSimply(eleSignInButton);
		captureScreenAndAttachExtent(driver);
	}
	
	public void amazonSearch(String sKeyword) {
		sendKeysSimple(eleKeywords, sKeyword);
		clickSimply(eleGo);
		ExtentTestManager.getTest().log(LogStatus.PASS, sKeyword+" Found");
		captureScreenAndAttachExtent(driver);
	}

}
