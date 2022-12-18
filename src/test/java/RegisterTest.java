import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import utils.UserGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegisterTest {
    private WebDriver driver;
    private final String path;
    private User user;
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/register";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.get(BASE_URI);
        user = UserGenerator.getUniqueUser();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
    public RegisterTest(String path){
        this.path = path;
    }
    @Parameterized.Parameters
    public static Object[][] getParameters(){
        return new Object[][]{
                {"src/main/resources/chromedriver.exe"},
                {"src/main/resources/yandexdriver.exe"}
        };
    }

    @Test
    @DisplayName("Successful registration test")
    public void successfulRegistrationTest(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.setTextToInput("Имя", user.getName());
        registerPage.setTextToInput("Email", user.getEmail());
        registerPage.setTextToInput("Пароль", user.getPassword());
        registerPage.clickRegisterButton();
        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginPageHeader()));
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://stellarburgers.nomoreparties.site/login";
        assertEquals("Wrong url", currentUrl, expectedUrl);
    }
    @Test
    @DisplayName("Unsuccessful registration with short password test")
    public void unsuccessfulRegistrationWithShortPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        user.setPassword("12345");
        registerPage.setTextToInput("Имя", user.getName());
        registerPage.setTextToInput("Email", user.getEmail());
        registerPage.setTextToInput("Пароль", user.getPassword());
        registerPage.clickRegisterButton();
        assertTrue(registerPage.incorrectPasswordErrorIsVisible());
    }
}
