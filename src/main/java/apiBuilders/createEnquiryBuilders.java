package apiBuilders;

import java.util.Map;

public class createEnquiryBuilders {

	public Map<String, String> createHEnquiry() {
		return null;

		/*
		 * createEnquiryPayload query = new createEnquiryPayload(); query.setQuery(
		 * "mutation {   createEnquiry(     payload: { channel: \"medplus\", location: \"533220\", created_by: \"USER\" }   ) {     enquiry_id   } }"
		 * );
		 * 
		 * given().contentType(ContentType.JSON).body(query).when()
		 * .post("https://api.insuranceinbox.in/graphql-api/graphql").then().assertThat(
		 * ).statusCode(200).and() .log().all();
		 * 
		 * 
		 * Map<String, String> body = new HashMap<String, String>();
		 * body.put("content-type", "application/json"); return body;
		 * 
		 */
	}
}
