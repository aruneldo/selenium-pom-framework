package utils;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {}

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(FrameworkConstants.CONFIG_PATH)) {
            properties.load(input);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load " + FrameworkConstants.CONFIG_PATH, e);
        }
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        return PROPERTIES.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
