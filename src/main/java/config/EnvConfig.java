package config;

import io.github.cdimascio.dotenv.Dotenv;

public final class EnvConfig {

	private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();

	private EnvConfig() {
		// Prevent instantiation
	}

	public static String get(String key) {

		// 1. First check system environment variables
		// Used by GitHub Actions
		String value = System.getenv(key);

		// 2. If not found, check .env
		// Used for local development
		if (value == null || value.isBlank()) {
			value = DOTENV.get(key);
		}

		// 3. Fail fast if the value doesn't exist
		if (value == null || value.isBlank()) {
			throw new IllegalStateException("Required environment variable is missing: " + key);
		}

		return value;
	}
}
