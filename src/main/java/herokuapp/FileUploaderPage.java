package herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class FileUploaderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FileUploaderPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    private final By INPUT_FILE = By.id("file-upload");
    private final By SUBMIT_BUTTON = By.id("file-submit");
    private final By CHOSE_SQUARE = By.id("drag-drop-upload");
    private final By MESSAGE = By.xpath("//div[@id='content']//h3");
    private final By UPLOADED_FILES = By.id("uploaded-files");

    public FileUploaderPage choseFileByButton(String fileName) {
        WebElement buttonForChose = driver.findElement(INPUT_FILE);

        File file = new File(fileName);
        String absolutPath = file.getAbsolutePath();
        buttonForChose.sendKeys(absolutPath);
        return this;
    }

    public void clickSubmitButton() {
        driver.findElement(SUBMIT_BUTTON).click();
    }

    public String getUploadedFileName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(UPLOADED_FILES));
        return driver.findElement(UPLOADED_FILES).getText().trim();
    }
    public String getMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE));
        WebElement messageElement = driver.findElement(MESSAGE);
        return messageElement.getText();
    }
}
