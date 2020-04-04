package com.utilities;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import utils.ExtentReports.ExtentTestManager;

public class RestUtils {
	public Response getResponse(String sROOT_URI) {
		Response response = get(sROOT_URI);
		//System.out.println(response.asString() + "  =========== " + sROOT_URI);
//		ExtentTestManager.getTest().log(LogStatus.PASS,
//				"Rest URI:" + sROOT_URI + "\n Response received::" + response.asString());
		return response;
	}

	public void validateResponseMatcher(Response response, String sPath, String sValue) {
		try {
			response.then().body(sPath, Matchers.equalTo(sValue)).statusCode(200).assertThat();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Rest Path:" + sPath + " Value" + sValue);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Rest Path:" + sPath + " Value" + e.getMessage());
		}
	}

	public void validateResponseHasItem(Response response, String sPath, String sValue) {
		try {
			response.then().body(sPath, hasItems(sValue)).statusCode(200).assertThat();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Rest Path:" + sPath + " Value" + sValue);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Rest Path:" + sPath + " Value" + e.getMessage());
		}
	}

	public void validateResponseMatcher(Response response, String sPath, int iValue) {
		try {
			response.then().body(sPath, Matchers.equalTo(iValue)).statusCode(200).assertThat();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Rest Path:" + sPath + " Value" + iValue);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Rest Path:" + sPath + " Value" + e.getMessage());
		}
	}

	public void validateResponseHasItem(Response response, String sPath, int iValue) {
		try {
			response.then().body(sPath, hasItems(iValue)).statusCode(200).assertThat();
			ExtentTestManager.getTest().log(LogStatus.PASS, "Rest Path:" + sPath + " Value" + iValue);
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Rest Path:" + sPath + " Value" + e.getMessage());
		}
	}
}