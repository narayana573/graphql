package enquiryAPI;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.FileEnv;

public class TC006_UpdateEnquiryWithSumInsured extends baseTestCase {

	@Test(groups = { "smoke" })
	public void updateEnquiryWithSumInsured() {
		test.log(LogStatus.INFO, "updateEnquiryWithSumInsured:Test Has be Started");
		//RestAssured.baseURI = "https://api.insuranceinbox.in";
		RestAssured.baseURI = FileEnv.envAndFile().get("ServerUrl");

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithSumInsured(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID + "\\\", payload: {sumInsured: 300000}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = RestAssured.given().contentType("application/json")
				.body(query)
				.when()
				.post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();

		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithSumInsured.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithSumInsured:" + enquiryID);
		test.log(LogStatus.PASS, "updateEnquiryWithSumInsured:Test Has be Passed");

	}
}
