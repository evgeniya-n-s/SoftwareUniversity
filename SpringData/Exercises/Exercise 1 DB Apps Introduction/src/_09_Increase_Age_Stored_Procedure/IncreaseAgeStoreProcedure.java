package _09_Increase_Age_Stored_Procedure;


import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoreProcedure {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    //Before you run the program create Procedure in Workbench:
    //DELIMITER $$
    //CREATE PROCEDURE usp_get_older(id_minion INT)
    //BEGIN
    //UPDATE minions
    //SET age = age +1
    //WHERE id = id_minion;
    //END $$
    //DELIMITER ;
    private static final String VALID_ID_MINIONS = "SELECT * FROM minions WHERE id=?";
    private static final String PRINT_FORMAT_NONE_ID = "No such minion ID was found";
    private static final String CALL_PROCEDURE = "CALL usp_get_older(?);";
    private static final String SELECT_MINIONS_NAME_AGE = "SELECT `name`, age FROM minions WHERE id = ?;";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String PRINT_FORMAT_OUTPUT = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        Scanner scanner = new Scanner(System.in);
        int inputId = Integer.parseInt(scanner.next());

        PreparedStatement checkIdMinions = connection.prepareStatement
                (VALID_ID_MINIONS);
        checkIdMinions.setInt(1, inputId);
        ResultSet minionSet = checkIdMinions.executeQuery();

        if (!minionSet.next()) {
            System.out.println(PRINT_FORMAT_NONE_ID);
            return;
        }

        PreparedStatement callProcedure = connection.prepareCall
                (CALL_PROCEDURE);
        callProcedure.setInt(1, inputId);
        callProcedure.executeUpdate();

        PreparedStatement selectMinions = connection.prepareStatement
                (SELECT_MINIONS_NAME_AGE);
        selectMinions.setInt(1,inputId);
        ResultSet resultUpdateTable = selectMinions.executeQuery();

        while (resultUpdateTable.next()) {
            String dbName = resultUpdateTable.getString(NAME);
            int dbAge = resultUpdateTable.getInt(AGE);
            System.out.printf(PRINT_FORMAT_OUTPUT, dbName, dbAge);
        }
        connection.close();

    }
}
