package com.javacl.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static final String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";
    private static final String username = "RM553912";
    private static final String password = "141204";

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                createConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao conectar ao banco de dados.", ex);
        }
    }
}
