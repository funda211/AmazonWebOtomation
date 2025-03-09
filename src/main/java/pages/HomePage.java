package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerHelper;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(css = "a[onclick*='window.location.reload()']")
    private WebElement tryDifferentImageLink;

    @FindBy(css = "i.a-icon.a-logo")
    private WebElement amazonLogo;

    @FindBy(id = "nav-logo-sprites")
    private WebElement amazonLogo2;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(id = "nav-cart-count")
    private WebElement cartIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public boolean isHomePageLoaded() {
        LoggerHelper.info("Checking if the homepage is loaded...");


        try {
            waitForElementToBeVisible(amazonLogo, 3); // Kısa timeout
            if(amazonLogo.isDisplayed()) {
                LoggerHelper.info("CAPTCHA logo found, clicking try different image...");
                clickTryDifferentImage();
                return true;
            }
        } catch (TimeoutException e) {
            System.out.println("captcha page not loaded");
        }


        try {
            waitForElementToBeVisible(amazonLogo2, 5);
            boolean isLogoDisplayed = amazonLogo2.isDisplayed();
            LoggerHelper.info("Main Amazon logo verification: " + isLogoDisplayed);
            return isLogoDisplayed;
        } catch (TimeoutException e) {
            LoggerHelper.error("Homepage not loaded!");
            return false;
        }
    }


    public void searchForProduct(String product) {
        LoggerHelper.info("Searching for product: " + product);
        typeText(searchBox, product);
        clickWithJavaScript(searchButton);
    }



    public void goToCart() {
        LoggerHelper.info("Navigating to the cart...");
        scrollToElement(cartIcon); // Sepet ikonunu görünür hale getir
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        clickWithJavaScript(cartIcon); // JavaScript ile tıkla
    }


    public void clickTryDifferentImage() {
        LoggerHelper.info("Clicking on 'Try different image' link...");
        click(tryDifferentImageLink);
    }


}