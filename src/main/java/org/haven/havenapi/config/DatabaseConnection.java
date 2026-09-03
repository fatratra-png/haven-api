package org.haven.havenapi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class DatabaseConnection {

    private final Dotenv dotenv = Dotenv.load();

    public Connection getConnection() throws SQLException {
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        return DriverManager.getConnection(url, username, password);
    }
}
