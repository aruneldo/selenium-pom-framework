package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver, int timeoutSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isDisplayed(By locator) {
        return visible(locator).isDisplayed();
    }

    public boolean isDisplayedIfPresent(By locator) {
        try {
            return isDisplayed(locator);
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void click(By locator) {
        clickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = visible(locator);
        element.clear();
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        }
    }

    public String text(By locator) {
        return visible(locator).getText();
    }

    public void urlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    public void titleContains(String value) {
        wait.until(ExpectedConditions.titleContains(value));
    }
}
