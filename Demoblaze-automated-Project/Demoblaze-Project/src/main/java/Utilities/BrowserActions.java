package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserActions {

    public static void clickWithRetry(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(d -> {
            try {
                WebElement element = d.findElement(locator);
                element.click();
                return true;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException encountered. Retrying...");
                return false;
            } catch (ElementClickInterceptedException e) {
                System.out.println("ElementClickInterceptedException encountered. Retrying...");
                return false;
            }
        });
    }
}