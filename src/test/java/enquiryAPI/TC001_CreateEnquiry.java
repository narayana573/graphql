package enquiryAPI;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import apiBuilders.postRequestBody;
import apiConfigs.HeaderConfigs;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.FileEnv;

public class TC001_CreateEnquiry extends baseTestCase {

	HeaderConfigs headers = new HeaderConfigs();
	postRequestBody builder = new postRequestBody();

	public static String jsonAsString;
	public static String enquiryID;

	@DataProvider
	public Object[][] createEnquiryData() {
		return new Object[][] { { "medplus", "533220", "USER" } };
	}

	@Test(dataProvider = "createEnquiryData",groups = { "smoke" })
	public void createHealthEnquiry(String channel, String location, String created_by) {

		test.log(LogStatus.INFO, "createHealthEnquiry: Test Has be Started");
		
		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  createEnquiry(payload: {channel: \\\""
				+ channel + "\\\", location: \\\"" + location + "\\\", created_by: \\\"" + created_by
				+ "\\\"}) {\\n    enquiry_id\\n  }\\n}\\n\"}";
		Response response = RestAssured.given()
								.contentType("application/json")
									.body(query)
										.when()
											.post("/graphql-api/graphql")
												.thenReturn();
		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.createEnquiry.enquiry_id");
		System.out.println("enquiryID received from Response createHealthEnquiry:" + enquiryID);
		TC001_CreateEnquiry.enquiryID = enquiryID;
		test.log(LogStatus.PASS, "createHealthEnquiry: Test Has be Passed");
		SoftAssert softassert = new SoftAssert();
		String contenttype = response.header("content-type");
		softassert.assertEquals(contenttype, "application/json");
		softassert.assertAll();
		Reporter.log("................");
		

	}
	
	


	@Test(dataProvider = "createEnquiryData",groups = { "sanity" })
	public void verifyCreateHealthEnquiry(String channel, String location, String created_by) throws InterruptedException {

		test.log(LogStatus.INFO, "Verify createHealthEnquiry: Test Has be Started");
		
		Thread.sleep(12000);
		Response response = RestAssured.given()
								.contentType("application/json")
										.when()
											.get(FileEnv.envAndFile().get("ElasticServerUrl")+"enquiries/items/"+enquiryID)
												.thenReturn();
		JsonPath jsonPathEvaluator = response.jsonPath();
		String verifychannel = jsonPathEvaluator.get("_source.channel");
		String verifylocation = jsonPathEvaluator.get("_source.location");
		String verifycreated_by = jsonPathEvaluator.get("_source.created_by");

	
		if( verifychannel.equals(channel) && verifylocation.equals(location) && verifycreated_by.equals(created_by)   ) 
										{
			test.log(LogStatus.PASS, "TC001_CreateEnquiry: Test Has be Passed");

		}
		else {
			System.out.println("TC001_CreateEnquiry Not Validated .. Some thing went wrong!!!!!!");
		}

		

	}

}
