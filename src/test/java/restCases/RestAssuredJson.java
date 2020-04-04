package restCases;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.utilities.GenericUtils;
import com.utilities.RestUtils;

import io.restassured.response.Response;
import utils.ExtentReports.ExtentTestManager;

public class RestAssuredJson extends GenericUtils {

final static String ROOT_URI = "https://reqres.in";

@Test(priority = 1)
public void simple_get_test() {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Rest Sample Case Started");
	String testName="simple_get_test";
	Response response = get(ROOT_URI + "/api/users?page=2");
	System.out.println(response.asString());
	ExtentTestManager.getTest().log(LogStatus.PASS, "Rest Sample Case Response received"+response.asString());
	System.out.println(
					response.then().body("data.id[0]", Matchers.equalTo(7))
				   .body("data.email", hasItems("lindsay.ferguson@reqres.in"))
				   .statusCode(200).assertThat()
				   );
	RestUtils restUtil=new RestUtils();
	response=restUtil.getResponse(ROOT_URI + "/api/users?page=2");
	restUtil.validateResponseHasItem(response, "data.id", 7);
//	restUtil.validateResponseMatcher(response, "data.id", 7);
	restUtil.validateResponseHasItem(response, "data.email", "lindsay.ferguson@reqres.in");
	ExtentTestManager.getTest().log(LogStatus.INFO, "Rest Sample Case ID & EMAIL validation success");
					
}

@Test(priority = 2)
public void simple_get_test_fail() {
	ExtentTestManager.getTest().log(LogStatus.INFO, "Rest Sample Case Started");
	String testName="simple_get_test";
	Response response = get(ROOT_URI + "/api/users?page=2");
	System.out.println(response.asString());
	ExtentTestManager.getTest().log(LogStatus.PASS, "Rest Sample Case Response received"+response.asString());
	System.out.println(
					response.then().body("data.id[0]", Matchers.equalTo(7))
				   .body("data.email", hasItems("lindsay.ferguson@reqres.in"))
				   .statusCode(200).assertThat()
				   );
	RestUtils restUtil=new RestUtils();
	response=restUtil.getResponse(ROOT_URI + "/api/users?page=2");
	restUtil.validateResponseHasItem(response, "data.id", 70);
//	restUtil.validateResponseMatcher(response, "data.id", 7);
	restUtil.validateResponseHasItem(response, "data.email", "lindsay.ferguson@reqres.in");
	ExtentTestManager.getTest().log(LogStatus.INFO, "Rest Sample Case ID & EMAIL validation success");
					
}
}

