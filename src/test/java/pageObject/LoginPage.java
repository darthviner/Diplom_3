package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    //заголовок формы "Вход"
    private final By loginPageHeader = By.xpath(".//h2[text()='Вход']");

    //поле "Пароль"
    private By input(String inputName){
        return  By.xpath(".//label[text()='"+inputName+"']//..//input");
    }

    //кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    //ссылка "Зарегистрироваться"
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");

    //ссылка "Восставновить пароль"
    private final By passwordRecoverLink = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Set text {0} to input {1}")
    public void setTextToInput(String inputName, String text){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(input(inputName)));
        driver.findElement(input(inputName)).sendKeys(text);
    }

    @Step("Click login button")
    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    @Step("Click register button")
    public void clickRegisterButton(){
        driver.findElement(registerLink).click();
    }

    @Step("Click password recover link")
    public void clickPasswordRecoverLink(){
        driver.findElement(passwordRecoverLink).click();
    }

    public LoginPage (WebDriver driver){
        this.driver = driver;
    }

    @Step("Get login page header")
    public By getLoginPageHeader() {
        return loginPageHeader;
    }

    @Step("Check if login page header is displayed")
    public boolean loginPageHeaderIsDisplayed() {
        return driver.findElement(loginPageHeader).isDisplayed();
    }

}