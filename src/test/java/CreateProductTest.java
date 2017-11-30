import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateProductTest {
    private final String NEWCATEGORY = "Handmade";
    EventFiringWebDriver webDriver;

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

    @Test
    public void createNewProduct() {
        ProductPage productPage = new ProductPage(webDriver);
        productPage.clickProductMenu();
        productPage.clickAddProductName();
        productPage.fillProductName(new ProductData().generateRandomName(6));
        productPage.clickAddAmount();
        productPage.clearProductAmount();
        productPage.fillProductAmount(new ProductData().generateRandomAmountString(1,100));
        productPage.clickAddCost();
        productPage.clearProductCost();
        productPage.fillProductCost(new ProductData().generateRandomCost());

    }

    // TODO implement logic to check product visibility on website
}
