import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PrestashopPage {
    WebDriver webDriver;

    private By allProductsLinkSelector = By.cssSelector("a[class='all-product-link pull-xs-left pull-md-right h4']");

    private By anyProductName = By.cssSelector("div[class='product-description']>h1[itemprop='name']>a");
    private By anyProductPrice = By.cssSelector("div[class='product-description']>div[class='product-price-and-shipping']>span[class='price']");


    // selector in the bottom of the page for waiting of loading page
    private By footerSelector = By.cssSelector("#footer");


    public PrestashopPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open (){
        webDriver.get(Properties.getBaseUrl());
    }

    public void clickAllProductsLink(){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(allProductsLinkSelector));
        WebElement allProductsLinkWebElement = webDriver.findElement(allProductsLinkSelector);
        allProductsLinkWebElement.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(footerSelector));

    }

    public boolean foundNewProductOnFirstPage(String createdProductEtalon){
        List<WebElement> listAllProducts = webDriver.findElements(anyProductName);
        for (WebElement curentWebElement: listAllProducts) {
            String curentWebElementString = curentWebElement.getText();
            if (curentWebElementString.equals(createdProductEtalon)){
                return true;
            }
        }
        // if circle was finished without return -> webelement was not found
        return false;
    }
}
