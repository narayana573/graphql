package enquiryAPI;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC003_UpdateEnquiryWithDiseases extends baseTestCase {

	@Test(groups = { "smoke" })
	public void updateEnquiryWithDiseases() {

		test.log(LogStatus.INFO, "updateEnquiryWithDiseases:Test Has be Started");
		//RestAssured.baseURI = "https://api.insuranceinbox.in";
		//RestAssured.baseURI = FileEnv.envAndFile().get("ServerUrl");

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithDiseases(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID
				+ "\\\", payload: {diseases: []}) {\\n    enquiry_id\\n    channel\\n    location\\n    userId\\n    created_by\\n    created_by_userId\\n    chosen_sumInsured\\n    chosen_term\\n    created_at\\n    updated_at\\n  }\\n}\\n\"}";

		Response response = RestAssured.given()
								.contentType("application/json")
									.body(query)
										.when()
											.post("/graphql-api/graphql")
												.then()
													.extract()
														.response();

		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithDiseases.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithDiseases:" + enquiryID);
		test.log(LogStatus.PASS, "updateEnquiryWithDiseases:Test Has be Passed");

	}
}
