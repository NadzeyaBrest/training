package herokuapp;

import core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AutocompletePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By AUTOCOMPLETE = By.id("autocomplete");
    private final By SELECTED_RESULTS = By.id("selected");
    private final By LIST_SUGGESTIONS = By.id("suggestions");
    private final By SUGGESTION_ITEMS = By.cssSelector("#suggestions li");

    public AutocompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public void enterText(String text) {
        WebElement inputField = driver.findElement(AUTOCOMPLETE);
        inputField.sendKeys(text);
    }

    public void clickSuggestion(String suggestionText) {
        WebElement ul = wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_SUGGESTIONS));
        List<WebElement> suggestions = driver.findElements(SUGGESTION_ITEMS);
        WebElement appleSuggestion = suggestions.stream().filter(el -> el.getText().equals(suggestionText)).findFirst().orElse(null);
        if (appleSuggestion != null) {
            appleSuggestion.click();
        }
    }

    public String getSelectedResults() {
        WebElement resultBox = wait.until(ExpectedConditions.visibilityOfElementLocated(SELECTED_RESULTS));
        return resultBox.getText();
    }


}
