package driver;

import config.FrameworkConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {}

    public static void createDriver() {
        FrameworkConfig config = FrameworkConfig.load();
        WebDriver driver = switch (config.browser().trim().toLowerCase()) {
            case "chrome" -> createChrome(config);
            case "firefox" -> createFirefox(config);
            case "edge" -> createEdge(config);
            default -> throw new IllegalArgumentException("Unsupported browser: " + config.browser());
        };

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(config.pageLoadTimeout()));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    private static WebDriver createChrome(FrameworkConfig config) {
        ChromeOptions options = new ChromeOptions();
        if (config.headless()) options.addArguments("--headless=new", "--window-size=1920,1080");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox(FrameworkConfig config) {
        FirefoxOptions options = new FirefoxOptions();
        if (config.headless()) options.addArguments("--headless");
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdge(FrameworkConfig config) {
        EdgeOptions options = new EdgeOptions();
        if (config.headless()) options.addArguments("--headless=new", "--window-size=1920,1080");
        return new EdgeDriver(options);
    }
}
