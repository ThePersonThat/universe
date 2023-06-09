package com.fluffy.universe.auto;

import com.fluffy.universe.auto.junit.BrowserExtension;
import com.fluffy.universe.auto.junit.DatabaseExtension;
import com.fluffy.universe.auto.junit.Driver;
import com.fluffy.universe.auto.objects.LoginPage;
import com.fluffy.universe.auto.objects.PostPage;
import com.fluffy.universe.models.Post;
import com.fluffy.universe.models.User;
import com.fluffy.universe.services.PostService;
import com.fluffy.universe.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({DatabaseExtension.class, BrowserExtension.class})
public class CommentTest {
    @Driver
    private ChromeDriver driver;

    private Post post;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .roleId(1)
                .firstName("Oleksii")
                .lastName("Haidabrus")
                .email("oleksiihaidabrus@gmail.com")
                .password(UserService.encodePassword("GNM3frcJk8AWJui@"))
                .address("Sumy")
                .build();
        UserService.saveUser(user);

        post = Post.builder()
                .title("Hello world")
                .description("Desription")
                .userId(user.getId())
                .publicationDateTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();

        PostService.savePost(post);

        new LoginPage(driver)
                .openLoginPage()
                .enterEmail("oleksiihaidabrus@gmail.com")
                .enterPassword("GNM3frcJk8AWJui@")
                .clickSubmit();
    }

    @Test
    void shouldCreateComment() {
        // when
        PostPage postPage = new PostPage(driver)
                .openPostPage(post.getId())
                .enterComment("Hello world")
                .clickSubmitButton();

        // then
        assertThat(postPage.commentIsShowed("Hello world", 1)).isTrue();
    }
}
