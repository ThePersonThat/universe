package com.fluffy.universe.auto.objects;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.fluffy.universe.auto.junit.BrowserExtension.URL;

@RequiredArgsConstructor
public class PostPage {
    private final WebDriver driver;
    private final By commentArea = By.cssSelector(".comment-form__textarea");
    private final By submit = By.xpath("/html/body/main/div/div/div/form/div[3]/button[1]");
    private final By comments = By.cssSelector(".comments");

    public PostPage openPostPage(Integer postId) {
        driver.get(URL + "posts/" + postId);
        return this;
    }

    public PostPage enterComment(String comment) {
        driver.findElement(commentArea).sendKeys(comment);
        return this;
    }

    public PostPage clickSubmitButton() {
        driver.findElement(submit).click();
        return this;
    }

    public boolean commentIsShowed(String commentText, int commentIndex) {
        List<WebElement> comments = driver.findElement(this.comments)
                .findElements(By.cssSelector(".comment"));
        WebElement comment = comments.get(commentIndex - 1)
                .findElement(By.cssSelector(".comment__description"));

        return comment.isDisplayed() && comment.getText().equals(commentText);
    }
}
