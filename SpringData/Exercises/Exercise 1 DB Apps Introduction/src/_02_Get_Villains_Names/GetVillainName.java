package _02_Get_Villains_Names;

import _01_Connection.ConnectionSQL;

import java.sql.*;

public class GetVillainName {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    private static final String GET_VILLAIN_NAME =
            "SELECT v.`name`, count(DISTINCT mv.minion_id) as count_minions " +
                    "FROM villains as v " +
                    "JOIN minions_villains as mv on mv.villain_id=v.id " +
                    "GROUP BY mv.villain_id " +
                    "HAVING count_minions > 15 " +
                    "ORDER BY count_minions DESC;";
    private static final String VILLAIN_NAME = "name";
    private static final String COUNT_MINIONS = "count_minions";
    private static final String PRINT_FORMAT = "%s %d";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        PreparedStatement getVillainName = connection.prepareStatement
                (GET_VILLAIN_NAME);

        ResultSet result = getVillainName.executeQuery();

        while (result.next()) {
            String dbVillainName = result.getString(VILLAIN_NAME);
            int dbCountMinions = result.getInt(COUNT_MINIONS);
            System.out.printf(PRINT_FORMAT, dbVillainName, dbCountMinions);
        }
        connection.close();
    }
}
