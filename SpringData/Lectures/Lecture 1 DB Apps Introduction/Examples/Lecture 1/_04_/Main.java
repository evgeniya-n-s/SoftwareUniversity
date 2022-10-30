package _04_;

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

        System.out.print("Enter password default (empty): ");
        String password = scanner.next().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user",user);
        props.setProperty("password",password);

        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/diablo",props);

        PreparedStatement stmt = connection.prepareStatement(
                "SELECT u.user_name,u.first_name,u.last_name, count(ug.id) as 'count_game' " +
                "FROM users as u " +
                "JOIN users_games as ug on ug.user_id=u.id " +
                "WHERE u.user_name=?;");

        String userName = scanner.next();
        stmt.setString(1, userName);

        ResultSet result = stmt.executeQuery();

        if(!result.next()){
            String dbUserName = result.getString("user_name");
            String dbFirstName = result.getString("first_name");
            String dbLastName = result.getString("last_name");
            int dbCount = result.getInt("count_game");
            System.out.printf("User: %s%n%s %s has played %d games",dbUserName,dbFirstName,dbLastName,dbCount);
        }else {
            System.out.println("No such user exists");
        }
        connection.close();
    }
}
