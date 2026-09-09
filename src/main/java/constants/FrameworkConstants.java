package constants;

import java.io.File;

public final class FrameworkConstants {
	private FrameworkConstants() {
	}

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String RESOURCES_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources";
	public static final String CONFIG_PATH = RESOURCES_PATH + File.separator + "config.properties";
	public static final String REPORT_PATH = PROJECT_PATH + File.separator + "reports" + File.separator
			+ "ExtentReport.html";
	public static final String SCREENSHOT_PATH = PROJECT_PATH + File.separator + "screenshots";

	public static final String BROWSER = "BROWSER";
	public static final String URL = "URL";
	public static final String HEADLESS = "HEADLESS";
	public static final String EXPLICIT_WAIT = "EXPLICIT_WAIT";
	public static final String PAGE_LOAD_TIMEOUT = "PAGE_LOAD_TIMEOUT";
}
