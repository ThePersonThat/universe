package com.fluffy.universe.auto;

import com.fluffy.universe.auto.junit.BrowserExtension;
import com.fluffy.universe.auto.junit.DatabaseExtension;
import com.fluffy.universe.auto.junit.Driver;
import com.fluffy.universe.auto.objects.LoginPage;
import com.fluffy.universe.auto.objects.ProfilePage;
import com.fluffy.universe.models.User;
import com.fluffy.universe.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith({DatabaseExtension.class, BrowserExtension.class})
public class ProfileTest {
    @Driver
    private ChromeDriver driver;

    @BeforeEach
    void setUp() {
        UserService.saveUser(User.builder()
                .roleId(1)
                .firstName("Oleksii")
                .lastName("Haidabrus")
                .email("oleksiihaidabrus@gmail.com")
                .password(UserService.encodePassword("GNM3frcJk8AWJui@"))
                .address("Sumy")
                .build()
        );

        new LoginPage(driver)
                .openLoginPage()
                .enterEmail("oleksiihaidabrus@gmail.com")
                .enterPassword("GNM3frcJk8AWJui@")
                .clickSubmit();
    }

    @Test
    void shouldShowUserProfile() {
        // when
        ProfilePage page = new ProfilePage(driver)
                .openProfilePage();

        // then
        assertThat(page.isProfileFormShowed()).isTrue();
        assertThat(page.isFirstNameAndLastNameShowed("Oleksii", "Haidabrus")).isTrue();
    }
}
