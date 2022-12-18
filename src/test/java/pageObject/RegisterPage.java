package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final WebDriver driver;
    private By input(String inputName){
        return By.xpath(".//label[text()='"+inputName+"']/parent::div/input");
    }
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By loginLink = By.xpath(".//a[text()='Войти']");

    private final By incorrectPasswordError = By.xpath(".//p[text()='Некорректный пароль']");

    @Step("Set value {1} to input {0}")
    public void setTextToInput(String inputName, String text){
        WebDriverWait webDriverWait = new WebDriverWait(driver,5);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(input(inputName)));
        driver.findElement(input(inputName)).sendKeys(text);
    }
    @Step("Click register button")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Check if incorrect password error text is visible")
    public boolean incorrectPasswordErrorIsVisible() {
        return driver.findElement(incorrectPasswordError).isDisplayed();
    }

    public RegisterPage (WebDriver driver){
        this.driver = driver;
    }
}