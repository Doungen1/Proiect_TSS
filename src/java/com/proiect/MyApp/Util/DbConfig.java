package com.proiect.MyApp.Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {
    private static final Properties props = new Properties();
    static {
        try (InputStream in = DbConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (in == null) {
                throw new RuntimeException("application.properties nu a fost găsit în classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Nu pot încărca application.properties din resurse", e);
        }

    }

    public static Connection getConnection() {
        try {
            String url  = props.getProperty("DB_URL");
            String user = props.getProperty("DB_USER");
            String pass = props.getProperty("DB_PASS");
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Conexiune DB eșuată", e);
        }
    }
}
