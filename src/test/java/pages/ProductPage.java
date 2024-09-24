package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    By priceField = By.xpath("//div[contains(@data-test-id, 'qa-pdp-price-final')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPriceDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceField));
        return priceElement.isDisplayed();
    }

    public boolean isTextSizeCorrect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(priceField));
        String fontSize = priceElement.getCssValue("font-size");
        return fontSize.equals("1.8rem");
    }
}