package _07_Print_All_Minion_Names;

import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.*;

public class PrintAllMinionName {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    //Before you run the program you need to reload your database
    private static final String SELECT_MINIONS_NAME = "SELECT `name` FROM minions;";
    private static final String NAME = "name";
    private static final String PRINT_FORMAT_NAME = "%s%n";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        PreparedStatement selectMinionsName = connection.prepareStatement
                (SELECT_MINIONS_NAME);
        ResultSet resultSet = selectMinionsName.executeQuery();
        ArrayDeque<String> minionsName = new ArrayDeque<>();

        while (resultSet.next()) {
            minionsName.offer(resultSet.getString(NAME));
        }

        int minionSize = minionsName.size();

        for (int i = 0; i < minionSize / 2; i++) {
            String upToDownNames = minionsName.pollFirst();
            String downToUpNames = minionsName.pollLast();
            System.out.printf(PRINT_FORMAT_NAME, upToDownNames);
            System.out.printf(PRINT_FORMAT_NAME, downToUpNames);
        }

        connection.close();

    }
}
