package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".s-result-item")
    private List<WebElement> searchResults;

    @FindBy(css = "button[id*='a-autoid'][id*='announce']")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".s-result-item .a-size-base.a-color-secondary")
    private List<WebElement> productDescriptions;

    @FindBy(css = ".s-result-item .a-size-base.a-color-price")
    private List<WebElement> stockStatuses;

    @FindBy(css = ".s-result-item .a-size-medium.a-color-base.a-text-normal")
    private List<WebElement> productTitles;

    private List<String> addedProductTitles = new ArrayList<>();

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int addInStockNonDiscountedProductsToCart() {
        int addedProductsCount = 0;

        for (int i = 0; i < searchResults.size(); i++) {
            WebElement product = searchResults.get(i);


            boolean isInStock = false;
            if (i < stockStatuses.size()) {
                String stockStatus = stockStatuses.get(i).getText();
                isInStock = stockStatus.contains("Only") || stockStatus.contains("In Stock");
            }


            boolean isDiscounted = false;
            if (i < productDescriptions.size()) {
                String descriptionText = productDescriptions.get(i).getText();
                isDiscounted = descriptionText.contains("Typical:") || descriptionText.contains("List:");
            }


            if (isInStock && !isDiscounted) {
                try {
                    WebElement addToCartButton = addToCartButtons.get(i);
                    scrollToElement(addToCartButton); // Elementi görünür hale getir

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
                    clickWithJavaScript(addToCartButton); // JavaScript ile tıkla

                    addedProductsCount++;
                    addedProductTitles.add(productTitles.get(i).getText());
                    LoggerHelper.info("Product added to cart: " + productTitles.get(i).getText());
                } catch (Exception e) {
                    LoggerHelper.error("Failed to add product to cart: " + e.getMessage());
                }
            }
        }

        return addedProductsCount;
    }

    public List<String> getAddedProductTitles() {
        return addedProductTitles;
    }
}