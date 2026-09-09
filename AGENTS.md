# AGENTS.md - Development Guidelines

## Project Context

This is a Java Maven project using TestNG, Selenium WebDriver, and ExtentReports for UI test automation following the Page Object Model (POM) pattern.

## Coding Conventions

### Java Code Style
- Use Java 17 records where appropriate (e.g., `FrameworkConfig` is already a record)
- Follow standard Java naming conventions (camelCase for variables/methods, PascalCase for classes)
- Keep methods focused and single-purpose
- Use meaningful parameter and variable names

### Page Object Pattern
- Each page should have its own class in `src/main/java/pages/`
- Page classes should encapsulate element locators and page actions
- Page methods should return the next page object or the same page object for method chaining
- **NO assertions in page classes** - assertions belong in test classes only

### Configuration

The framework supports configuration via **environment variables** and a **`.env` file** for local development:

### Environment Variables
- All config properties can be set as environment variables
- Used by GitHub Actions and CI/CD pipelines
- Example: `export BROWSER=chrome`, `export URL=https://example.com`

### `.env` File (Local Development)
- Create a `.env` file in the project root
- Format: `KEY=VALUE` per line
- Example `.env`:
  ```
  BROWSER=chrome
  URL=https://practicesoftwaretesting.com/auth/login
  HEADLESS=false
  EXPLICIT_WAIT=10
  PAGE_LOAD_TIMEOUT=30
  ```

### How It Works
1. **First**: System environment variables (`System.getenv()`) - preferred for CI/CD
2. **Second**: `.env` file values via `io.github.cdimascio.dotenv.Dotenv` - for local development
3. **Fail fast**: Throws `IllegalStateException` if required variable is missing

The `EnvConfig.get(key)` method handles the fallback chain automatically, so you can use `FrameworkConfig.load()` as before without code changes.

### Test Structure
- Base test class in `src/test/java/base/BaseTest.java` handles driver setup/teardown
- Tests use TestNG annotations (@Test, @BeforeMethod, @AfterMethod)
- Data providers in `src/test/java/dataproviders/` for data-driven tests
- Test listeners in `src/test/java/listeners/TestListener.java`

## Adding New Functionality

### New Page
1. Create a new class in `src/main/java/pages/`
2. Define WebElement annotations or use PageFactory
3. Add methods for page interactions
4. Return appropriate page object from methods

### New Test
1. Create test class in `src/test/java/tests/`
2. Extend base test class if needed
3. Use data providers for data-driven tests
4. Add assertions in the test method, not in page objects

### New Configuration
1. Add property to `src/main/resources/config.properties`
2. Add constant to `src/main/java/constants/FrameworkConstants.java`
3. Update `FrameworkConfig` record if needed

## Build and Test

### Running Tests
```bash
mvn test              # Run all tests
mvn test -Dtest=LoginTest  # Run specific test
mvn test -Dbrowser=chrome  # Run with Chrome
```

### Code Quality
- `mvn clean compile` - Compile check
- `mvn verify` - Full verification including reports
- Check `target/sure-reports/extentreports/` for test reports

## Git Workflow

1. Create a branch from `main`
2. Commit changes with descriptive messages
3. Run `mvn test` before pushing
4. Ensure new tests don't break existing ones
5. Submit Pull Request for review