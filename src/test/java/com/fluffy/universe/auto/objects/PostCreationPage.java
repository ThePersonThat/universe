package com.fluffy.universe.auto.objects;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.fluffy.universe.auto.junit.BrowserExtension.URL;


@RequiredArgsConstructor
public class PostCreationPage {
    private final WebDriver driver;
    private final By titleInput = By.cssSelector(".post-form__title");
    private final By descriptionInput = By.cssSelector(".post-form__description");
    private final By submit = By.xpath("/html/body/main/div/form/div[3]/button");
    private final By alert = By.cssSelector(".alert__inner");

    private final static String ALERT_TEXT = "Blog post published successfully.";

    public PostCreationPage openPostPage() {
        driver.get(URL + "posts/create");
        return this;
    }

    public PostCreationPage enterTitle(String title) {
        driver.findElement(titleInput).sendKeys(title);
        return this;
    }

    public PostCreationPage enterDescription(String description) {
        driver.findElement(descriptionInput).sendKeys(description);
        return this;
    }

    public PostCreationPage clickSubmit() {
        driver.findElement(submit).click();
        return this;
    }

    public boolean isAlertShowed() {
        WebElement alertForm = driver.findElement(alert)
                .findElement(By.className("alert__description"));

        return alertForm.isDisplayed() && alertForm.getText().equals(ALERT_TEXT);
    }
}
