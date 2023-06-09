package com.fluffy.universe.auto.objects;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.fluffy.universe.auto.junit.BrowserExtension.URL;

@RequiredArgsConstructor
public class RegistrationPage {
    private final WebDriver driver;

    private final By firstNameField = By.id("sign-up__first-name");
    private final By lastNameField = By.id("sign-up__last-name");
    private final By emailField = By.id("sign-up__email");
    private final By passwordField = By.id("sign-up__password");
    private final By confirmPasswordField = By.id("sign-up__confirm-password");
    private final By submitButton = By.cssSelector(".form__button");
    private final By alert = By.cssSelector(".alert__inner");

    private final static String ALERT_TEXT = "You have successfully signed up. Welcome to our community!";

    public RegistrationPage openRegistrationPage() {
        driver.get(URL + "sign-up");
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public RegistrationPage enterConfirmPassword(String confirmPassword) {
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
        return this;
    }

    public RegistrationPage clickSubmit() {
        driver.findElement(submitButton).click();
        return this;
    }

    public boolean isAlertShowed() {
        WebElement alertForm = driver.findElement(alert)
                                        .findElement(By.className("alert__description"));

        return alertForm.isDisplayed() && alertForm.getText().equals(ALERT_TEXT);
    }
}
