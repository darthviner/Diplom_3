package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    private final By burgerLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    private final By hintLabel = By.xpath(".//p[text()='В этом разделе вы можете изменить свои персональные данные']");

    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    private final By loader = By.xpath(".//img[@alt='loading animation']");

    @Step("Click constructor button")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public boolean hintLabelIsVisible() {
        return driver.findElement(hintLabel).isDisplayed();
    }

    @Step("Click burger logo")
    public void clickBurgerLogo() {
        driver.findElement(burgerLogo).click();
    }

    public By getHintLabel() {
        return hintLabel;
    }

    @Step("Click logout button")
    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    @Step("Get loader")
    public By getLoader() {
        return loader;
    }

    public ProfilePage (WebDriver driver){
        this.driver = driver;
    }
}