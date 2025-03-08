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


    public boolean verifyCartItemCount(int expectedCartItemCount) {
        int actualCartItemCount = cartItems.size(); // cartItems, sepetteki ürünleri temsil eden WebElement listesi
        LoggerHelper.info("Expected cart count: " + expectedCartItemCount);
        LoggerHelper.info("Actual cart count: " + actualCartItemCount);

        if (expectedCartItemCount != actualCartItemCount) {
            LoggerHelper.error("Cart item count mismatch!");
            return false;
        }
        return true;
    }

}