# ARCHITECTURE.md - System Design

## High-Level Architecture

This framework follows a modular, layered architecture designed for maintainability and separation of concerns.

### Layered Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Test Layer                               │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │ Test Classes    │  │ Data Providers  │  │ Listeners   │ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
│           │                   │                   │       │
│           └───────┬─────────────┘                   │       │
│                     ▼                           │       │
└─────────────────────────────────────────────────────────────┘
                      │
                      │ TestNG @Test annotations
                      │
┌─────────────────────────────────────────────────────────────┐
│                    Behavior Layer                           │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │ Test Methods    │  │ Data-Driven     │  │ Parametrized  │ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
└──────────────────────┬──────────────────────────────────────┘
                        │
                        │ @BeforeMethod/@AfterMethod
                        │
┌─────────────────────────────────────────────────────────────┐
│                    Fixture Layer                            │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │ BaseTest        │  │ DriverFactory   │  │ DriverManager │ │
│  │ (setup/teardown)│  │ (WebDriver creation)││ (driver lifecycle)│ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
│           │                   │                   │       │
│           └───────┬─────────────┘                   │       │
│                     ▼                           │       │
└─────────────────────────────────────────────────────────────┘
                      │
                      │ WebDriver initialization, quit
                      │
┌─────────────────────────────────────────────────────────────┐
│                    Service Layer                              │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │ Page Objects    │  │ Utils/Helpers   │  │ Config      │ │
│  │ (actions/assertions)││ (Waits, Screens)││ (loading)   │ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
│           │                   │                   │       │
│           └───────┬─────────────┘                   │       │
│                     ▼                           │       │
└─────────────────────────────────────────────────────────────┘
                      │
                      │ Page actions, utility methods
                      │
┌─────────────────────────────────────────────────────────────┐
│                    Infrastructure Layer                       │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │ WebDriver       │  │ ExtentReports   │  │ TestNG      │ │
│  │ (Selenium)      │  │ (HTML Reports)  │  │ Framework   │ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
│           │                   │                   │       │
│           └───────┬─────────────┘                   │       │
│                     ▼                           │       │
└─────────────────────────────────────────────────────────────┘
                      │
                      │ Browser automation, report generation, test execution
                      │
┌─────────────────────────────────────────────────────────────┐
│                    External Dependencies                      │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │ Selenium 4      │  │ ExtentReports 5 │  │ TestNG 7    │ │
│  │ ChromeDriver    │  │ HTML Reports    │  │ Maven       │ │
│  │ GeckoDriver     │  │               │  │             │ │
│  └─────────────────┘  └─────────────────┘  └─────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### Key Design Decisions

1. **Record Types for Configuration**
   - `FrameworkConfig` uses Java 17 `record` type for immutable configuration
   - All fields are final, reducing boilerplate and ensuring thread-safety

2. **Separation of Concerns**
   - **Page Objects** - Only page interactions and element encapsulation
   - **No assertions in service classes** - Assertions belong in test methods only
   - **Config-driven** - All externalized via properties files
   - **Fixtures via TestNG** - setup/teardown in base class, not scattered

3. **Dependency Flow**
   - Tests → BaseTest → DriverManager → DriverFactory → WebDriver
   - Tests → Page Objects → Utils → Config
   - No reverse dependencies - clean separation

4. **Reporting Integration**
   - ExtentReports HTML reports generated after each test
   - Screenshots attached to failed steps automatically via ScreenshotUtil
   - Reports include test name, duration, pass/fail status, and error messages

5. **Data Management**
   - Test data in `src/test/java/data/` as plain Java classes
   - Data providers in `src/test/java/dataproviders/` for @DataProvider methods
   - Separation of test data from test logic

### Key Components

#### BaseTest.java
- Initialize WebDriver via DriverFactory
- Configure timeouts (implicit, explicit, page load)
- Setup ExtentTest manager
- Handle @BeforeMethod and @AfterMethod for screenshot capture on failure

#### DriverFactory.java
- Creates WebDriver instances based on config.properties browser setting
- Supports Chrome, Firefox, Edge browsers
- Headless mode support via config.properties

#### Page Object Pattern
- Each page has its own class with element locators
- Methods represent user actions (click, input text, verify elements)
- Return type is typically the next page or same page for chaining

#### ExtentReports Integration
- Thread-local ExtentTest instances
- Automatic screenshot capture on test failure
- Rich HTML output with collapsible sections, step details, and pass/fail status

#### Configuration System
- `EnvConfig` - environment variable-first approach using `System.getenv()`
- `.env` file - local development via `io.github.cdimascio.dotenv.Dotenv`
- `config.properties` - legacy runtime configuration (optional, for backwards compatibility)
- `FrameworkConstants.java` - property key constants
- `FrameworkConfig` record - loaded configuration at startup
- All values have sensible defaults; fail fast if required vars missing

**Environment Variable Priority:**
1. System environment variables (`System.getenv()`) - preferred for CI/CD/GitHub Actions
2. `.env` file values - for local development
3. Default values in code - last resort

The `EnvConfig.get(key)` method handles the fallback chain automatically.

#### Dependency Flow
- Tests → EnvConfig → FrameworkConfig → runtime values
- No reverse dependencies - clean separation
- Environment variables override `.env` file values

### Extensibility Points

1. **New Browsers**: Add browser option to DriverFactory and update config.properties
2. **New Reports**: Modify ExtentManager/ExtentTestManager to customize output
3. **New Utilities**: Add to `src/main/java/utils/` following existing patterns
4. **New Pages**: Create class in `src/main/java/pages/` following POM conventions
5. **New Data Providers**: Add methods in `src/test/java/dataproviders/`