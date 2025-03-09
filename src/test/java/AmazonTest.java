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

    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private CartPage cartPage;
    private List<String> addedProductTitles;
    private int addedProductsCount;

    @Test(priority = 1)
    @Story("Verify Amazon Home Page Loads")
    @Description("Ensures the Amazon homepage loads correctly.")
    @Severity(SeverityLevel.CRITICAL)
    public void testHomePageLoads() {
        LoggerHelper.info("Verifying homepage is loaded...");
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page did not load correctly.");
        LoggerHelper.info("Homepage loaded successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "testHomePageLoads")
    @Story("Search for Laptop")
    @Description("Searches for 'laptop' on Amazon.")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearchLaptop() {
        LoggerHelper.info("Searching for 'laptop'...");
        homePage.searchForProduct("laptop");
        LoggerHelper.info("Search completed successfully.");
    }

    @Test(priority = 3, dependsOnMethods = "testSearchLaptop")
    @Story("Add in-stock, non-discounted items to cart")
    @Description("Adds in-stock, non-discounted products to the cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddItemsToCart() {
        searchResultsPage = new SearchResultsPage(driver);
        LoggerHelper.info("Adding in-stock, non-discounted products to cart...");
        addedProductsCount = searchResultsPage.addInStockNonDiscountedProductsToCart();
        Assert.assertTrue(addedProductsCount > 0, "No products were added to the cart!");
        LoggerHelper.info(addedProductsCount + " products added to cart.");
        addedProductTitles = searchResultsPage.getAddedProductTitles();
    }

    @Test(priority = 4, dependsOnMethods = "testAddItemsToCart")
    @Story("Verify Cart Contents")
    @Description("Checks if the added products appear correctly in the cart.")
    @Severity(SeverityLevel.CRITICAL)
    public void testVerifyCart() {
        LoggerHelper.info("Navigating to cart...");
        homePage.goToCart();
        LoggerHelper.info("Cart page loaded successfully.");

        cartPage = new CartPage(driver);
        LoggerHelper.info("Verifying products in the cart...");
        Assert.assertTrue(cartPage.verifyCartItemCount(addedProductsCount), "Cart item count mismatch!");
        LoggerHelper.info("Cart products verified successfully.");
    }

    @Test(priority = 5, dependsOnMethods = "testVerifyCart")
    @Story("Complete Amazon Shopping Test")
    @Description("Final verification of cart and logs test completion.")
    @Severity(SeverityLevel.NORMAL)
    public void testCompleteAmazonShopping() {
        LoggerHelper.info("Amazon shopping test completed successfully.");
    }
}
