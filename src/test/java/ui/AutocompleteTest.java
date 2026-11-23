package ui;

import core.DriverManager;
import herokuapp.AutocompletePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AutocompleteTest {
    private WebDriver driver;
    private AutocompletePage autocompletePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://the-internet.herokuapp.com/autocomplete");
        driver.manage().window().maximize();
        autocompletePage = new AutocompletePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.getInstance().driverQuit();
    }

    @DataProvider(name = "autocompleate")
    public Object[][] dataForSearch() {
        return new Object[][]{
                {"app", "Apple"},
                {"lem", "Lemon"}
        };
    }

    @Test(dataProvider = "autocompleate", description = "проверка успешного вывода подсказок")
    public void testAutocompleteClick(String inputText, String expectedSuggestions) {
        autocompletePage.enterText(inputText);
        autocompletePage.clickSuggestion(expectedSuggestions);
        String resultBoxText = autocompletePage.getSelectedResults();
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(resultBoxText, "You selected: " + expectedSuggestions, "Текст выбранного результата не совпадают");
        soft.assertAll();
    }

}
