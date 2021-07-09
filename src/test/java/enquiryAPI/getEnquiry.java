package enquiryAPI;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.APIPath;
import apiVerifications.APIVerification;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getEnquiry extends baseTestCase {
	@Test
	public void getEnquiryTest() {

		RestAssured.given().when().get(APIPath.apiPath.GET_List_Enquires).then().log().all();

		test.log(LogStatus.INFO, "My Test is Starting..........");

		Response response = RestAssured.given().when().get(APIPath.apiPath.GET_List_Enquires);
		
		APIVerification.responseKeyValidation(response, "channel");
		APIVerification.responseCodeValidation(response, 200);
		APIVerification.responseTimeValidation(response);
		test.log(LogStatus.INFO, "My Test is Ended..........");

		

		/*
		 * test.log(LogStatus.INFO, response.getBody().asString());
		 * 
		 * test.log(LogStatus.INFO, " Status Code is:" + response.statusCode());
		 * 
		 * test.log(LogStatus.INFO, " Response Time is:" + response.getTime());
		 * 
		 * test.log(LogStatus.INFO, response.contentType());
		 * 
		 * System.out.println(response.getBody());
		 * System.out.println(response.statusCode());
		 * System.out.println(response.contentType());
		 * System.out.println(response.getTime());
		 * System.out.println(response.cookies());
		 */

		/*
		 * JSONObject obj = new JSONObject(response.getBody().asString());
		 * test.log(LogStatus.INFO, "Values is Passed" + obj.getString("channel"));
		 * test.log(LogStatus.INFO, "My Test is Passed......." );
		 * test.log(LogStatus.INFO, "My Test is Ended.." );
		 * System.out.println(obj.getString("channel"));
		 * System.out.println(obj.getInt("chosen_sumInsured"));
		 */

	}

}
