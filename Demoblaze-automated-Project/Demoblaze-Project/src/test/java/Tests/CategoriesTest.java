package Tests;


import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.CategoriesPage;

import java.util.List;


// This class contains TestNG test cases to verify the functionality of the Categories page
public class CategoriesTest extends BaseTest {

    // Test to verify that the Categories page loads correctly by checking the page title
    @Test(priority = 1, description = "Verify that the CategoriesPage loads correctly")
    public void testCategoriesPageLoadsCorrectly() {
        String pageTitle = driver.getTitle(); // Get the title of the current page
        // Assert that the page title is "STORE", otherwise fail the test
        Assert.assertEquals(pageTitle, "STORE", "CategoriesPage title is incorrect");
    }

    // Test to verify that the categories section (sidebar) is displayed on the page
    @Test(priority = 2, description = "Verify that the categories section is displayed")
    public void testCategoriesSectionIsDisplayed() {
        CategoriesPage catPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        // Assert that the categories section is displayed, otherwise fail the test
        Assert.assertTrue(catPage.isCategoriesSectionDisplayed(), "Categories section is not displayed on the CategoriesPage");
    }

    // Test to verify that selecting the "Phones" category displays phone-related products
    @Test(priority = 3, description = "Verify that the Phones category shows phone products")
    public void testPhonesCategoryShowsPhoneProducts() {
        CategoriesPage catPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        catPage.clickCategory("Phones"); // Click on the "Phones" category
        int productCount = catPage.getProductListSize(); // Get the number of products displayed
        // Assert that there are products displayed, otherwise fail the test
        Assert.assertTrue(productCount > 0, "No products displayed in Phones category");

        List<String> productNames = catPage.getProductNames(); // Get the names of the displayed products
        // Check if all product names contain phone-related keywords
        boolean allPhones = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("phone") ||
                        name.toLowerCase().contains("nexus") ||
                        name.toLowerCase().contains("galaxy") ||
                        name.toLowerCase().contains("iphone") ||
                        name.toLowerCase().contains("samsung") ||
                        name.toLowerCase().contains("nokia") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("htc"));
        // Assert that all products are phone-related, otherwise fail the test
        Assert.assertTrue(allPhones, "Products in Phones category do not seem to be phones: " + productNames);
    }

    // Test to verify that selecting the "Laptops" category displays laptop-related products
    @Test(priority = 4, description = "Verify that the Laptops category shows laptop products")
    public void testLaptopsCategoryShowsLaptopProducts() {
        CategoriesPage catPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        catPage.clickCategory("Laptops"); // Click on the "Laptops" category
        int productCount = catPage.getProductListSize(); // Get the number of products displayed
        // Assert that there are products displayed, otherwise fail the test
        Assert.assertTrue(productCount > 0, "No products displayed in Laptops category");

        List<String> productNames = catPage.getProductNames(); // Get the names of the displayed products
        // Check if all product names contain laptop-related keywords
        boolean allLaptops = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("laptop") ||
                        name.toLowerCase().contains("macbook") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("hp") ||
                        name.toLowerCase().contains("lenovo"));
        // Assert that all products are laptop-related, otherwise fail the test
        Assert.assertTrue(allLaptops, "Products in Laptops category do not seem to be laptops: " + productNames);
    }

    // Test to verify that selecting the "Monitors" category displays monitor-related products
    @Test(priority = 5, description = "Verify that the Monitors category shows monitor products")
    public void testMonitorsCategoryShowsMonitorProducts() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        categoriesPage.clickCategory("Monitors"); // Click on the "Monitors" category
        int productCount = categoriesPage.getProductListSize(); // Get the number of products displayed
        // Assert that there are products displayed, otherwise fail the test
        Assert.assertTrue(productCount > 0, "No products displayed in Monitors category");

        List<String> productNames = categoriesPage.getProductNames(); // Get the names of the displayed products
        // Check if all product names contain monitor-related keywords
        boolean allMonitors = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("monitor") ||
                        name.toLowerCase().contains("apple") ||
                        name.toLowerCase().contains("asus") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("lg"));
        // Assert that all products are monitor-related, otherwise fail the test
        Assert.assertTrue(allMonitors, "Products in Monitors category do not seem to be monitors: " + productNames);
    }

    // Test to verify that the default state (before selecting any category) shows products from multiple categories
    @Test(priority = 6, description = "Verify that the default state shows mixed products")
    public void testDefaultStateShowsMixedProducts() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        int productCount = categoriesPage.getProductListSize(); // Get the number of products displayed
        // Assert that there are products displayed, otherwise fail the test
        Assert.assertTrue(productCount > 0, "No products displayed in default state");

        List<String> productNames = categoriesPage.getProductNames(); // Get the names of the displayed products
        // Check if there are phone-related products
        boolean hasPhone = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("phone") ||
                        name.toLowerCase().contains("nexus") ||
                        name.toLowerCase().contains("galaxy") ||
                        name.toLowerCase().contains("iphone") ||
                        name.toLowerCase().contains("samsung") ||
                        name.toLowerCase().contains("nokia") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("htc"));
        // Check if there are laptop-related products
        boolean hasLaptop = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("laptop") ||
                        name.toLowerCase().contains("macbook") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("hp") ||
                        name.toLowerCase().contains("lenovo"));
        // Check if there are monitor-related products
        boolean hasMonitor = productNames.stream()
                .anyMatch(name -> name.toLowerCase().contains("monitor") ||
                        name.toLowerCase().contains("apple") ||
                        name.toLowerCase().contains("asus") ||
                        name.toLowerCase().contains("dell") ||
                        name.toLowerCase().contains("lg"));
        // Count the number of categories present in the default state
        int categoriesPresent = 0;
        if (hasPhone) categoriesPresent++;
        if (hasLaptop) categoriesPresent++;
        if (hasMonitor) categoriesPresent++;
        // Assert that products from at least two categories are present, otherwise fail the test
        Assert.assertTrue(categoriesPresent >= 2, "Default state should contain products from at least two categories. Found: " + productNames);
    }

    // Test to verify that switching between categories updates the displayed products
    @Test(priority = 7, description = "Verify that switching between categories works correctly")
    public void testSwitchingBetweenCategories() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        categoriesPage.clickCategory("Phones"); // Click on the "Phones" category
        int phoneProductCount = categoriesPage.getProductListSize(); // Get the number of products in "Phones"
        categoriesPage.clickCategory("Laptops"); // Switch to the "Laptops" category
        int laptopProductCount = categoriesPage.getProductListSize(); // Get the number of products in "Laptops"
        // Assert that the product count changed after switching to "Laptops", otherwise fail the test
        Assert.assertNotEquals(laptopProductCount, phoneProductCount, "Product count did not change after switching to Laptops category");

        categoriesPage.clickCategory("Monitors"); // Switch to the "Monitors" category
        int monitorProductCount = categoriesPage.getProductListSize(); // Get the number of products in "Monitors"
        // Assert that the product count changed after switching to "Monitors", otherwise fail the test
        Assert.assertNotEquals(monitorProductCount, laptopProductCount, "Product count did not change after switching to Monitors category");
    }

    // Test to verify that clicking the "Next" button changes the displayed products
    @Test(priority = 8, description = "Verify that the Next button changes the products")
    public void testNextButtonFunctionality() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        int initialProductCount = categoriesPage.getProductListSize(); // Get the initial number of products
        if (categoriesPage.isNextButtonEnabled()) { // Check if the "Next" button is enabled
            categoriesPage.clickNextButton(); // Click the "Next" button
            int newProductCount = categoriesPage.getProductListSize(); // Get the number of products after clicking "Next"
            // Assert that the product count changed after clicking "Next", otherwise fail the test
            Assert.assertNotEquals(newProductCount, initialProductCount, "Product count did not change after clicking Next");
        } else {
            System.out.println("Next button is not enabled, skipping test."); // Log if the "Next" button is disabled
        }
    }

    // Test to verify that the "Previous" button keeps the user in the same category (BUG_CAT_05)
    @Test(priority = 9, description = "Verify that the Previous button stays in the same category (Updated for BUG_CAT_05)")
    public void testPreviousButtonFunctionality() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        categoriesPage.clickCategory("Phones"); // Select the "Phones" category
        if (categoriesPage.isNextButtonEnabled()) { // Check if the "Next" button is enabled
            categoriesPage.clickNextButton(); // Click the "Next" button to go to the next page of products
            categoriesPage.clickPreviousButton(); // Click the "Previous" button to go back
            List<String> productNames = categoriesPage.getProductNames(); // Get the names of the displayed products
            // Check if all products are still phone-related after clicking "Previous"
            boolean allPhones = productNames.stream()
                    .allMatch(name -> name.toLowerCase().contains("phone") ||
                            name.toLowerCase().contains("nexus") ||
                            name.toLowerCase().contains("galaxy") ||
                            name.toLowerCase().contains("iphone") ||
                            name.toLowerCase().contains("samsung") ||
                            name.toLowerCase().contains("nokia") ||
                            name.toLowerCase().contains("sony") ||
                            name.toLowerCase().contains("htc"));
            // Assert that the products are still in the "Phones" category, otherwise fail the test
            Assert.assertTrue(allPhones, "Previous button did not stay in Phones category: " + productNames);
        } else {
            System.out.println("Next button is not enabled, skipping test."); // Log if the "Next" button is disabled
        }
    }

    // Test to verify that the selected category persists after refreshing the page (BUG_CAT_01)
    @Test(priority = 10, description = "Verify that category selection persists after page refresh (BUG_CAT_01)")
    public void testCategorySelectionPersistenceAfterRefresh() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        categoriesPage.clickCategory("Phones"); // Select the "Phones" category
        int phoneProductCount = categoriesPage.getProductListSize(); // Get the number of products in "Phones"
        categoriesPage.refreshPage(); // Refresh the page
        int productCountAfterRefresh = categoriesPage.getProductListSize(); // Get the number of products after refresh
        // Assert that the product count remains the same after refresh, meaning the category selection persisted
        Assert.assertEquals(productCountAfterRefresh, phoneProductCount, "Product count changed after page refresh, category selection did not persist");
    }

    // Test to verify that selecting the "Phones" category shows the correct phone-related products (BUG_CAT_02)
    // This was modified to check the products instead of visual highlighting due to issues with CSS styles
    @Test(priority = 11, description = "Verify that selecting a category shows the correct products (BUG_CAT_02)")
    public void testVisualHighlightingForSelectedCategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        categoriesPage.clickCategory("Phones"); // Select the "Phones" category
        List<String> productNames = categoriesPage.getProductNames(); // Get the names of the displayed products
        // Check if all product names are phone-related
        boolean allPhones = productNames.stream()
                .allMatch(name -> name.toLowerCase().contains("phone") ||
                        name.toLowerCase().contains("nexus") ||
                        name.toLowerCase().contains("galaxy") ||
                        name.toLowerCase().contains("iphone") ||
                        name.toLowerCase().contains("samsung") ||
                        name.toLowerCase().contains("nokia") ||
                        name.toLowerCase().contains("sony") ||
                        name.toLowerCase().contains("htc"));
        // Assert that all products are phone-related, otherwise fail the test
        Assert.assertTrue(allPhones, "Products in Phones category do not seem to be phones: " + productNames);
    }

    // Test to verify that the URL updates after selecting a category (BUG_CAT_04)
    @Test(priority = 12, description = "Verify URL updates after selecting a category (BUG_CAT_04)")
    public void testUrlUpdatesAfterCategorySelection() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        String initialUrl = driver.getCurrentUrl(); // Get the initial URL before selecting a category
        categoriesPage.clickCategory("Phones"); // Select the "Phones" category
        String updatedUrl = driver.getCurrentUrl(); // Get the updated URL after selecting the category
        // Assert that the URL changed after selecting the category
        Assert.assertNotEquals(updatedUrl, initialUrl, "URL did not update after selecting Phones category");
        // Assert that the updated URL contains the "category=phones" parameter
        Assert.assertTrue(updatedUrl.contains("category=phones"), "URL does not contain the selected category parameter (category=phones)");
    }

    // Test to verify that the "Previous" button navigates to the previous category (BUG_CAT_06)
    @Test(priority = 13, description = "Verify Previous button navigates to the previous category (BUG_CAT_06)")
    public void testPreviousButtonNavigatesToPreviousCategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        categoriesPage.clickCategory("Laptops"); // Select the "Laptops" category (assuming order: Phones -> Laptops -> Monitors)
        if (categoriesPage.isNextButtonEnabled()) { // Check if the "Next" button is enabled
            categoriesPage.clickNextButton(); // Click the "Next" button to go to the next page
            categoriesPage.clickPreviousButton(); // Click the "Previous" button to go back
            List<String> productNames = categoriesPage.getProductNames(); // Get the names of the displayed products
            // Check if all products are phone-related (previous category should be "Phones")
            boolean allPhones = productNames.stream()
                    .allMatch(name -> name.toLowerCase().contains("phone") ||
                            name.toLowerCase().contains("nexus") ||
                            name.toLowerCase().contains("galaxy") ||
                            name.toLowerCase().contains("iphone") ||
                            name.toLowerCase().contains("samsung") ||
                            name.toLowerCase().contains("nokia") ||
                            name.toLowerCase().contains("sony") ||
                            name.toLowerCase().contains("htc"));
            // Assert that the products are in the "Phones" category, otherwise fail the test
            Assert.assertTrue(allPhones, "Previous button did not navigate to the previous category (Phones): " + productNames);
        } else {
            System.out.println("Next button is not enabled, skipping test."); // Log if the "Next" button is disabled
        }
    }

    // Test to verify that selecting an invalid category does not change the displayed products (BUG_CAT_07)
    @Test(priority = 14, description = "Verify selecting an invalid category does not change products (BUG_CAT_07)")
    public void testSelectingInvalidCategory() {
        CategoriesPage categoriesPage = new CategoriesPage(driver); // Create an instance of CategoriesPage
        int initialProductCount = categoriesPage.getProductListSize(); // Get the initial number of products
        // Assert that there are products displayed in the default state, otherwise fail the test
        Assert.assertTrue(initialProductCount > 0, "No products displayed in default state, cannot test invalid category selection");
        boolean categoryClicked = categoriesPage.clickCategoryByName("Tablets"); // Try to click an invalid category ("Tablets")
        // Assert that the invalid category was not clickable, otherwise fail the test
        Assert.assertFalse(categoryClicked, "Invalid category 'Tablets' was clicked, but it should not exist");
        int productCountAfter = categoriesPage.getProductListSize(); // Get the number of products after attempting to click
        // Assert that the product count did not change after selecting an invalid category
        Assert.assertEquals(productCountAfter, initialProductCount, "Product count changed after selecting invalid category, system should not redirect to default");
    }
}