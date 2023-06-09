package com.fluffy.universe.auto.junit;

import com.fluffy.universe.utils.Configuration;
import com.fluffy.universe.utils.DataSource;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.extension.*;
import org.sql2o.Connection;

import java.io.File;

public class DatabaseExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback {
    private Connection connection;

    static {
        Configuration.load(new File("application.properties"));
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        connection = DataSource.getConnection();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        DatabaseUtils.clear(connection);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        connection.close();
    }

    @UtilityClass
    private static class DatabaseUtils {
        public void clear(Connection connection) {
            connection.createQuery("DELETE FROM User WHERE 1 = 1").executeUpdate();
            connection.createQuery("DELETE FROM Post WHERE 1 = 1").executeUpdate();
            connection.createQuery("DELETE FROM Comment WHERE 1 = 1").executeUpdate();
        }
    }
}
