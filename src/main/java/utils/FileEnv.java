package utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileEnv {

	public static Map<String, String> fileandenv = new HashMap<String, String>();
	public static Properties propMain = new Properties();
	public static Properties propset = new Properties();

	public static Map<String, String> envAndFile() {
		
		
		// Maven env execute
		String environment = System.getenv("env");


		// TestNG env execute
		//String environment = System.getProperty("env");
		try {
			if (environment.equalsIgnoreCase("qa")) {
				FileInputStream fisqa = new FileInputStream(System.getProperty("user.dir") + "/inputs/qa.properties");
				propMain.load(fisqa);
				// System.out.println("URLL>>>>>>>"+propMain.getProperty("ServerUrl"));
				fileandenv.put("ServerUrl", propMain.getProperty("ServerUrl"));
				fileandenv.put("ElasticServerUrl", propMain.getProperty("ElasticServerUrl"));

			} else if (environment.equalsIgnoreCase("prod")) {
				FileInputStream fisprod = new FileInputStream(
						System.getProperty("user.dir") + "/inputs/prod.properties");
				propMain.load(fisprod);
			}
		} catch (Exception e) {

		}

		return fileandenv;

	}

}
