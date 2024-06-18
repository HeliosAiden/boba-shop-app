package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String dataBaseURL = "jdbc:mysql://localhost:3306/boba_shop_db";
    static final String username = "root";
    static final String password = "secret-password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseURL, username, password);
    }
    
}
