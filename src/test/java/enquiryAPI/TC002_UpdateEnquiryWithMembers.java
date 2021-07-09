package enquiryAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC002_UpdateEnquiryWithMembers {

	@Test(dependsOnMethods = { "createHealthEnquiry" }, priority = 2)
	public void updateEnquiryWithMembers() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithMembers(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID
				+ "\\\", payload: {members: [{member_id: \\\"self\\\", member: \\\"self\\\", type: \\\"adult\\\", visibleName: \\\"You\\\", age: 22, age_in_months: 0, gender: \\\"M\\\"}]}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithMembers.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithMembers:" + enquiryID);

	}

}
