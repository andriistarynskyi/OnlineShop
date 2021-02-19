package DbUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    public static Connection getConnection() throws IOException, SQLException {
        Connection conn;
        Properties props = new Properties();

        try (BufferedReader in = Files.newBufferedReader(Path.of("app.properties"),
                Charset.forName("UTF-8"))) {
            props.load(in);
        }
        String dbUrl = props.getProperty("urlAddress");
        String dbUserName = props.getProperty("userName");
        String dbPassword = props.getProperty("password");

        conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return conn;
    }
}