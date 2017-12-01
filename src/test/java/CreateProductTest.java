import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProductTest {
    private String newProduct;
    EventFiringWebDriver webDriver;
    PrestashopPage prestashopPage;

    @BeforeClass
    public void beforeClass (){
            webDriver = BaseScript.getConfiguredDriver();

            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.open();

            loginPage.fillEmailInput();
            loginPage.fillPasswordInput();
            loginPage.clickSubmitButton();
            loginPage.waitLoadingLoginPage();
    }

    @AfterClass
    public void afterClass (){
        BaseScript.quiteDriver(webDriver);
    }

    @Test (priority = 10)
    public void createNewProduct() {

        ProductPage productPage = new ProductPage(webDriver);
        productPage.clickProductMenu();
        productPage.clickAddNewProduct();
        newProduct = new ProductData().generateRandomName(6);
        productPage.fillProductName(newProduct);
        productPage.clickAddAmount();
        productPage.clearProductAmount();
        productPage.fillProductAmount(new ProductData().generateRandomAmountString(1,100));
        productPage.clickAddCost();
        productPage.clearProductCost();
        productPage.fillProductCost(new ProductData().generateRandomCost());
        productPage.saveNewProduct();
        productPage.clickPopUpAfterSave();

    }

    @Test (priority = 20)
    public void createOpenPrestashopClickAllProduct() {
        prestashopPage = new PrestashopPage(webDriver);
        prestashopPage.open();
        prestashopPage.clickAllProductsLink();
    }

    @Test (priority = 30, dependsOnMethods = "createOpenPrestashopClickAllProduct")
    public void assertNewProductOnFirstPage () {
        boolean foundNewProductOnFirstPageBoolean = prestashopPage.foundNewProductOnFirstPage(newProduct);

        if (foundNewProductOnFirstPageBoolean == true) {
            Assert.assertTrue(true, "Web element " + newProduct + " is displayed in Catalog.");
        } else Assert.assertTrue(false, "Web element " + newProduct + " is not displayed in Catalog.");
    }

}
