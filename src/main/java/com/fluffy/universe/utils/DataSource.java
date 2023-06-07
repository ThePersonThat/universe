package com.fluffy.universe.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.SQLException;

public final class DataSource {
    private static HikariConfig dataSourceConfiguration = new HikariConfig();
    private static Sql2o dataSource;
    private static boolean init = false;

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        if (!init) {
            dataSourceConfiguration.setJdbcUrl(String.format("jdbc:sqlite:/%s", Configuration.get("database.filename")));
            dataSource = new Sql2o(new HikariDataSource(dataSourceConfiguration));
            init = true;
        }

        return dataSource.open();
    }
}
