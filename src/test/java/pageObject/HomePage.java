package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;

    //лого домашней страницы
    private final By homePageLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    //кнопка личный кабинет
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");

    //кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //кнопка "Булки"
    private final By bunsButton = By.xpath(".//span[text()='Булки']");

    private final By bunsButtonSelected = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Булки']");

    //кнопка "Соусы"
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");

    private final By saucesButtonSelected = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Соусы']");

    //кнопка "Начинки"
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");

    private final By fillingsButtonSelected = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text()='Начинки']");

    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    private final By constructorHeader = By.xpath(".//h1[text()='Соберите бургер']");

    private final By loader = By.xpath(".//img[@alt='loading animation']");

    public boolean createOrderButtonIsVisible(){
        return driver.findElement(createOrderButton).isDisplayed();
    }

    //кликнуть на кнопку "Личный кабинет"
    @Step("Click account button")
    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }

    //кликнуть на кнопку "Войти в аккаунт"
    @Step("Click login button")

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    //кликнуть на кнопку "Булки"
    @Step("Click buns button")

    public void clickBunsButton(){
        driver.findElement(bunsButton).click();
    }

    //Выделена ли кнопка "Булки"
    public boolean isBunsButtonSelected(){
        return driver.findElement(bunsButtonSelected).isDisplayed();
    }

    //кликнуть на кнопку "Соусы"
    @Step("Click sauces button")

    public void clickSaucesButton(){
        driver.findElement(saucesButton).click();
    }

    //выделена ли кнопка "Соусы"
    public boolean isSaucesButtonSelected(){
        return driver.findElement(saucesButtonSelected).isDisplayed();
    }

    //кликнуть на кнопку "Начинки"
    @Step("Click fillings button")

    public void clickFillingsButton(){
        driver.findElement(fillingsButton).click();
    }

    //выделена ли кнопка "Начинки"
    public boolean isFillingsButtonSelected(){
        return driver.findElement(fillingsButtonSelected).isDisplayed();
    }

    public By getBunsSelectedButton(){
        return bunsButtonSelected;
    }

    public By getFillingsSelectedButton(){
        return fillingsButtonSelected;
    }

    public By getSaucesSelectedButton(){
        return saucesButtonSelected;
    }

    public boolean constructorHeaderIsVisible(){
        return driver.findElement(constructorHeader).isDisplayed();
    }
    public By getCreateOrderButton(){
        return createOrderButton;
    }

    public By getLoader() {
        return loader;
    }

    @Step("Wait until home page is loaded")
    public void waitUntilHomePageIsLoaded() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(homePageLogo));
    }

    public HomePage(WebDriver driver){
        this.driver = driver;
    }
}