package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class PlaceOrderPage {
    WebDriver driver;
    WebDriverWait wait;
    public PlaceOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators for Place Order Page

    public By CartTab= By.id("cartur");
    public By PlaceOrderButton=By.xpath("//button[@type=\"button\" and @class=\"btn btn-success\"]");
    By Name=By.id("name");
    By Country=By.id("country");
    By City=By.id("city");
    By CreditCard=By.id("card");
    By Month=By.id("month");
    By Year=By.id("year");
    public By PurchaseButton=By.xpath("//button[@type=\"button\" and @onclick=\"purchaseOrder()\"]");
    By ConfirmButton=By.xpath("//button[@class=\"confirm btn btn-lg btn-primary\"]");
    public By ConfirmationMessage=By.xpath("//div[@class=\"sweet-alert  showSweetAlert visible\"]");
    By buttonOk = By.xpath("//button[text()=\"OK\"]");
    //Methods for Place Order Page

    //Fill Purchase Information Form
    public void EnterPurchaseInfo(String name,String country,String city,String creditcard,String month,String year){
       // driver.findElement(CartTab).click();
        driver.findElement(PlaceOrderButton).click();
        driver.findElement(Name).sendKeys(name);
        driver.findElement(Country).sendKeys(country);
        driver.findElement(City).sendKeys(city);
        driver.findElement(CreditCard).sendKeys(creditcard);
        driver.findElement(Month).sendKeys(month);
        driver.findElement(Year).sendKeys(year);
        driver.findElement(PurchaseButton).click();
    }
    //Verify Purchase confirmation
    public void verifyPurchaseConfirmation() {
        WebElement confirmation = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(ConfirmationMessage));
        String confirmationtext= confirmation.getText();
        Assert.assertTrue(confirmationtext.contains("Thank you for your purchase!"));
        driver.findElement(ConfirmButton);
    }

    //Verify Purchase Information
    public void verifyPurchaseInformation(){
        String details= driver.findElement(ConfirmationMessage).getText();
        Assert.assertTrue(details.contains("Id:"));
        Assert.assertTrue(details.contains("Amount:"));
        Assert.assertTrue(details.contains("Card Number:"));
        Assert.assertTrue(details.contains("Date:"));
    }

    public void VerifyOrderSpecificValues(String expectedName, String expectedAmount, String expectedDate, String expectedCardNo ){
        SoftAssert softAssert=new SoftAssert();
        String details= driver.findElement(ConfirmationMessage).getText();
        softAssert.assertTrue(details.contains("Name: " + expectedName),
                "Name not matching: " + expectedName);
        softAssert.assertTrue(details.contains("Amount: " + expectedAmount + " USD"),
                "Amount not matching: " + expectedAmount);
        softAssert.assertTrue(details.contains("Date: " + expectedDate),
                "Date not matching: " + expectedDate);
        softAssert.assertTrue(details.contains("Card Number: " + expectedCardNo),
                "Card Number not matching: " + expectedCardNo);
        softAssert.assertAll();
    }

    public void clickOK() {
        driver.findElement(buttonOk).click();
    }
}
