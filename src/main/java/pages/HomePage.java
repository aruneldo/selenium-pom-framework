package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private static final By WELCOME_BANNER = By.cssSelector(".lead");
    private static final By LOGIN_LINK = By.cssSelector("[data-test='nav-sign-in']");
    private static final By PROFILE_MENU = By.cssSelector(".nav-profile");
    private static final By LOGOUT_BUTTON = By.cssSelector(".logout-button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeBanner() {
        return getText(WELCOME_BANNER);
    }

    public boolean isWelcomeBannerDisplayed() {
        return isDisplayedIfPresent(WELCOME_BANNER);
    }

    public LoginPage clickLogin() {
        click(LOGIN_LINK);
        return new LoginPage(driver);
    }

    public HomePage openProfileMenu() {
        click(PROFILE_MENU);
        return this;
    }

    public LoginPage clickLogout() {
        click(LOGOUT_BUTTON);
        return new LoginPage(driver);
    }
}
