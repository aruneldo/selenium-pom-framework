package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
    private static final By ACCOUNT_NAV = By.cssSelector("[data-test='nav-menu']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return getCurrentUrl().contains("account") || isDisplayedIfPresent(ACCOUNT_NAV);
    }
}
