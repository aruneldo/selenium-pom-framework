package dataproviders;

import config.EnvConfig;
import data.LoginData;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	@DataProvider(name = "loginData", parallel = true)
	public static Object[][] loginData() {

		String email = EnvConfig.get("LOGIN_EMAIL");
		String password = EnvConfig.get("LOGIN_PASSWORD");

		return new Object[][] {

				{ new LoginData("Valid Login", email, password, true) },
				{ new LoginData("Invalid Email", "invalid@example.com", "welcome01", false) },
				{ new LoginData("Invalid Password", "customer@practicesoftwaretesting.com", "wrongpassword", false) },
				{ new LoginData("Empty Email", "", "welcome01", false) },
				{ new LoginData("Empty Password", "customer@practicesoftwaretesting.com", "", false) },
				{ new LoginData("Empty Credentials", "", "", false) } };
	}

}