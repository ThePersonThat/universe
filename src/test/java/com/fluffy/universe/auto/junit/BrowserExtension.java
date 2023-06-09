package com.fluffy.universe.auto.junit;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.ReflectionUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BrowserExtension implements BeforeEachCallback, AfterEachCallback {
    private ChromeDriver driver;
    public static final String URL = "http://localhost:7000/";

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        driver = getChrome();

        extensionContext.getTestClass()
                .flatMap(BrowserExtension::findField)
                .ifPresent(field -> setField(extensionContext, driver, field));
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        driver.quit();
    }

    @SneakyThrows
    private static void setField(ExtensionContext context, ChromeDriver driver, Field field) {
        Object object = context.getTestInstance()
                .orElseThrow(IllegalArgumentException::new);

        field.setAccessible(true);
        field.set(object, driver);
    }

    private static ChromeDriver getChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");

        return new ChromeDriver(options);
    }

    private static Optional<Field> findField(Class<?> clazz) {
        List<Field> fieldList =
                ReflectionUtils.findFields(clazz, BrowserExtension::hasDriverAnnotation, ReflectionUtils.HierarchyTraversalMode.BOTTOM_UP);

        return fieldList.stream().findFirst();
    }

    private static boolean hasDriverAnnotation(Field field) {
        return Objects.nonNull(field.getAnnotation(Driver.class));
    }
}
