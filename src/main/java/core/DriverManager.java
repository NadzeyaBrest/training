package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverManager instance;

    private DriverManager() {
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            return new ChromeDriver();
        } else {
            return driver;
        }

    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
        instance = null;
    }
}
