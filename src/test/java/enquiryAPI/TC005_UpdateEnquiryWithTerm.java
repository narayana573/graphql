package enquiryAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC005_UpdateEnquiryWithTerm {

	@Test(dependsOnMethods = { "createHealthEnquiry", "updateEnquiryWithUser" }, priority = 5)
	public void updateEnquiryWithTerm() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithTerm(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID + "\\\", payload: {term: 1}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();

		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithTerm.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithTerm:" + enquiryID);

	}

}
