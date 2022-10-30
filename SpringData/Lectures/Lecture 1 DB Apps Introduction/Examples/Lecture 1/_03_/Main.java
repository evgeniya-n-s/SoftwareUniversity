package _03_;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = scanner.next();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = scanner.next().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt = connection.prepareStatement
                ("SELECT first_name,last_name,job_title FROM employees WHERE salary >?");

        String salary = scanner.next();
        stmt.setDouble(1,Double.parseDouble(salary));
        ResultSet result = stmt.executeQuery();

        while (result.next()){
            System.out.println(result.getString("first_name") + " " + result.getString("last_name")
            +": "+ result.getString("job_title"));
        }
        connection.close();
    }
}
