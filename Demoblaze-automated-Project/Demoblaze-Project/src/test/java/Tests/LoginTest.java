package Tests;

import Base.BaseTesttt;
import Pages.HeaderPage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTesttt {
    HeaderPage header;
    LoginPage loginPage;
    @BeforeMethod
    public void preconditions(){
        // Initialize page objects for each test
        header=new HeaderPage(driver);
        loginPage=new LoginPage(driver);
    }
    @Test (priority = 6, description = "Validate that registered user can Log in")
    public void testSuccessfulLogin()  {
        header.clickSignInLink();
        loginPage.enterUsername("Mimo");
        loginPage.enterPassword("Mimo111181");
        loginPage.clickLoginButton();
        String welcomeText=loginPage.getWelcomeMessage();
        Assert.assertEquals(welcomeText, "Welcome Mimo");
        loginPage.clickLogOut();
    }
    @Test (priority = 7, description = "Validate error with blank username and password")
    public void testLoginWithBlankCredentials() {
        header.clickSignInLink();
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Please fill out Username and Password.");
        loginPage.acceptAlert();
    }
    @Test(priority = 8, description = "Validate error with blank username")
    public void testLoginWithBlankUsername() {
        loginPage.enterUsername("");
        loginPage.enterPassword("Mimo111181");
        loginPage.clickLoginButton();
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Please fill out Username and Password.");
        loginPage.acceptAlert();
    }
    @Test(priority = 9, description = "Validate error with blank password")
    public void testLoginWithBlankPassword() {
        loginPage.enterUsername("Mimo");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Please fill out Username and Password.");
        loginPage.acceptAlert();
    }
    @Test(priority = 10, description = "Validate error with wrong password")
    public void testLoginWithWrongPassword() {
        loginPage.enterUsername("Mimo");
        loginPage.enterPassword("123456");
        loginPage.clickLoginButton();
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"Wrong password.");
        loginPage.acceptAlert();
    }
    @Test(priority = 11, description = "Validate error with wrong username")
    public void testLoginWithWrongUsername() {
        loginPage.enterUsername("Bibaaaaaao");
        loginPage.enterPassword("Mimo111181");
        loginPage.clickLoginButton();
        String alert=loginPage.getAlertText();
        Assert.assertEquals(alert,"User does not exist.");
        loginPage.acceptAlert();
    }


}
