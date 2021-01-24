package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static String dbUrl = "jdbc:mysql://localhost:3306/ONLINE_SHOP";
    public static String user = "root";
    public static String password = "67yhn)*u7hoasdhj";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(dbUrl, user, password);
    }
}
