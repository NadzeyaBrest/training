package herokuapp;

import core.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;




public class InteractionsTest {
    private WebDriver driver;
    private final By DRAG = By.id("column-a");
    private final By DROP = By.id("column-b");

    @Test
    public void checkDrugAndDrop() {
        driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement dragable = driver.findElement(DRAG);
        WebElement dropable = driver.findElement(DROP);
        Actions action = new Actions(driver);
        action.dragAndDrop(dragable, dropable).perform();
        String textInDropable = dropable.getText();
        System.out.println(textInDropable);
        Assert.assertEquals(textInDropable, "A", "Некорректный текст");
        DriverManager.getInstance().driverQuit();
    }
}
