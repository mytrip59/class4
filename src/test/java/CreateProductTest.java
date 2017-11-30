import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProductTest {
    private String newProduct = "Blouse2"; // Correct!!!!!!!!!!!!
    EventFiringWebDriver webDriver;

    @BeforeClass
    public void beforeClass (){
            webDriver = BaseScript.getConfiguredDriver();

/*            LoginPage loginPage = new LoginPage(webDriver);
            loginPage.open();

            loginPage.fillEmailInput();
            loginPage.fillPasswordInput();
            loginPage.clickSubmitButton();
            loginPage.waitLoadingLoginPage();*/
    }

    @AfterClass
    public void afterClass (){
        BaseScript.quiteDriver(webDriver);
    }

    @Test
    public void createNewProduct() {

/*        ProductPage productPage = new ProductPage(webDriver);
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
        productPage.clickPopUpAfterSave();*/


        PrestashopPage prestashopPage = new PrestashopPage(webDriver);
        prestashopPage.open();
        prestashopPage.clickAllProductsLink();
        prestashopPage.assertNewProductOnFirstPage(newProduct);

    }
}
