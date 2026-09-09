// ScreenshotUtil.java
package utils;

import constants.FrameworkConstants;
import driver.DriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ScreenshotUtil {

	private ScreenshotUtil() {
	}

	public static String takeScreenshot(String testName) {

		try {
			Path screenshotDir = Path.of(FrameworkConstants.SCREENSHOT_PATH);

			Files.createDirectories(screenshotDir);

			String safeTestName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");

			Path screenshotPath = screenshotDir.resolve(safeTestName + ".png");

			File source = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

			Files.copy(source.toPath(), screenshotPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

//			return screenshotPath.toAbsolutePath().toString();
			return "screenshots/" + safeTestName + ".png";

		} catch (Exception e) {
			return null;
		}
	}
}