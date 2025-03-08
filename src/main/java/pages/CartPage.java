package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".sc-list-item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
    }


    public boolean verifyCartProducts(List<String> expectedProductTitles) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));


            List<String> actualProductTitles = new ArrayList<>();
            for (WebElement item : cartItems) {
                WebElement titleElement = item.findElement(By.cssSelector(".sc-product-title"));
                String title = titleElement.getText();
                actualProductTitles.add(title);
                LoggerHelper.info("Product in cart: " + title);
            }


            boolean result = actualProductTitles.containsAll(expectedProductTitles);
            if (!result) {
                LoggerHelper.error("Some products are missing in the cart.");
                LoggerHelper.error("Expected: " + expectedProductTitles);
                LoggerHelper.error("Actual: " + actualProductTitles);
            }

            return result;
        } catch (Exception e) {
            LoggerHelper.error("An error occurred while verifying cart products: " + e.getMessage());
            return false;
        }
    }
}