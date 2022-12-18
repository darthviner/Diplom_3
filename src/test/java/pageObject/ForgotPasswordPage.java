package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private final By loginLink = By.xpath(".//a[text()='Войти']");

    @Step("Click login link")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public ForgotPasswordPage (WebDriver driver){
        this.driver = driver;
    }
}