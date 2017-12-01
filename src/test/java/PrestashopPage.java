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


    private By priceNewPoductSelector = By.cssSelector("span[itemprop='price']");
    private By amountNewPoductSelector = By.cssSelector("#product-details > div.product-quantities > span");


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

    // if found successfully->return found web element
    public WebElement foundNewProductOnFirstPage(String createdProductEtalon){
        List<WebElement> listAllProducts = webDriver.findElements(anyProductName);
        for (WebElement curentWebElement: listAllProducts) {
            String curentWebElementString = curentWebElement.getText();
            if (curentWebElementString.equals(createdProductEtalon)){
                return curentWebElement;
            }
        }
        // if circle was finished without return -> webelement was not found
        return null;
    }

    public void clickNewPoduct(WebElement foundWebElement){
        foundWebElement.click();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(amountNewPoductSelector));
    }

    public String getPricePoduct(){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(priceNewPoductSelector));
        WebElement priceNewPoductWebElement = webDriver.findElement(priceNewPoductSelector);
        String priceNewPoductString =  priceNewPoductWebElement.getText();
        return priceNewPoductString;
    }

    public String parsStringDigitDotComma (String inString){
        StringBuilder sb = new StringBuilder();
        char[] chars = inString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit (chars[i]) | (chars[i]) == ',' | (chars[i]) == '.'){
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public String parsStringDigit (String inString){
        StringBuilder sb = new StringBuilder();
        char[] chars = inString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit (chars[i])){
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public String getAmountPoduct(){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(amountNewPoductSelector));
        WebElement priceNewPoductWebElement = webDriver.findElement(amountNewPoductSelector);
        String amountNewPoductString =  priceNewPoductWebElement.getText();
        return amountNewPoductString;
    }


}
