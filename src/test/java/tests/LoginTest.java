package tests;

import base.BaseTest;
import data.LoginData;
import dataproviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void loginTest(LoginData data) {
		logger.info("Executing test case: {}", data.testCase());

		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.enterEmail(data.email()).enterPassword(data.password()).clickLogin();

		boolean emailEmpty = data.email().isBlank();
		boolean passwordEmpty = data.password().isBlank();

		if (data.valid()) {
			Assert.assertTrue(getDriver().getCurrentUrl().contains("account"),
					"Expected successful login but current URL was: " + getDriver().getCurrentUrl());

		} else if (emailEmpty && passwordEmpty) {
			Assert.assertTrue(loginPage.isEmailErrorDisplayed(),
					"Expected Email Validation error for: " + data.testCase());
			Assert.assertTrue(loginPage.isPasswordErrorDisplayed(),
					"Expected Password Validation error for: " + data.testCase());

		} else if (emailEmpty) {
			Assert.assertTrue(loginPage.isEmailErrorDisplayed(),
					"Expected Email Validation error for: " + data.testCase());

		} else if (passwordEmpty) {
			Assert.assertTrue(loginPage.isPasswordErrorDisplayed(),
					"Expected Password Validation error for: " + data.testCase());

		} else {
			// Email and password have values,
			// but credentials should be invalid
			Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "Expected login error for: " + data.testCase());
		}

	}
}
