package Tests;

import Base.BaseTest;
import Base.BaseTesttt;
import Pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PlaceOrderTest extends BaseTest {
    LoginPage loginPage;
    PlaceOrderPage palceOrder;
    ProductPage productPage;
    WebDriverWait wait;
    HeaderPage header;
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

//@BeforeMethod
public void preConditions(){
    loginPage = new LoginPage(driver);
    palceOrder = new PlaceOrderPage(driver);
    header = new HeaderPage(driver);
    homePage = new HomePage(driver);
    productPage = new ProductPage(driver);
    header.clickSignInLink();
    loginPage.successfulLogin("Mimo","Mimo111181");
}

    @Test (priority = 1 ,description = "Positive Scenario")
    public void SuccessPlaceOfOrder() {
        loginPage = new LoginPage(driver);
        palceOrder = new PlaceOrderPage(driver);
        header = new HeaderPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        header.clickSignInLink();
        loginPage.successfulLogin("Mimo","Mimo111181");
        homePage.clickNexus6();
        productPage.addToCart();
        homePage.goToCart();
        palceOrder.EnterPurchaseInfo("Osama", "Egypt", "Cairo", "123456789", "3", "2025");
        palceOrder.verifyPurchaseConfirmation();
        palceOrder.clickOK();
    }
   @Test (priority = 2 , description ="Order With InComplete Details")
    public void OrderWithInCompleteDetails(){
       loginPage = new LoginPage(driver);
       palceOrder = new PlaceOrderPage(driver);
       header = new HeaderPage(driver);
       homePage = new HomePage(driver);
       productPage = new ProductPage(driver);
       header.clickSignInLink();
       loginPage.successfulLogin("Mimo","Mimo111181");
        homePage.clickNexus6();
        productPage.addToCart();
        homePage.goToCart();
        palceOrder.EnterPurchaseInfo("","","","","","");
       // wait.until(ExpectedConditions.alertIsPresent());
        Alert alert=driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"Please fill out Name and Creditcard.");
        alert.accept();
    }

 @Test (priority = 3 ,description ="palce Order Empty Place OrderTrial")
    public void palceOrderEmptyPlaceOrderTrial(){
     loginPage = new LoginPage(driver);
     palceOrder = new PlaceOrderPage(driver);
     header = new HeaderPage(driver);
     homePage = new HomePage(driver);
     productPage = new ProductPage(driver);
     header.clickSignInLink();
     loginPage.successfulLogin("Mimo","Mimo111181");
     homePage.goToCart();
     Assert.assertFalse(driver.findElement(palceOrder.PlaceOrderButton).isEnabled(), "Place Order button should be disabled when palceOrder is empty, but it was clickable");
    }

    @Test (priority = 4 ,description = "Verify CreditCard Number Length")
    public void VerifyCreditCardNoLength() throws InterruptedException {
        loginPage = new LoginPage(driver);
        palceOrder = new PlaceOrderPage(driver);
        header = new HeaderPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        header.clickSignInLink();
        loginPage.successfulLogin("Mimo","Mimo111181");
        homePage.goToCart();
        palceOrder.EnterPurchaseInfo("Osama","Egypt","Cairo","1","3","2025");
        Assert.assertFalse(driver.findElement(palceOrder.ConfirmationMessage).isDisplayed(),"Purchasing Must be failed and message appears that Card No. is Invaild");
    }

    @Test (priority = 5 , description ="Verify Invaild Name Is Not Accepted")
    public void VerifyInvaildNameIsNotAccepted(){
        loginPage = new LoginPage(driver);
        palceOrder = new PlaceOrderPage(driver);
        header = new HeaderPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        header.clickSignInLink();
        loginPage.successfulLogin("Mimo","Mimo111181");
        homePage.goToCart();
        palceOrder.EnterPurchaseInfo("$","Egypt","Cairo","123456789","3","2025");
        Assert.assertFalse(driver.findElement(palceOrder.ConfirmationMessage).isDisplayed(),"Invaild Name,Please Enter a Valid Name");
    }

    @Test (priority = 6 , description = "Verify Order Details")
    public void VerifyOrderDetails(){
        loginPage = new LoginPage(driver);
        palceOrder = new PlaceOrderPage(driver);
        header = new HeaderPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        header.clickSignInLink();
        loginPage.successfulLogin("Mimo","Mimo111181");
        homePage.clickNexus6();
        productPage.addToCart();
        homePage.goToCart();
        palceOrder.EnterPurchaseInfo("Osama","Egypt","Cairo","123456789","4","2025");
        palceOrder.verifyPurchaseInformation();
        palceOrder.VerifyOrderSpecificValues("Osama","360","21/4/2025","123456789");
    }

}
