// ExtentManager.java
package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.FrameworkConstants;

public final class ExtentManager {

	private static ExtentReports extent;

	private ExtentManager() {
	}

	public static synchronized ExtentReports getExtentReports() {
		if (extent == null) {
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(FrameworkConstants.REPORT_PATH);

			sparkReporter.config().setDocumentTitle("Automation Test Report");
			sparkReporter.config().setReportName("Selenium POM Framework");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
		}

		return extent;
	}

	public static synchronized void flush() {
		if (extent != null) {
			extent.flush();
		}
	}
}