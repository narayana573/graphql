package enquiryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import utils.FileEnv;
import apiBuilders.postRequestBody;
import apiConfigs.HeaderConfigs;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import baseTest.baseTestCase;

public class TC002_UpdateEnquiryWithMembers extends baseTestCase {
	HeaderConfigs headers = new HeaderConfigs();
	postRequestBody builder = new postRequestBody();

	public static String jsonAsString;
	@DataProvider
	public Object[][] dataupdateEnquiryWithMembers() {
		return new Object[][] { { "self", "22", "M" } };
	}
	
	@Test(dataProvider = "dataupdateEnquiryWithMembers",groups = { "smoke" })
	public void updateEnquiryWithMembers(String member, String age, String gender) {

		test.log(LogStatus.INFO, "updateEnquiryWithMembers:Test Has be Started");
		//RestAssured.baseURI = "https://api.insuranceinbox.in";
		//RestAssured.baseURI = FileEnv.envAndFile().get("ServerUrl");
		
		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithMembers(enquiryId: \\\""
				+ TC001_CreateEnquiry.enquiryID
				+ "\\\", payload: {members: [{member_id: \\\""+member+"\\\", member: \\\""+member+"\\\", type: \\\"adult\\\", visibleName: \\\"You\\\", age: "+age+", age_in_months: 0, gender: \\\""+gender+"\\\"}]}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = RestAssured.given()
								.contentType("application/json")
									.body(query)
										.when()
											.post("/graphql-api/graphql")
												.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithMembers.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithMembers:" + enquiryID);
		test.log(LogStatus.PASS, "updateEnquiryWithMembers:Test Has be Passed");


	}
	
	
	@Test(dataProvider = "dataupdateEnquiryWithMembers",groups = { "smoke" })
	public void verifyupdateEnquiryWithMembers(String member, String age, String gender) throws InterruptedException {

		test.log(LogStatus.INFO, "Verify updateEnquiryWithMembers: Test Has be Started");
		
		Thread.sleep(12000);
		Response response = RestAssured.given()
								.contentType("application/json")
										.when()
											.get(FileEnv.envAndFile().get("ElasticServerUrl")+"enquiries/items/"+TC001_CreateEnquiry.enquiryID)
												.thenReturn();
		JsonPath jsonPathEvaluator = response.jsonPath();
		String verifymember = jsonPathEvaluator.get("_source.members[0].member");
		String verifyage =  Integer.toString(jsonPathEvaluator.get("_source.members[0].age"));
		String verifygender = jsonPathEvaluator.get("_source.members[0].gender");
		
		
		if(verifymember.equals(member) && verifygender.equals(gender) && verifyage.equals(age) ) 
										{
			test.log(LogStatus.PASS, "TC002_UpdateEnquiryWithMembers: Test Has be Passed");

		}
		else {
			System.out.println("TC002_UpdateEnquiryWithMembers Not Validated .. Some thing went wrong!!!!!!");
			test.log(LogStatus.FAIL, "TC002_UpdateEnquiryWithMembers: Test Has be Failed");

		}
	}

}
