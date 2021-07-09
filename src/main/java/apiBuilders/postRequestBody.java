package apiBuilders;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import utils.DataGenerator;

public class postRequestBody {


	@Test(dataProvider = "Excel", dataProviderClass =DataGenerator.class )
	public Map<String, String> isMedplusUser(String mobile ) {
		Map<String, String> body = new HashMap<String, String>();
		body.put("mobileNumber", ""+mobile+"");
		return body;
	}

}
