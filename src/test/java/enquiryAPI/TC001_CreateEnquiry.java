package enquiryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import apiBuilders.postRequestBody;
import apiConfigs.APIPath;
import apiConfigs.HeaderConfigs;

import static io.restassured.RestAssured.given;
import baseTest.baseTestCase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.DataGenerator;

public class TC001_CreateEnquiry extends baseTestCase {

	HeaderConfigs headers = new HeaderConfigs();
	postRequestBody builder = new postRequestBody();

	@Test(dataProvider = "Excel", dataProviderClass = DataGenerator.class)
	public void testdataprovider(String channel, String pin) {
		System.out.println("Channel is::::: " + channel);
		System.out.println("Channel is::::: " + pin);

	}

	@Test(dataProvider = "Excel", dataProviderClass = DataGenerator.class)
	public void testdataprovider(String channel) {
		System.out.println("Channel is::::: " + channel);
	}

	@Test(dataProvider = "Excel", dataProviderClass = DataGenerator.class)
	public void isMedplusUser(String mobile) {

		Response response = RestAssured.given().headers(headers.defaultHeaders()).body(builder.isMedplusUser(mobile))
				.when().log().all().post(APIPath.apiPath.POST_IS_MedplusUser);

		System.out.println(response.asString());
		System.out.println(response.statusCode());
		System.out.println();
		System.out.println();

		// JsonPath jsonPathEvaluator = response.jsonPath();

		// String enquiryID =
		// jsonPathEvaluator.get("data.updateEnquiryWithDiseases.enquiry_id");
		// System.out.println("enquiryID received from Response
		// updateEnquiryWithDiseases:" + enquiryID);

	}

	public static String jsonAsString;
	public static String enquiryID;

	@DataProvider
	public Object[][] createEnquiryData() {
		return new Object[][] { { "medplus", "533220", "USER" } };
	}

	@Test(dataProvider = "createEnquiryData", priority = 1)
	public void createHealthEnquiry(String channel, String location, String created_by) {

		RestAssured.baseURI = "https://api.insuranceinbox.in";
		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  createEnquiry(payload: {channel: \\\""
				+ channel + "\\\", location: \\\"" + location + "\\\", created_by: \\\"" + created_by
				+ "\\\"}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.createEnquiry.enquiry_id");
		System.out.println("enquiryID received from Response createHealthEnquiry:" + enquiryID);
		TC001_CreateEnquiry.enquiryID = enquiryID;

	}
/*
	
	@Test(dependsOnMethods = { "createHealthEnquiry" }, priority = 2)
	public void updateEnquiryWithMembers() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithMembers(enquiryId: \\\""
				+ enquiryID
				+ "\\\", payload: {members: [{member_id: \\\"self\\\", member: \\\"self\\\", type: \\\"adult\\\", visibleName: \\\"You\\\", age: 22, age_in_months: 0, gender: \\\"M\\\"}]}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithMembers.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithMembers:" + enquiryID);

	}

	@Test(dependsOnMethods = { "createHealthEnquiry", "updateEnquiryWithMembers" }, priority = 3)
	public void updateEnquiryWithDiseases() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithDiseases(enquiryId: \\\""
				+ enquiryID
				+ "\\\", payload: {diseases: []}) {\\n    enquiry_id\\n    channel\\n    location\\n    userId\\n    created_by\\n    created_by_userId\\n    chosen_sumInsured\\n    chosen_term\\n    created_at\\n    updated_at\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.then().extract().response();

		JsonPath jsonPathEvaluator = response.jsonPath();
		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithDiseases.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithDiseases:" + enquiryID);

	}

	@Test(dependsOnMethods = { "createHealthEnquiry" }, priority = 4)
	public void updateEnquiryWithUser() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithUser(enquiryId: \\\""
				+ enquiryID
				+ "\\\", payload: {userId: \\\"aee49858-7f28-3532-b570-f91f096d5303\\\", created_by_userId: \\\"aee49858-7f28-3532-b570-f91f096d5303\\\"}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).log().all().when().log().all()
				.post("/graphql-api/graphql").thenReturn();

		System.out.println(
				"enquiryID received from Response updateEnquiryWithUser:::!!!!!!::" + response.getBody().asString());

		System.out.println("enquiryID received from Response updateEnquiryWithUser:::::" + enquiryID);

	}

	@Test(dependsOnMethods = { "createHealthEnquiry", "updateEnquiryWithUser" }, priority = 5)
	public void updateEnquiryWithTerm() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithTerm(enquiryId: \\\""
				+ enquiryID + "\\\", payload: {term: 1}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();

		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithTerm.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithTerm:" + enquiryID);

	}

	@Test(dependsOnMethods = { "createHealthEnquiry", "updateEnquiryWithTerm" }, priority = 6)
	public void updateEnquiryWithSumInsured() {

		RestAssured.baseURI = "https://api.insuranceinbox.in";

		String query = "{\"operationName\":null,\"variables\":{},\"query\":\"mutation {\\n  updateEnquiryWithSumInsured(enquiryId: \\\""
				+ enquiryID + "\\\", payload: {sumInsured: 300000}) {\\n    enquiry_id\\n  }\\n}\\n\"}";

		Response response = given().contentType("application/json").body(query).when().post("/graphql-api/graphql")
				.thenReturn();

		JsonPath jsonPathEvaluator = response.jsonPath();

		String enquiryID = jsonPathEvaluator.get("data.updateEnquiryWithSumInsured.enquiry_id");
		System.out.println("enquiryID received from Response updateEnquiryWithSumInsured:" + enquiryID);

	}

*/
}
