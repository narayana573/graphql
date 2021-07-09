package enquiryAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC004_UpdateEnquiryWithUser {

	@Test(dependsOnMethods = { "createHealthEnquiry" }, priority = 4)
	public void updateEnquiryWithUser() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithUser(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID
				+ "\\\", payload: {userId: \\\"aee49858-7f28-3532-b570-f91f096d5303\\\", created_by_userId: \\\"aee49858-7f28-3532-b570-f91f096d5303\\\"}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).log().all().when().log().all()
				.post("/graphql-api/graphql").thenReturn();

		System.out.println(
				"enquiryID received from Response updateEnquiryWithUser:::!!!!!!::" + response.getBody().asString());

		// System.out.println("enquiryID received from Response
		// updateEnquiryWithUser:::::" + enquiryID);

	}
}
