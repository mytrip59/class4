import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    // private By productAmountLinkSelector = By.cssSelector("#tab_step3 > a");
    // private By productAmountInputSelector = By.cssSelector("#form_step3_qty_0");
    private By productAmountInputSelector = By.cssSelector("#form_step1_qty_0_shortcut");

/*    private By productPriceLinkSelector = By.cssSelector("#tab_step2 > a");
    private By productPriceInputSelector = By.cssSelector("#form_step2_price");*/
    private By productPriceInputSelector = By.cssSelector("#form_step1_price_shortcut");


    private By productSaveButtonSelector = By.cssSelector("div[class='btn-group hide dropdown pull-right'] button[type='submit']");

    private By webElementPopUpCloseSelector = By.cssSelector("div > div.growl-close");

    private By activeProductSelector = By.cssSelector("div[class*='switch-input']");

    // selector in the bottom of the page for waiting of loading page
    private By buttonSaveNewCategorySelector = By.cssSelector("#add-categories > h2");


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

/*    public void clickAddAmount() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productAmountLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(productAmountLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productAmountInputSelector));
    }*/

/*    public void clickAddPrice() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productPriceLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(productPriceLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productPriceInputSelector));
    }*/

    public void fillProductName(String productName) {
        WebElement webElementEmailInput = webDriver.findElement(productNameInputSelector);
        webElementEmailInput.sendKeys(productName);
    }

    public void clearProductAmount() {
        WebElement webElementEmailInput = webDriver.findElement(productAmountInputSelector);
        webElementEmailInput.clear();
    }

    public void fillProductAmount(String productAmount) {
        WebElement webElementEmailInput = webDriver.findElement(productAmountInputSelector);
        webElementEmailInput.sendKeys(productAmount);
    }

    public void clearProductPrice() {
        WebElement webElementEmailInput = webDriver.findElement(productPriceInputSelector);
        webElementEmailInput.clear();
    }

    public void fillProductPrice(String productPrice) {
        WebElement webElementEmailInput = webDriver.findElement(productPriceInputSelector);
        webElementEmailInput.sendKeys(productPrice);
    }


    // Handle Pop up window
    public void saveNewProduct() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        By temp = By.cssSelector("#submit");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(temp));
        WebElement webElementSaveNewCategoryLink = webDriver.findElement(temp);
        webElementSaveNewCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(temp));
    }

    // not use
    public void waitLoadingProductPage() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonProductSelector));
    }

    public void clickPopUpAfterSave() {
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

}
