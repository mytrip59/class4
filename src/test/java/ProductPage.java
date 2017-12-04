import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver webDriver;

    // Menu panel
    private By catalogLinkSelector = By.cssSelector("ul[class=menu] a[href*=catalog]");
    private By productLinkSelector = By.cssSelector("#subtab-AdminProducts > a");

    private By addNewProductLinkSelector = By.cssSelector("#page-header-desc-configuration-add > span");

    // 1 selector in the bottom of the page for waiting of loading page
    private By buttonProductSelector = By.cssSelector("#product_catalog_list select[name='paginator_select_page_limit']");


    // new product subpage, wait anable to write
    private By productNameInputSelector = By.cssSelector("#form_step1_name_1");

    private By productAmountInputSelector = By.cssSelector("#form_step1_qty_0_shortcut");

    private By productPriceLinkSelector = By.cssSelector("#tab_step2 > a");
    private By productPriceInputSelector = By.cssSelector("#form_step2_price");
    //private By productPriceInputSelector = By.cssSelector("#form_step1_price_shortcut");

    // SAVE button for created product: take one from two variants through the exception
    By saveNewProductSelector1 = By.cssSelector("button[class='btn btn-primary js-btn-save']>span");
    By saveNewProductSelector2 = By.cssSelector("#submit");

    private By webElementPopUpCloseSelector = By.cssSelector("div > div.growl-close");

    private By activeProductSelector = By.cssSelector("div[class*='switch-input']");


    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickProductMenu() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(catalogLinkSelector));
        Actions mouseAction = new Actions(webDriver);
        WebElement webElementCatalogLink = webDriver.findElement(catalogLinkSelector);
        mouseAction.moveToElement(webElementCatalogLink).build().perform();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productLinkSelector));
        WebElement webElementCategoryLink = webDriver.findElement(productLinkSelector);
        mouseAction.moveToElement(webElementCategoryLink).click().build().perform();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(addNewProductLinkSelector));
    }

    public void clickAddNewProduct() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addNewProductLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(addNewProductLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productNameInputSelector));
    }

    public void clickAddProductPrice() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productPriceLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(productPriceLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productPriceInputSelector));
    }

    public void fillProductName(String productName) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productNameInputSelector));
        WebElement productNameInputWebElement = webDriver.findElement(productNameInputSelector);
        productNameInputWebElement.sendKeys(productName);
    }

    public void clearProductAmount() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productAmountInputSelector));
        WebElement productAmountInputWebElement = webDriver.findElement(productAmountInputSelector);
        productAmountInputWebElement.clear();
    }

    public void fillProductAmount(String productAmount) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productAmountInputSelector));
        WebElement productAmountInputWebElement = webDriver.findElement(productAmountInputSelector);
        productAmountInputWebElement.sendKeys(productAmount);
    }
/*
    public void clearProductPrice() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productPriceInputSelector));
        WebElement productPriceInputWebElement = webDriver.findElement(productPriceInputSelector);
        productPriceInputWebElement.clear();
    }*/

/*    public void clearBackSpaceProductPrice() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productPriceInputSelector));
        WebElement productPriceInputWebElement = webDriver.findElement(productPriceInputSelector);
        String inputProductPriceString = productPriceInputWebElement.getText();
        for (int i = 0; i < inputProductPriceString.length(); i++) {
            productPriceInputWebElement.sendKeys(Keys.BACK_SPACE);
        }

    }*/

    public void fillProductPrice(String productPrice) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productPriceInputSelector));
        WebElement productPriceInputWebElement = webDriver.findElement(productPriceInputSelector);
        productPriceInputWebElement.sendKeys(productPrice);
    }

    // input productPrice, clear and input productPrice second time
    public void clearBackSpaceAndFillProductPrice(String productPrice) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productPriceInputSelector));
        WebElement productPriceInputWebElement = webDriver.findElement(productPriceInputSelector);
        productPriceInputWebElement.sendKeys(productPrice);

        String inputProductPriceString = productPriceInputWebElement.getText();
        for (int i = 0; i < inputProductPriceString.length(); i++) {
            productPriceInputWebElement.sendKeys(Keys.BACK_SPACE);
        }
        // clear attributes
        //setAttributeValue(productPriceInputWebElement, "value", "");
        productPriceInputWebElement.clear();
        ;
        // second time
        productPriceInputWebElement.sendKeys(productPrice);

    }


    public void saveNewProduct() {
        WebElement saveNewProductWebElement;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(saveNewProductSelector1));
            saveNewProductWebElement = webDriver.findElement(saveNewProductSelector1);
            System.out.println("Find saveNewProductSelector1!");
            } catch (org.openqa.selenium.TimeoutException e){
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(saveNewProductSelector2));
            saveNewProductWebElement = webDriver.findElement(saveNewProductSelector2);
            System.out.println("Find saveNewProductSelector2!");
            }
        saveNewProductWebElement.click();
    }

    // not use
    public void waitLoadingProductPage() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonProductSelector));
    }

    // Handle Pop up window
    public void clickPopUpAfterSaveAndActivate() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(webElementPopUpCloseSelector));
        WebElement webElementPopUpCloseLink = webDriver.findElement(webElementPopUpCloseSelector);
        webElementPopUpCloseLink.click();
    }

    public void clickActiveProduct() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(activeProductSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(activeProductSelector);
        webElementaddCategoryLink.click();
    }

    public void setAttributeValue(WebElement webElement, String attributeName, String attributeValue){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        String scriptSetAttrValue = "arguments[0].setAttribute(arguments[1],arguments[2])";
        js.executeScript(scriptSetAttrValue, webElement, attributeName, attributeValue);

    }
}
