package _03_Get_Minion_Names;

import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class GetMinionNames {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    private static final String GET_VILLAIN_ID = "SELECT name FROM villains WHERE id = ?";
    private static final String PRINT_FORMAT_NO_VILLAIN_ID = "No villain with ID %d exists in the database.";
    private static final String GET_NAME = "name";
    private static final String PRINT_FORMAT_VILLAIN_NAME = "Villain: %s%n";
    private static final String GET_MINIONS_NAME_AND_AGE_BY_ID =
            "SELECT m.`name`, m.age " +
                    "FROM minions as m " +
                    "JOIN minions_villains as mv on mv.minion_id=m.id " +
                    "WHERE mv.villain_id = ?;";
    private static final String GET_AGE = "age";
    private static final String PRINT_FORMAT_MINION_NAME_AND_AGE = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        Scanner scanner = new Scanner(System.in);

        PreparedStatement villainsStatement = connection.prepareStatement
                (GET_VILLAIN_ID);

        Integer villainId = Integer.parseInt(scanner.next());
        villainsStatement.setInt(1, villainId);
        ResultSet resultVillains = villainsStatement.executeQuery();

        if (!resultVillains.next()) {
            System.out.printf(PRINT_FORMAT_NO_VILLAIN_ID, villainId);
            connection.close();
            return;
        }

        String dbNameVillains = resultVillains.getString(GET_NAME);
        System.out.printf(PRINT_FORMAT_VILLAIN_NAME, dbNameVillains);

        PreparedStatement minionsStatement = connection.prepareStatement
                (GET_MINIONS_NAME_AND_AGE_BY_ID);

        minionsStatement.setInt(1, villainId);
        ResultSet minionsSet = minionsStatement.executeQuery();

        for (int i = 1; minionsSet.next(); i++) {
            String dbNameMinions = minionsSet.getString(GET_NAME);
            int dbAgeMinions = minionsSet.getInt(GET_AGE);
            System.out.printf(PRINT_FORMAT_MINION_NAME_AND_AGE, i, dbNameMinions, dbAgeMinions);
        }

        connection.close();
    }
}
