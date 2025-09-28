package org.carturo.ministore.database;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("db.properties not found in classpath");
            }
            PROPS.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load DB configuration", e);
        }
    }

    public static Connection openConnection() throws SQLException {
        try {
            Class.forName(PROPS.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Database driver not found: " + e.getMessage());
            throw new SQLException("Driver not found", e);
        }

        String url = PROPS.getProperty("url");
        String user = PROPS.getProperty("user");
        String password = PROPS.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }
}