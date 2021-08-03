package enquiryAPI;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC004_UpdateEnquiryWithUser extends baseTestCase {

	@Test(groups = { "smoke" })
	public void updateEnquiryWithUser() {

		test.log(LogStatus.INFO, "updateEnquiryWithUser:Test Has be Started");
		//RestAssured.baseURI = "https://api.insuranceinbox.in";
		//RestAssured.baseURI = FileEnv.envAndFile().get("ServerUrl");

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithUser(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID
				+ "\\\", payload: {userId: \\\"aee49858-7f28-3532-b570-f91f096d5303\\\", created_by_userId: \\\"aee49858-7f28-3532-b570-f91f096d5303\\\"}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = RestAssured.given().contentType("application/json")
				.body(query)
					.when()
						.post("/graphql-api/graphql")
							.thenReturn();

		System.out.println(
				"enquiryID received from Response updateEnquiryWithUser:::!!!!!!::" + response.getBody().asString());

		// System.out.println("enquiryID received from Response
		// updateEnquiryWithUser:::::" + enquiryID);
		test.log(LogStatus.PASS, "updateEnquiryWithUser:Test Has be Passed");


	}
}
