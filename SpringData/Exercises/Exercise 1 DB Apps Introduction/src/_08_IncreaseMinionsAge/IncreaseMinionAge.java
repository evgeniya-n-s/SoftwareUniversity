package _08_IncreaseMinionsAge;

import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncreaseMinionAge {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    private static final String UPDATE_MINIONS_AGE_AND_NAME =
            "UPDATE minions SET age = age +1, `name` = lower(`name`) WHERE id = ?;";
    private static final String SELECT_MINIONS_NAME_AGE = "SELECT `name`, age FROM minions;";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String PRINT_FORMAT_OUTPUT = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Integer> idMinions = Arrays.asList(input.split(" ")).
                stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());

        PreparedStatement updateMinions = connection.prepareStatement
                (UPDATE_MINIONS_AGE_AND_NAME);
        for (int i = 0; i < idMinions.size(); i++) {
            updateMinions.setInt(1, idMinions.get(i));
            updateMinions.executeUpdate();
        }

        PreparedStatement selectMinions = connection.prepareStatement
                (SELECT_MINIONS_NAME_AGE);
        ResultSet resultUpdateTable = selectMinions.executeQuery();

        while (resultUpdateTable.next()) {
            String dbName = resultUpdateTable.getString(NAME);
            int dbAge = resultUpdateTable.getInt(AGE);
            System.out.printf(PRINT_FORMAT_OUTPUT, dbName, dbAge);
        }
        connection.close();
    }
}
