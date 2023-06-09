package com.fluffy.universe.auto;

import com.fluffy.universe.auto.junit.BrowserExtension;
import com.fluffy.universe.auto.junit.DatabaseExtension;
import com.fluffy.universe.auto.junit.Driver;
import com.fluffy.universe.auto.objects.RegistrationPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({DatabaseExtension.class, BrowserExtension.class})
public class RegisterTest {
    @Driver
    private ChromeDriver driver;

    @Test
    void shouldRegisterNewUser() {
        // when
        RegistrationPage page = new RegistrationPage(driver)
                .openRegistrationPage()
                .enterFirstName("Alex")
                .enterLastName("Haidabrus")
                .enterEmail("alexhaidabrus@gmail.com")
                .enterPassword("GNM3frcJk8AWJui@")
                .enterConfirmPassword("GNM3frcJk8AWJui@")
                .clickSubmit();

        // then
        assertThat(page.isAlertShowed()).isTrue();
    }
}
