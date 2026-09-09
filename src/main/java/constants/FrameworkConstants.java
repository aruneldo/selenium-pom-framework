package constants;

import java.io.File;

public final class FrameworkConstants {
    private FrameworkConstants() {}

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String RESOURCES_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "main"
            + File.separator + "resources";
    public static final String CONFIG_PATH = RESOURCES_PATH + File.separator + "config.properties";
    public static final String REPORT_PATH = PROJECT_PATH + File.separator + "reports" + File.separator + "ExtentReport.html";
    public static final String SCREENSHOT_PATH = PROJECT_PATH + File.separator + "screenshots";

    public static final int EXPLICIT_WAIT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 30;

    public static final String BROWSER = "browser";
    public static final String URL = "url";
    public static final String HEADLESS = "headless";
    public static final String EXPLICIT_WAIT_KEY = "explicitWait";
    public static final String PAGE_LOAD_TIMEOUT_KEY = "pageLoadTimeout";
}
