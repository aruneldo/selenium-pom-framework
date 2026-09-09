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
		if (value != null && !value.isBlank()) {
			return value;
		}

		// 2. If not found, check .env
		// Used for local development
		value = DOTENV.get(key);

		if (value != null && !value.isBlank()) {
			return value;
		}

		return null;
	}

	public static String required(String key) {

		String value = get(key);

		// Fail fast if the value doesn't exist
		if (value == null || value.isBlank()) {
			throw new IllegalStateException("Required environment variable is missing: " + key);
		}

		return value;
	}
}
