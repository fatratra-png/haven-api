package org.haven.havenapi.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Component
public class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static Connection getConnection() throws SQLException {
        String url = getEnv("DB_URL");
        String username = getEnv("DB_USERNAME");
        String password = getEnv("DB_PASSWORD");
        return DriverManager.getConnection(url, username, password);
    }

    public static String getEnv(String key) {
        String value = System.getenv(key);
        return value != null ? value : dotenv.get(key);
    }
}
