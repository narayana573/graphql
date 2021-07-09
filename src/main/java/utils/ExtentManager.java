package utils;

import com.relevantcodes.extentreports.ExtentReports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {

		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss");
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("IST"));
		String currentDateTime = dateTimeInGMT.format(new Date());

		if (extent == null) {
			// Set HTML reporting file location
			String workingDir = System.getProperty("user.dir");
			String fileSeparator = System.getProperty("file.separator");

			extent = new ExtentReports(workingDir + fileSeparator + "ExtentReports" + fileSeparator
					+ "ExtentReportResults_" + currentDateTime + ".html", true);
		}
		return extent;
	}
}
