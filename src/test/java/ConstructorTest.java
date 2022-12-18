import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.HomePage;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;
    private final String path;
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    public ConstructorTest(String path){
        this.path = path;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters(){
        return new Object[][]{
                {"src/main/resources/chromedriver.exe"},
                {"src/main/resources/yandexdriver.exe"}
        };
    }
    @After
    public void teardown() {
        driver.quit();
    }

    //Тест на выбор списка соусов
    @Test
    @DisplayName("Select sauces")
    public void transitionToSauces() {
        HomePage homePageBurgers = new HomePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(homePageBurgers.getLoader()));
        homePageBurgers.clickSaucesButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getSaucesSelectedButton()));
        Assert.assertTrue("Sauces button is not selected", homePageBurgers.isSaucesButtonSelected());
    }

    @Test
    @DisplayName("Select fillings")
    public void transitionToFillings() {
        HomePage homePageBurgers = new HomePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(homePageBurgers.getLoader()));
        homePageBurgers.clickFillingsButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getFillingsSelectedButton()));
        Assert.assertTrue("Fillings button is not selected", homePageBurgers.isFillingsButtonSelected());
    }

    @Test
    @DisplayName("Select fillings then buns")
    public void transitionFromFillingsToBuns() {
        HomePage homePageBurgers = new HomePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(homePageBurgers.getLoader()));
        homePageBurgers.clickFillingsButton();
        homePageBurgers.clickBunsButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getBunsSelectedButton()));
        Assert.assertTrue("Fillings button is not selected", homePageBurgers.isBunsButtonSelected());
    }
}
