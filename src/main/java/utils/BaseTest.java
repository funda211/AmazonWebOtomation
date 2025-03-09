package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeSuite
    public void setup() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");

            if (browser == null) {
                throw new IllegalStateException("Browser is not set in config.properties");
            }

            if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setPageLoadStrategy(PageLoadStrategy.EAGER); // DOM yüklendikten sonra devam et
                driver = new FirefoxDriver(options);
            } else if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new ChromeDriver(options);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            String baseUrl = ConfigReader.getProperty("baseUrl");
            if (baseUrl == null) {
                throw new IllegalStateException("baseUrl is not set in config.properties");
            }
            driver.get(baseUrl);
        }
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
            driver = null;  // Driver kapatıldıktan sonra tekrar kullanılmaması için null yap
        }
    }
}
