package enquiryAPI;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC005_UpdateEnquiryWithTerm extends baseTestCase {

	@Test(groups = { "smoke" })
	public void updateEnquiryWithTerm() {

		test.log(LogStatus.INFO, "updateEnquiryWithTerm:Test Has be Started");
		//RestAssured.baseURI = "https://api.insuranceinbox.in";
		//RestAssured.baseURI = FileEnv.envAndFile().get("ServerUrl");

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithTerm(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID + "\\\", payload: {term: 1}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = RestAssured.given().contentType("application/json")
				.body(query)
				.when()
				.post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();

		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithTerm.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithTerm:" + enquiryID);
		test.log(LogStatus.PASS, "updateEnquiryWithTerm:Test Has be Passed");


	}

}
