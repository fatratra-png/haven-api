package org.haven.havenapi.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnection() throws SQLException {
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        return DriverManager.getConnection(url, username, password);
    }
}
