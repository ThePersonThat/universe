package com.fluffy.universe.auto.objects;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.fluffy.universe.auto.junit.BrowserExtension.URL;

@RequiredArgsConstructor
public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.id("sign-in__email");
    private final By passwordField = By.id("sign-in__password");
    private final By submitButton = By.cssSelector(".form__button");

    public LoginPage openLoginPage() {
        driver.get(URL + "sign-in");
        return this;
    }

    public LoginPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public LoginPage clickSubmit() {
        driver.findElement(submitButton).click();
        return this;
    }
}
