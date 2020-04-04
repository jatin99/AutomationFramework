package com.testCases;

import org.testng.annotations.Test;

import com.pageObjects.AmazonPage;
import com.utilities.GenericUtils;
import com.utilities.XLUtils;;

public class TC_Parallel1 extends GenericUtils {
	@Test(priority = 1,dataProvider = "LoginAmazonData", dataProviderClass = XLUtils.class)
	public void test(String sUser,String sPwd,String sKey) {
		AmazonPage amzPg=new AmazonPage(Basedriver);
//		amzPg.amazonLogin(sUser,sPwd);
		amzPg.amazonSearch(sKey);
	}

	
}