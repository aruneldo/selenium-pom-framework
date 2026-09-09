package listeners;

import com.aventstack.extentreports.Status;
import driver.DriverManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getExtentReports();
	}

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTestManager.setTest(ExtentManager.getExtentReports().createTest(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentTestManager.getTest().log(Status.PASS, "Test passed");

		ExtentTestManager.unload();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());

		String screenshot = ScreenshotUtil.takeScreenshot(result.getMethod().getMethodName());

		if (screenshot != null) {
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshot);
		}

		ExtentTestManager.unload();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");

		ExtentTestManager.unload();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.flush();
	}
}