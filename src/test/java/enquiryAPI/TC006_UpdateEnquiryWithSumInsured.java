package enquiryAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC006_UpdateEnquiryWithSumInsured {

	@Test(dependsOnMethods = { "createHealthEnquiry", "updateEnquiryWithTerm" }, priority = 6)
	public void updateEnquiryWithSumInsured() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithSumInsured(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID + "\\\", payload: {sumInsured: 300000}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();

		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithSumInsured.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithSumInsured:" + enquiryID);

	}
}
