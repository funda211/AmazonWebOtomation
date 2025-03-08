**Amazon Shopping Automation**

#### 📁 Project Overview

This project is an automated test suite developed to validate the shopping cart functionality on Amazon. The test ensures that in-stock and non-discounted laptops can be successfully added to the cart and verifies that the correct items are present.

#### 📁 Technologies Used

**Selenium :** Used for web automation.

**TestNG:** Framework for writing and executing test cases.

**WebDriverManager:** Manages browser drivers automatically.

**Page Object Model (POM):** Enhances test maintainability and reusability.

**Log4j:** Implements logging for better test tracking.

**Maven:** Handles project dependencies and builds.

**HtmlReport / AllureReport:** Used for test execution reporting (Bonus feature). After run test, write on terminal " allure serve target/allure-results" to show allure report on browser.

#### 📁 Test Workflow

1 Open Amazon and verify that the homepage loads correctly.

2 Enter the keyword "laptop" into the search bar.

3 Identify and add in-stock and non-discounted products from the first page of search results to the cart.

4 Navigate to the cart and validate that the selected products match the items in the cart.

5 Capture a screenshot in case of test failure


#### 📁 Pages
- **`BasePage.java`**: Contains common methods for all pages (e.g., element locators, waits, base actions).
- **`HomePage.java`**: Manages Amazon homepage interactions (e.g., search bar input, page load verification).
- **`SearchResultsPage.java`**: Filters "laptop" search results (in-stock, non-discounted products) and selects items.
- **`CartPage.java`**: Handles cart page operations (e.g., item validation, navigation).

#### 📁 Utils (Utilities)
- **`BaseTest.java`**: Manages test setup/teardown (e.g., WebDriver initialization).
- **`ConfigReader.java`**: Reads parameters from `config.properties` (e.g., URL, browser type).
- **`LoggerHelper.java`**: Integrates Log4j for step-by-step test logging.
- **`ScreenshotHelper.java`**: Captures screenshots on test failure.

#### 📁 Resources
- **`config.properties`**: Stores project configurations (e.g., browser, Amazon URL).
- **`log4j2.xml`**: Configures logging levels and formats for Log4j.
- **`testng.xml`**: Defines test groups and parameters for TestNG execution.

####  Test Scenario
- **`AmazonTest.java`**: Implements the core test flow (homepage → search → add to cart → validation).

### Key Features
- **Cross-Browser Testing**: Supports Chrome and Firefox via parameterization.
- **Logging**: Detailed step tracking using Log4j.
- **Failure Handling**: Automatic screenshots on test failures.
- **OOP Principles**: Modular design for scalability and maintainability.




This automation framework is designed to ensure robust validation of Amazon's shopping cart functionality while maintaining high test reliability and code reusability.

