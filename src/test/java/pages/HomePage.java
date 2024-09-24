package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    By searchBar = By.xpath("//button[contains(@data-test-id, 'qa-header-search-button')]");
    By searchBarText = By.xpath("//input[contains(@data-test-id, 'qa-search-box-input')]");
    By loginButton = By.xpath("//button[contains(@data-test-id, 'qa-header-login-button')]");
    By flightLoadingPageSymbol = By.xpath("//div[contains(@class, 'loading-bar')]");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    }

    public void waitUntilLoadingPerformed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(wait.until(ExpectedConditions.visibilityOfElementLocated(flightLoadingPageSymbol)).isDisplayed()){
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(flightLoadingPageSymbol)));
        }
    }
    public void searchFor(String query) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBarText)).sendKeys(query);
    }
}
