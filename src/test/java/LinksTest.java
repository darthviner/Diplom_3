
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.User;
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
import pageObject.LoginPage;
import pageObject.ProfilePage;
import utils.UserClient;
import utils.UserGenerator;

@RunWith(Parameterized.class)
public class LinksTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String token;
    private final String path;
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver",path);
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.get("https://stellarburgers.nomoreparties.site/");
        userClient = new UserClient();
        user = UserGenerator.getUniqueUser();
        ValidatableResponse response = userClient.create(user);
        String rawToken = response.extract().path("accessToken");
        token = rawToken.replaceFirst("Bearer ", "");
    }
    public LinksTest(String path){
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
        userClient.delete(token);
        driver.quit();
    }

    //Тест на переход в личный кабинет через кнопку "Личный кабинет" на главной странице
    @Test
    @DisplayName("Transition to profile with login")
    public void transitionToProfileTest() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        homePageBurgers.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(profilePage.getHintLabel()));
        Assert.assertTrue("Constructor header is not visible", profilePage.hintLabelIsVisible());
    }
    @Test
    @DisplayName("Transition to constructor from profile with constructor button")
    public void transitionToConstructorFromProfileViaConstructorButtonTest() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        homePageBurgers.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(profilePage.getLoader()));
        profilePage.clickConstructorButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        Assert.assertTrue("Constructor header is not visible", homePageBurgers.constructorHeaderIsVisible());
    }

    @Test
    @DisplayName("Transition to constructor from profile with logo")
    public void transitionToConstructorFromProfileViaLogoTest() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        homePageBurgers.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(profilePage.getLoader()));
        profilePage.clickBurgerLogo();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        Assert.assertTrue("Constructor header is not visible", homePageBurgers.constructorHeaderIsVisible());
    }
    @Test
    @DisplayName("Transition from profile to login via logout")
    public void logoutTest() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        homePageBurgers.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(profilePage.getHintLabel()));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(profilePage.getLoader()));
        profilePage.clickLogoutButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginPage.getLoginPageHeader()));
        Assert.assertTrue("Login page header is not visible", loginPage.loginPageHeaderIsDisplayed());
    }
}