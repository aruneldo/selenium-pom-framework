package config;

import constants.FrameworkConstants;
import utils.ConfigReader;

public record FrameworkConfig(
        String browser,
        String url,
        boolean headless,
        int explicitWait,
        int pageLoadTimeout) {

    public static FrameworkConfig load() {
        return new FrameworkConfig(
                ConfigReader.get(FrameworkConstants.BROWSER, "chrome"),
                ConfigReader.get(FrameworkConstants.URL, "https://practicesoftwaretesting.com/auth/login"),
                Boolean.parseBoolean(ConfigReader.get(FrameworkConstants.HEADLESS, "false")),
                Integer.parseInt(ConfigReader.get(FrameworkConstants.EXPLICIT_WAIT_KEY,
                        String.valueOf(FrameworkConstants.EXPLICIT_WAIT))),
                Integer.parseInt(ConfigReader.get(FrameworkConstants.PAGE_LOAD_TIMEOUT_KEY,
                        String.valueOf(FrameworkConstants.PAGE_LOAD_TIMEOUT)))
        );
    }
}
