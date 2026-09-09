package pages;

import config.FrameworkConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, FrameworkConfig.load().explicitWait());
    }

    protected void click(By locator) {
        wait.click(locator);
    }

    protected void type(By locator, String text) {
        wait.type(locator, text);
    }

    protected String getText(By locator) {
        return wait.text(locator);
    }

    protected boolean isDisplayed(By locator) {
        return wait.isDisplayed(locator);
    }

    protected boolean isDisplayedIfPresent(By locator) {
        return wait.isDisplayedIfPresent(locator);
    }

    protected void waitForUrl(String value) {
        wait.urlContains(value);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
