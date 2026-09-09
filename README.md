[![Test Automation Pipeline](https://github.com/aruneldo/selenium-pom-framework/actions/workflows/test-pipeline.yml/badge.svg)](https://github.com/aruneldo/selenium-pom-framework/actions/workflows/test-pipeline.yml)
[![Selenium Extent Reports](https://img.shields.io/badge/Selenium%20Extent%20Reports-Live-2da44e?logo=github&logoColor=white)](https://aruneldo.github.io/selenium-pom-framework/)

# Selenium POM Framework

A modern Page Object Model (POM) framework for test automation using Selenium WebDriver and TestNG.

## Overview

This framework provides a structured approach to UI test automation with:
- **Page Object Pattern** - Encapsulates page elements and actions
- **TestNG** - Testing framework with flexible test configuration
- **ExtentReports** - Rich HTML test reporting
- **Selenium 4** - Modern WebDriver API with relative locators
- **Config-Driven** - Externalized configuration via properties files

## Project Structure

```
selenium-pom-framework/
├── pom.xml                 # Maven build configuration
├── src/main/java/
│   ├── config/             # Framework configuration (records, constants)
│   ├── constants/          # Test constants and framework settings
│   ├── driver/             # WebDriver factory and manager
│   ├── pages/              # Page Object classes
│   ├── reporting/          # ExtentReports integration
│   ├── utils/              # Utility classes (waits, logger, screenshot)
│   └── resources/          # Configuration properties and log4j2
├── src/test/java/
│   ├── base/               # Base test class with setup/teardown
│   ├── data/               # Test data and data providers
│   ├── listeners/          # TestNG listeners
│   └── tests/              # Test classes
├── src/test/resources/     # Test resources (testng.xml)
└── reports/                # Generated test reports (ExtentHTML, screenshots)
```

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Chrome/Firefox browser for test execution

## Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd selenium-pom-framework
   ```

2. **Install dependencies**
   ```bash
   mvn install
   ```

3. **Run tests**
   ```bash
   mvn test
   ```

4. **View reports**
   - ExtentReports HTML report: `reports/index.html`
   - Screenshots: `reports/screenshots/`

## Configuration

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
2. **Second**: `.env` file values - for local development
3. **Fail fast**: Throws `IllegalStateException` if required variable is missing

The `EnvConfig.get(key)` method handles the fallback chain automatically, so you can use `config.properties` to store common configurations.

## Reporting

Tests generate ExtentReports HTML reports with:
- Test step details with screenshots
- Execution time tracking
- Pass/fail status with error messages
- Environment information

Reports are located in `reports/index.html` after test execution.