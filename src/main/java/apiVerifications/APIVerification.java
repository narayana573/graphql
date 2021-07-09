package apiVerifications;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import io.restassured.response.Response;

import com.relevantcodes.extentreports.LogStatus;

import utils.ExtentReportListner;

public class APIVerification extends ExtentReportListner {

	public static void responseCodeValidation(Response response, int statusCode) {

		try {
			Assert.assertEquals(statusCode, response.getStatusCode());
			test.log(LogStatus.PASS,
					"Successfully Validated status code, status code is :: " + response.getStatusCode());
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
			test.log(LogStatus.FAIL,
					"Expected Status code is :: " + statusCode + " , insted of getting :: " + response.getStatusCode());
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

	public static void responseTimeValidation(Response response) {
		try {
			long time = response.time();
			test.log(LogStatus.INFO, "Api response time is :: " + time);
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

	public static void responseKeyValidation(Response response, String Key) {
		try {

			JSONObject obj = new JSONObject(response.getBody().asString());
			test.log(LogStatus.INFO, "Validate Values are:" + obj.getString(Key));

		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

	public static void responseKeyValidationFromJsonObject(Response response, String Key) {
		try {

			JSONObject json = new JSONObject(response.getBody().asString());
			if (json.has(Key) && json.get(Key) != null) {
				test.log(LogStatus.PASS, "Successfully Validate Value of:" + Key + " it is " + json.getString(Key));

			} else {
				test.log(LogStatus.FAIL, "Key is Not Avaliable");
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

	public static void responseKeyValidationfromArray(Response response, String key) {
		try {
			JSONArray array = new JSONArray(response.getBody().asString());
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				test.log(LogStatus.PASS, "Validetd values are  " + obj.get(key));

			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, e.fillInStackTrace());
		}
	}

}
