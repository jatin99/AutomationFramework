package com.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.pageObjects.AmazonPage;
import com.utilities.GenericUtils;;

public class TC_Parallel2 extends GenericUtils {
	@Test(priority = 1)
	public void test() {
		WebDriver wd=getDriverInstance();
		AmazonPage amzPg=new AmazonPage(wd);
		amzPg.amazonLogin();
		amzPg.amazonSearch("Bumblebee");
	}
}