package base;

import config.FrameworkConfig;
import driver.DriverFactory;
import driver.DriverManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.LoggerUtil;

public abstract class BaseTest {

	protected Logger logger;

	@BeforeMethod
	public void setUp() {

		logger = LoggerUtil.getLogger(this.getClass());

		FrameworkConfig config = FrameworkConfig.load();

		logger.info("Starting test");

		logger.info("Browser: {}", config.browser());

		logger.info("Headless: {}", config.headless());

		logger.info("URL: {}", config.url());

		DriverFactory.createDriver();

		getDriver().get(config.url());

		logger.info("Application launched successfully");
	}

	@AfterMethod
	public void tearDown() {

		logger.info("Closing browser");

		DriverManager.quitDriver();
	}

	public WebDriver getDriver() {
		return DriverManager.getDriver();
	}
}