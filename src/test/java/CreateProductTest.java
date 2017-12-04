import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CreateProductTest {
    private WebDriver driver;
    private EventFiringWebDriver webDriver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private ProductData productData;
    private PrestashopPage prestashopPage;
    private final boolean DEBUGMODEENABLE = true;

    private String newProductNameEtalon;
    private String newProductAmountEtalon;
    private String newProductPriceEtalon;
    private WebElement foundNewProductOnFirstPageWebElement = null;

    @DataProvider(name = "getLoginPassword")
    public static Object[][] getLoginPaswordMethod() {
        String  dataProviderObject[][] = {{"webinar.test@gmail.com","Xcg7299bnSmMuRLp9ITw"}};
        return dataProviderObject;
    }

    @BeforeClass
    @Parameters ("selenium.browser")
    public void beforeClass (@Optional("chrome") String browser){
        driver = BaseScript.getDriver(browser);
        webDriver = new EventFiringWebDriver(driver);
        webDriver.register(new EventHandler());


        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass (){
        BaseScript.quiteDriver(webDriver);
    }

    @Test (priority = 5, dataProvider = "getLoginPassword")
    public void loginPage (String login, String password){
        loginPage = new LoginPage(webDriver);
        loginPage.open();

        loginPage.fillLoginInput(login);
        loginPage.fillPasswordInput(password);
        loginPage.clickSubmitButton();
        loginPage.waitLoadingLoginPage();

    }

    @Test (priority = 10)
    public void createNewProduct() {
        productData = new ProductData();
        productPage = new ProductPage(webDriver);
        productPage.clickProductMenu();
        productPage.clickAddNewProduct();
        newProductNameEtalon = productData.generateRandomName(6);
        productPage.fillProductName(newProductNameEtalon);
        productPage.clearProductAmount();
        newProductAmountEtalon = productData.generateRandomAmountString(1,100);
        productPage.fillProductAmount(newProductAmountEtalon);
        productPage.clickAddProductPrice();
        //productPage.clearBackSpaceProductPrice();
        newProductPriceEtalon = productData.generateRandomCost();
        productPage.clearBackSpaceAndFillProductPrice(newProductPriceEtalon);
        //productPage.fillProductPrice(newProductPriceEtalon);
        productPage.clickActiveProduct();
        productPage.clickPopUpAfterSaveAndActivate();
        productPage.saveNewProduct();
        productPage.clickPopUpAfterSaveAndActivate();
        // wait adding new product 5 sec
        threadSleep(10000);
    }
    @Test (priority = 20)
    public void createOpenPrestashopClickAllProduct() {
        prestashopPage = new PrestashopPage(webDriver);
        prestashopPage.open();
        prestashopPage.clickAllProductsLink();
    }

    @Test (priority = 30)
    public void assertNewProductOnFirstPage () {
        foundNewProductOnFirstPageWebElement = prestashopPage.foundNewProductInCatalog(newProductNameEtalon);
        String foundNewProductOnFirstPageString = foundNewProductOnFirstPageWebElement.getText().toLowerCase();
             Assert.assertNotNull(foundNewProductOnFirstPageString, "Web element " + newProductNameEtalon + " is not displayed in Catalog.");
    }
    @Test (priority = 40)
    public void clickFoundProductPrestashop() {
        prestashopPage.openCreatedPoductPage(foundNewProductOnFirstPageWebElement);
    }

    @Test (priority = 50)
    public void assertAmountProductPrestashop() {
        String amountString = prestashopPage.getAmountPoduct();
        String amountStringAfterClean = prestashopPage.parsStringDigit(amountString);
        Assert.assertEquals(amountStringAfterClean, newProductAmountEtalon, "Amount of product is not equal to etalon!");
    }

    @Test (priority = 60)
    public void assertPriceProductPrestashop() {
        String priceString = prestashopPage.getPricePoduct();
        String priceStringAfterClean = prestashopPage.parsStringOneSymbolAfterComma(priceString);
        Assert.assertEquals(priceStringAfterClean, newProductPriceEtalon, "Price of product is not equal to etalon!");

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
