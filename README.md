# Owner of the Automation Framework
@Author jatin@testautomationacademy.in

## Installation
```bash
mvn install
```
## Add the drivers
Update the drivers for chrome browser from https://chromedriver.chromium.org/ and add it into the Drivers

## Add the test data
Set your test data in the xlsx file in the Data folder

## Usage

```Java
public class TC_Parallel1 extends GenericUtils {
	@Test(priority = 1,dataProvider = "LoginAmazonData", dataProviderClass = XLUtils.class)
	public void test(String sUser,String sPwd,String sKey) {
		AmazonPage amzPg=new AmazonPage(Basedriver);
		amzPg.amazonSearch(sKey);
	}
```
All testcases must extends Generic Utils to use the features


## License
[MIT](https://choosealicense.com/licenses/mit/)

## Liked the Framework? 
Connect to me on https://www.linkedin.com/in/jatin-sharma-bb365153/ 
Planning for a job change? 
Enroll for a 3 months course live with me! and take learn the top automation testing and devops skills live with me!

