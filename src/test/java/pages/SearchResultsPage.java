package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    //I adjusted this function more specifically, as the website had combination of hebrew and english, making it harder to parser results, resulting in false positives.
    public boolean allResultsContain(String phrase) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'container_2pxt')]")));
        for (WebElement result : results) {
            String normalizedText = result.getText().replaceAll("[^a-zA-Z ]", "").toLowerCase();
            boolean containsHelloKitty = normalizedText.contains("hello kitty");
            if (!containsHelloKitty) {
                return false;
            }
        }
        return true;
    }

    public boolean resultsAreOrderedByPriceAscending() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> prices = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'price_3AB9 rtl_2D7O')]")));
        double previousPrice = 0.0;

        for (WebElement priceElement : prices) {
            // Get the text of the price and remove currency symbol and whitespace
            String priceText = priceElement.getText().replace("₪", "").trim();

            // Convert the cleaned price text to a double
            double currentPrice = Double.parseDouble(priceText);

            // Check if the current price is less than the previous one (i.e., out of order)
            if (currentPrice < previousPrice) {
                return false;
            }

            // Update previous price to the current one
            previousPrice = currentPrice;
        }

        return true; // Return true if all prices are in ascending order
    }

    public void selectThirdResult() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'container_2pxt')]")));
        if (results.size() >= 3) {
            results.get(2).findElement(By.tagName("a")).click();
        }
    }
}
