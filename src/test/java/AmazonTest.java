package test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.BaseTest;
import utils.LoggerHelper;
import utils.ScreenshotHelper;

import java.util.List;

@Epic("Amazon Test Automation")
@Feature("Amazon Shopping")
public class AmazonTest extends BaseTest {

    @Test
    @Story("User searches for 'laptop' and adds non-discounted items to the cart")
    @Description("Verify Amazon home page loads, search for 'laptop', add in-stock, non-discounted products to cart, and verify them in the cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void testAmazonShopping() {
        try {
            LoggerHelper.info("Starting Amazon shopping test...");

            // 1. Verify that the homepage loads correctly.
            HomePage homePage = new HomePage(driver);
            LoggerHelper.info("Verifying homepage is loaded...");
            Assert.assertTrue(homePage.isHomePageLoaded(), "Home page did not load correctly.");
            LoggerHelper.info("Homepage loaded successfully.");


            // 2. Search for the word "laptop".
            LoggerHelper.info("Searching for 'laptop'...");
            homePage.searchForProduct("laptop");
            LoggerHelper.info("Search completed successfully.");

            // 3. Add in-stock, non-discounted products from the first page of search results to the cart.
            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
            LoggerHelper.info("Adding in-stock, non-discounted products to cart...");
            int addedProductsCount = searchResultsPage.addInStockNonDiscountedProductsToCart();
            Assert.assertTrue(addedProductsCount > 0, "No products were added to the cart!");
            LoggerHelper.info(addedProductsCount + " products added to cart.");

            List<String> addedProductTitles = searchResultsPage.getAddedProductTitles();

            // 4. Go to the cart and verify that the added products are correct.
            LoggerHelper.info("Navigating to cart...");
            homePage.goToCart();
            LoggerHelper.info("Cart page loaded successfully.");

            CartPage cartPage = new CartPage(driver);
            LoggerHelper.info("Verifying products in the cart...");
            int expectedCount = searchResultsPage.getAddedProductTitles().size();
            Assert.assertTrue(cartPage.verifyCartItemCount(expectedCount), "Cart item count mismatch!");

            LoggerHelper.info("Cart products verified successfully.");

            LoggerHelper.info("Amazon shopping test completed successfully.");
        } catch (AssertionError e) {
            // Capture screenshot on failure
            String screenshotPath = ScreenshotHelper.takeScreenshot(driver, "testAmazonShopping_Failure");
            LoggerHelper.error("Test failed! Screenshot saved: " + screenshotPath);
            throw e; // Rethrow the exception to mark the test as failed
        }
    }
}