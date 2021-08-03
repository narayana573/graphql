package enquiryAPI;

import com.relevantcodes.extentreports.LogStatus;
import apiConfigs.APIPath;
import apiVerifications.APIVerification;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getEnquiry extends baseTestCase {

	//@Test
	public void getEnquiryTest() {

		RestAssured.given().when().get(APIPath.apiPath.GET_List_Enquires).then();

		test.log(LogStatus.INFO, "My Test is Starting..........");

		Response response = RestAssured.given().when().get(APIPath.apiPath.GET_List_Enquires);

		APIVerification.responseKeyValidation(response, "channel");
		APIVerification.responseCodeValidation(response, 200);
		APIVerification.responseTimeValidation(response);
		test.log(LogStatus.INFO, "My Test is Ended..........");

	}

}
