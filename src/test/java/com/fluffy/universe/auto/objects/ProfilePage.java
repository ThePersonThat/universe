package com.fluffy.universe.auto.objects;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.fluffy.universe.auto.junit.BrowserExtension.URL;

@RequiredArgsConstructor
public class ProfilePage {
    private final WebDriver driver;
    private final By profileForm = By.cssSelector(".account-form");
    private final By firstNameField = By.id("account__first-name");
    private final By lastNameField = By.id("account__last-name");

    public ProfilePage openProfilePage() {
        driver.get(URL + "account");
        return this;
    }

    public boolean isProfileFormShowed() {
        return driver.findElement(profileForm).isDisplayed();
    }

    public boolean isFirstNameAndLastNameShowed(String firstName, String lastName) {
        return verifyField(firstName, firstNameField) && verifyField(lastName, lastNameField);
    }

    private boolean verifyField(String text, By field) {
        WebElement element = driver.findElement(field);

        return element.isDisplayed() && element.getAttribute("value").equals(text);
    }
}
