package ui;

import core.DriverManager;
import herokuapp.FileUploaderPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class FileUploaderTest {
    WebDriver driver;
    FileUploaderPage fileUploaderPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");
        fileUploaderPage = new FileUploaderPage(driver);

    }

    @DataProvider(name = "fileName")
    public Object[][] fileNameProvider() {
        return new Object[][]{
                {"test.txt"}
        };
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.getInstance().driverQuit();
    }

    @Test(dataProvider = "fileName")
    public void testUploadingFile(String fileName) {
        fileUploaderPage.choseFileByButton(fileName)
                .clickSubmitButton();
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(fileUploaderPage.getUploadedFileName(), fileName,
                "Некорректное имя загруженного файла");
        soft.assertEquals(fileUploaderPage.getMessage(), "File Uploaded!",
                "Некорректное сообщение о результатах загрузки");
        soft.assertAll();
    }
    public void testUmptyFileDownLoad(){
        fileUploaderPage.clickSubmitButton();
    }
}
