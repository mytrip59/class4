import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PrestashopPage {
    WebDriver webDriver;

    private By allProductsLinkSelector = By.cssSelector("a[class='all-product-link pull-xs-left pull-md-right h4']");

    private By anyProductNameSelector = By.cssSelector("div[class='product-description']>h1[itemprop='name']>a");
    private By anyProductPriceSelector = By.cssSelector("div[class='product-description']>div[class='product-price-and-shipping']>span[class='price']");


    private By priceNewPoductSelector = By.cssSelector("span[itemprop='price']");
    private By amountNewPoductSelector = By.cssSelector("#product-details > div.product-quantities > span");


    // selector in the bottom of the page for waiting of loading page
    private By footerSelector = By.cssSelector("footer#footer");


    public PrestashopPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open() {
        webDriver.get(Properties.getBaseUrl());
    }

    public void clickAllProductsLink() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(allProductsLinkSelector));
        WebElement allProductsLinkWebElement = webDriver.findElement(allProductsLinkSelector);
        allProductsLinkWebElement.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(footerSelector));

    }

    // if found successfully->return found web element
    public WebElement foundNewProductInCatalog(String createdProductEtalon) {
        while (true) {
            // threadSleep(5000);
            List<WebElement> listAllProducts;
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(footerSelector));
            listAllProducts = webDriver.findElements(anyProductNameSelector);
            for (WebElement curentWebElement : listAllProducts) {
                String curentWebElementString = curentWebElement.getText();
                if (curentWebElementString.equalsIgnoreCase(createdProductEtalon)) {
                    return curentWebElement;
                }
            }
            // if new product was not found on first page, click next catalog page and search again,
            // till method clicNextProductListLink() return exception on next link.
            try {
                clicNextProductListLink();
                listAllProducts = null;
                webDriverWait = null;
                //System.out.println("clicNextProductListLink()");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                //System.out.println("clicNextProductListLink() is not click;");
                return null;
            }
        }
    }

    public void openCreatedPoductPage(WebElement foundWebElement) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(footerSelector));
        foundWebElement.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(amountNewPoductSelector));
    }

    public String getPricePoduct() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(priceNewPoductSelector));
        WebElement priceNewPoductWebElement = webDriver.findElement(priceNewPoductSelector);
        String priceNewPoductString = priceNewPoductWebElement.getText();
        return priceNewPoductString;
    }

    public String parsStringDigitDotComma(String inString) {
        StringBuilder sb = new StringBuilder();
        char[] chars = inString.toCharArray();
        for (int j = 0, i = 0; i < chars.length; i++) {
            if ((Character.isDigit(chars[i]) | (chars[i]) == ',')) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public String parsStringDigit(String inString) {
        StringBuilder sb = new StringBuilder();
        char[] chars = inString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    // Use only for float and desimal format. After ',' add one symbot and return
    public String parsStringOneSymbolAfterComma(String inString) {
        StringBuilder sb = new StringBuilder();
        char[] chars = inString.toCharArray();
        for (int j = 0, i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (chars[i] == ',') {
                sb.append(chars[i + 1]);
                break;
            }
        }
        return sb.toString();
    }

    public String getAmountPoduct() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(amountNewPoductSelector));
        WebElement priceNewPoductWebElement = webDriver.findElement(amountNewPoductSelector);
        String amountNewPoductString = priceNewPoductWebElement.getText();
        return amountNewPoductString;
    }

    public void clicNextProductListLink() {

        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
//************************** start ***********************

                By nextProductListSelector = By.cssSelector("a[rel='next']");
                WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);
                webDriverWait.until(ExpectedConditions.elementToBeClickable(nextProductListSelector));
                WebElement nextProductListWebElement = webDriver.findElement(nextProductListSelector);
                nextProductListWebElement.click();
//************************** end ***********************

            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }

        }
    }

    // wait adding new product 5 sec
    public void threadSleep(int waitTimeMillisec){
        try {
            Thread.sleep(waitTimeMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
