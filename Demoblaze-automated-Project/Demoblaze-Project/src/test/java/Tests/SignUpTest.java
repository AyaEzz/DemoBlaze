package Tests;

import Base.BaseTesttt;
import Pages.HeaderPage;
import Pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpTest extends BaseTesttt {
    HeaderPage homePage;
    SignUpPage signupPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void beforeMethod() {
        // Initialize page objects for each test
        homePage = new HeaderPage(driver);
        signupPage = new SignUpPage(driver);
       // homePage.clickSignUpLink();
    }

  //  @Test(priority = 1, description = "Validate that User can Sign up with valid credentials")
    public void testValidSignup() {
        homePage.clickSignUpLink();
        signupPage.enterUsername("Omayiah");
        signupPage.enterPassword("Oyaiee111");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Sign up successful.");
        signupPage.acceptAlert();
    }
    @Test(priority = 2, description = "Validate error message when sign up with blank User name")
    public void testSignupWithBlankUsername()  {
        homePage.clickSignUpLink();
        signupPage.enterUsername("");
        signupPage.enterPassword("123456");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        signupPage.acceptAlert();
    }
    @Test(priority = 3, description = "Validate error message when sign up with blank Password")
    public void testSignupWithBlankPassword() {
        signupPage.enterUsername("TestUser");
        signupPage.enterPassword("");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        signupPage.acceptAlert();
    }
    @Test(priority = 4, description = "Validate error message when sign up with blank User name and Password")
    public void testSignupWithBlankUsernameAndPassword(){
        signupPage.enterUsername("");
        signupPage.enterPassword("");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        signupPage.acceptAlert();
    }
   @Test(priority = 5, description = "Validate existing user signup")
    public void testSignupWithExistingUser(){
        signupPage.enterUsername("Mimo");
        signupPage.enterPassword("Mimo111181");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        Assert.assertEquals(alertText, "This user already exist.");
        signupPage.acceptAlert();
    }
    //
    @Test(priority = 6, description = "Validate that Entering Space as username should show error message")
    public void testSignupWithSpaceUsername()  {
       // homePage.clickSignUpLink();
        signupPage.enterUsername(" ");
        signupPage.enterPassword("Mimo111181");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        softAssert.assertEquals(alertText, "Please Enter Valid Username");
        signupPage.acceptAlert();
        softAssert.assertAll();
    }
    @Test(priority = 7, description = "Validate that Entering SQL injection as username should show error message")
    public void testSignupWithSQLInjectionUsername(){
        String sqlInjection = "INSERT INTO user (user_name) VALUES (QQQ);";
        signupPage.enterUsername(sqlInjection);
        signupPage.enterPassword("Mimo111181");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        softAssert.assertEquals(alertText, "Please Enter Valid Username");
        signupPage.acceptAlert();
        softAssert.assertAll();
    }
    @Test(priority = 8, description = "Validate that Entering Special characters as username should show error message")
    public void testSignupWithSpecialCharactersUsername() {
        String specialChars = "!@#$%^&**&^^%$##";
        signupPage.enterUsername(specialChars);
        signupPage.enterPassword("Mimo111181");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        softAssert.assertEquals(alertText, "Please Enter Valid Username");
        signupPage.acceptAlert();
        softAssert.assertAll();

    }
    @Test(priority = 9, description = "Validate that Entering 200 characters username should show error message")
    public void testSignupWithLongUsername() throws InterruptedException {
        String longUsername = "asdfghjkloiuytrewxcvbnmlkjhgfdteruosnbdhbcbcyfbvybfvybfvbyuvbyvbrvyvyubrvubfhvbfgbvyfvbfudndjnhcfuyvbryvbrfiuvnfhvrb7r8vhjfimcjdnhcbdcbtydcf8jvimvingbugbyb7vhgvnkjnchubstf54fe6fhreuivrfvngy8vhg9vjmgmgniujgg";
        signupPage.enterUsername(longUsername);
        signupPage.enterPassword("Mimo111181");
        signupPage.clickSignUpButton();
        String alertText = signupPage.getAlertText();
        softAssert.assertEquals(alertText, "Please Enter Valid Username");
        signupPage.acceptAlert();
        softAssert.assertAll();
        signupPage.clickClose();
    }


}
