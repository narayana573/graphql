package baseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import utils.ExtentReportListner;
import utils.FileEnv;

@Listeners(ExtentReportListner.class)
public class baseTestCase extends ExtentReportListner {
	@BeforeClass
	public void BaseTest() {

		RestAssured.baseURI = FileEnv.envAndFile().get("ServerUrl");
	}

	@Test
	public void utilsTest() {
		test.log(LogStatus.INFO, "Test Has be Started");
		test.log(LogStatus.PASS, "Test Has be Passed");

		System.out.println(FileEnv.envAndFile().get("ServerUrl"));

	}

}
