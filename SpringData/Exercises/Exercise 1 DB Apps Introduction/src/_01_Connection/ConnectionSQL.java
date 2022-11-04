package _01_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSQL {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    //Before you run the program check: user, password and localhost with your parameters in database
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "");

        return DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/minions_db", properties);
    }
}
