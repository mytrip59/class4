import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProductTest {
    EventFiringWebDriver webDriver;
    PrestashopPage prestashopPage;
    final boolean DEBUGMODEENABLE = true;

    private String newProductNameEtalon;
    private String newProductAmountEtalon;
    private String newProductPriceEtalon;
    private WebElement foundNewProductOnFirstPageWebElement = null;


    @BeforeClass
    public void beforeClass (){
            webDriver = BaseScript.getConfiguredDriver();
    }

    @AfterClass
    public void afterClass (){
        BaseScript.quiteDriver(webDriver);
    }

    @Test (priority = 5)
    public void loginPage (){
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.open();

        loginPage.fillEmailInput();
        loginPage.fillPasswordInput();
        loginPage.clickSubmitButton();
        loginPage.waitLoadingLoginPage();
    }

    @Test (priority = 10)
    public void createNewProduct() {

        ProductPage productPage = new ProductPage(webDriver);
        productPage.clickProductMenu();
        productPage.clickAddNewProduct();
        newProductNameEtalon = new ProductData().generateRandomName(6);
        productPage.fillProductName(newProductNameEtalon);
        // productPage.clickAddAmount();
        productPage.clearProductAmount();
        newProductAmountEtalon = new ProductData().generateRandomAmountString(1,100);
        productPage.fillProductAmount(newProductAmountEtalon);
        // productPage.clickAddPrice();
        productPage.clearProductPrice();
        newProductPriceEtalon = new ProductData().generateRandomCost();
        productPage.fillProductPrice(newProductPriceEtalon);
        productPage.saveNewProduct();
        productPage.clickPopUpAfterSave();
        productPage.clickActiveProduct();

    }

    @Test (priority = 20)
    public void createOpenPrestashopClickAllProduct() {
        prestashopPage = new PrestashopPage(webDriver);
        prestashopPage.open();
        prestashopPage.clickAllProductsLink();
    }

    @Test (priority = 30, dependsOnMethods = "createOpenPrestashopClickAllProduct")
    public void assertNewProductOnFirstPage () {
        foundNewProductOnFirstPageWebElement = prestashopPage.foundNewProductOnFirstPage(newProductNameEtalon);

        if (foundNewProductOnFirstPageWebElement != null) {
            Assert.assertTrue(true, "Web element " + newProductNameEtalon + " is displayed in Catalog.");
        } else Assert.assertTrue(false, "Web element " + newProductNameEtalon + " is not displayed in Catalog.");
    }

    @Test (priority = 40)
    public void clickFoundProductPrestashop() {
        prestashopPage.clickNewPoduct(foundNewProductOnFirstPageWebElement);
    }

    @Test (priority = 50)
    public void assertAmountProductPrestashop() {
        String amountString = prestashopPage.getAmountPoduct();
        Assert.assertEquals(amountString, newProductAmountEtalon, "Amount of product is equal!");
    }

    @Test (priority = 60)
    public void assertPriceProductPrestashop() {
        String priceString = prestashopPage.getPricePoduct();
        Assert.assertEquals(priceString, newProductPriceEtalon, "Price of product is equal!");

    }
}
