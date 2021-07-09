package apiConfigs;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfigs {

	public Map<String, String> defaultHeaders() {

		Map<String, String> defaultHeaders = new HashMap<String, String>();
		defaultHeaders.put("content-type", "application/json");
		return defaultHeaders;

	}

	public Map<String, String> headersWithToken() {

		Map<String, String> defaultHeaders = new HashMap<String, String>();
		defaultHeaders.put("Authorization", "Basic MjU4NjA3MTE6ZGlnaXQxMjM=");
		return defaultHeaders;

	}

	/*
	 * // Testing code to Access the Headers public static void main(String[] args)
	 * { HeaderConfigs hed = new HeaderConfigs();
	 * 
	 * System.out.println(hed.defaultHeaders().get("content-type"));
	 * System.out.println(hed.defaultHeaders());
	 * 
	 * }
	 */
}
