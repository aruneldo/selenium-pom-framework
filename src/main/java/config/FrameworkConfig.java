package config;

import constants.FrameworkConstants;
import utils.ConfigReader;

public record FrameworkConfig(String browser, String url, boolean headless, int explicitWait, int pageLoadTimeout) {

	public static FrameworkConfig load() {

		String browser = getConfig(FrameworkConstants.BROWSER, "chrome");
		String url = getConfig(FrameworkConstants.URL, "https://practicesoftwaretesting.com/auth/login");
		String headless = getConfig(FrameworkConstants.HEADLESS, "false");
//		String explicitWait = getConfig(FrameworkConstants.EXPLICIT_WAIT, "10");
//		String pageLoadTimeout = getConfig(FrameworkConstants.PAGE_LOAD_TIMEOUT, "30");
		String explicitWait = getConfig("EXPLICIT_WAIT", "10s");
		String pageLoadTimeout = getConfig("PAGE_LOAD_TIMEOUT", "30");

		return new FrameworkConfig(browser, url, Boolean.parseBoolean(headless.trim()),
				Integer.parseInt(explicitWait.trim()), Integer.parseInt(pageLoadTimeout.trim()));
	}

	private static String getConfig(String key, String defaultValue) {

		// 1. Environment variable / .env
		String value = EnvConfig.get(key);

		if (value != null && !value.isBlank()) {
			return value;
		}

		// 2. config.properties / JVM system property
		return ConfigReader.get(key, defaultValue);
	}
}
