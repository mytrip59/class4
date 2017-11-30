import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    private By productAmountLinkSelector = By.cssSelector("#tab_step3 > a");
    private By productAmountInputSelector = By.cssSelector("#form_step3_qty_0");

    private By productCostLinkSelector = By.cssSelector("#tab_step2 > a");
    private By productCostInputSelector = By.cssSelector("#form_step2_price");

    private By productSaveButtonSelector = By.cssSelector("div[class='btn-group hide dropdown pull-right']");

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

    public void clickAddProductName() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addNewProductLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(addNewProductLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productNameInputSelector));
    }

    public void clickAddAmount() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productAmountLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(productAmountLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productAmountInputSelector));
    }

    public void clickAddCost() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productCostLinkSelector));
        WebElement webElementaddCategoryLink = webDriver.findElement(productCostLinkSelector);
        webElementaddCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productCostInputSelector));
    }

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

    public void clearProductCost() {
        WebElement webElementEmailInput = webDriver.findElement(productCostInputSelector);
        webElementEmailInput.clear();
    }

    public void fillProductCost(String productCost) {
        WebElement webElementEmailInput = webDriver.findElement(productCostInputSelector);
        webElementEmailInput.sendKeys(productCost);
    }


    // Handle Pop up window
    public void saveNewProduct() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productSaveButtonSelector));
        WebElement webElementSaveNewCategoryLink = webDriver.findElement(productSaveButtonSelector);
        webElementSaveNewCategoryLink.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(productSaveButtonSelector));
    }

    // not use
    public void waitLoadingProductPage() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(buttonProductSelector));
    }





/*    public boolean checkCreateNewCategoryAlert(String newCategoryName) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 3);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(alertSuccessfulNewCategorySelector));
            System.out.println("**** Alert! New category was created successfully! ****");
            return true;
            // save number of categories with new name
        } catch (Exception e) {
            System.out.println("**** Successful alert, that new category was created, was not displayed! ****");
            BaseScript.quiteDriver(webDriver);
            System.exit(1);
            return false;

        }
    }*/
}
