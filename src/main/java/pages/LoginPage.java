package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By EMAIL = By.id("email");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("[data-test='login-submit']");
    private static final By EMAIL_ERROR = By.cssSelector("[data-test='email-error']");
    private static final By PASSWORD_ERROR = By.cssSelector("[data-test='password-error']");
    private static final By LOGIN_ERROR = By.cssSelector("[data-test='login-error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterEmail(String email) {
        type(EMAIL, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(LOGIN_BUTTON);
        return this;
    }

    public boolean isEmailErrorDisplayed() {
        return isDisplayedIfPresent(EMAIL_ERROR);
    }

    public boolean isPasswordErrorDisplayed() {
        return isDisplayedIfPresent(PASSWORD_ERROR);
    }

    public boolean isLoginErrorDisplayed() {
        return isDisplayedIfPresent(LOGIN_ERROR);
    }

    public boolean isDisplayed() {
        return isDisplayed(EMAIL) && isDisplayed(PASSWORD);
    }
}
