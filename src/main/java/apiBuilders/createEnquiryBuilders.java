package apiBuilders;

import java.util.HashMap;
import java.util.Map;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class createEnquiryBuilders {

	public Map<String, String> createHEnquiry() {

		createEnquiryPayload query = new createEnquiryPayload();
		query.setQuery(
				"mutation {   createEnquiry(     payload: { channel: \"medplus\", location: \"533220\", created_by: \"USER\" }   ) {     enquiry_id   } }");

		given().contentType(ContentType.JSON).body(query).when()
				.post("https://api.insuranceinbox.in/graphql-api/graphql").then().assertThat().statusCode(200).and()
				.log().all();

		/*
		 * Map<String, String> body = new HashMap<String, String>();
		 * body.put("content-type", "application/json"); return body;
		 */

	}
}
