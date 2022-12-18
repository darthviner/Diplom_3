
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
import pageObject.ForgotPasswordPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegisterPage;
import utils.UserClient;
import utils.UserGenerator;

@RunWith(Parameterized.class)
public class LoginTest {
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
        token = response.extract().path("accessToken");
        token = token.replaceFirst("Bearer ", "");
    }
    public LoginTest(String path){
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

    //Тест на вход через кнопку "Войти" на главной странице
    @Test
    @DisplayName("Login with login button")
    public void loginWithLoginButton() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

    //Тест на вход через кнопку "Личный кабинет" на главной странице
    @Test
    @DisplayName("Login with account button")
    public void loginWithAccountButton() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Login with registration button")
    public void loginWithRegistrationButton() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Login with recover password button")
    public void loginWithForgotPasswordButton() {
        HomePage homePageBurgers = new HomePage(driver);
        homePageBurgers.waitUntilHomePageIsLoaded();
        homePageBurgers.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickPasswordRecoverLink();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();
        loginPage.setTextToInput("Email",user.getEmail());
        loginPage.setTextToInput("Пароль",user.getPassword());
        loginPage.clickLoginButton();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(homePageBurgers.getCreateOrderButton()));
        Assert.assertTrue("Create order button is not Visible", homePageBurgers.createOrderButtonIsVisible());
    }

}