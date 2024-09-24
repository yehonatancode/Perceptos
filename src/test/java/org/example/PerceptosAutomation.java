package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SearchResultsPage;
import automationUtils.UserCredentials;

import java.io.IOException;
import java.util.Map;
public class PerceptosAutomation {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;

    private static final String URL = "https://www.terminalx.com";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void testTerminalX() throws IOException {
        //Login with a random user from the list
        Map<String, String> user = UserCredentials.getRandomUser();
        homePage.clickOnLogin();
        loginPage.login(user.get("username"), user.get("password"));
        homePage.waitUntilLoadingPerformed();

        //Search for "hello"
        homePage.searchFor("hello");

        //Check if all results contain "hello kitty"
        Assert.assertTrue(searchResultsPage.allResultsContain("hello kitty"));

        //Check if products are ordered by ascending price
        Assert.assertTrue(searchResultsPage.resultsAreOrderedByPriceAscending());

        //Select the third result
        searchResultsPage.selectThirdResult();

        //Check if price is displayed and text size is correct
        Assert.assertTrue(productPage.isPriceDisplayed());
        Assert.assertTrue(productPage.isTextSizeCorrect());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
