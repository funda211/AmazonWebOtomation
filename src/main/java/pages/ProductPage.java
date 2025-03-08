package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LoggerHelper;

public class ProductPage extends BasePage {

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        LoggerHelper.info("Adding product to cart...");
        waitForElementToBeClickable(addToCartButton, 10);
        addToCartButton.click();
    }
}