package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "div[data-name='Active Items'] .sc-list-item")
    private List<WebElement> cartItems;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyCartItemCount(int expectedCount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-name='Active Items'] .sc-list-item"))
        );

        int actualCount = cartItems.size();
        LoggerHelper.info("Expected cart items: " + expectedCount);
        LoggerHelper.info("Actual cart items: " + actualCount);

        return actualCount == expectedCount;
    }
}