package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    By emailField = By.xpath("//input[contains(@id, 'qa-login-email-input')]");
    By passwordField = By.xpath("//input[contains(@id, 'qa-login-password-input')]");
    By loginButton = By.xpath("//button[contains(@data-test-id, 'qa-login-submit')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
        } catch (Throwable t){
            System.out.println("An error occurred during runtime.");
        }
    }
}
