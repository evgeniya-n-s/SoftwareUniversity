package _06_Remove_Villain;

import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class RemoveVillain {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    private static final String VILLAINS_NAME_BY_GIVEN_ID = "SELECT `name` FROM villains WHERE id = ?;";
    private static final String PRINT_FORMAT_NONE_ID = "No such villain was found";
    private static final String NAME = "name";
    private static final String COUNT_MINION_ID_BY_GIVEN_VILLAIN_ID =
            "SELECT COUNT(minion_id) as m_count " +
                    "FROM minions_villains WHERE villain_id = ?";
    private static final String MINION_COUNT = "m_count";
    private static final String DELETE_MINIONS_VILLAINS_BY_GIVEN_VILLAIN_ID = "DELETE FROM minions_villains WHERE villain_id = ?";
    private static final String DELETE_VILLAINS_BY_GIVEN_ID = "DELETE FROM villains WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        Scanner scanner = new Scanner(System.in);
        int idVillain = Integer.parseInt(scanner.nextLine());

        PreparedStatement selectVillainName = connection.prepareStatement
                (VILLAINS_NAME_BY_GIVEN_ID);
        selectVillainName.setInt(1, idVillain);
        ResultSet villainSet = selectVillainName.executeQuery();

        if (!villainSet.next()) {
            System.out.println(PRINT_FORMAT_NONE_ID);
            return;
        }
        String villainName = villainSet.getString(NAME);

        PreparedStatement selectAllVillainMinions = connection.prepareStatement
                (COUNT_MINION_ID_BY_GIVEN_VILLAIN_ID);
        selectAllVillainMinions.setInt(1, idVillain);
        ResultSet minionsCountSet = selectAllVillainMinions.executeQuery();
        minionsCountSet.next();
        int countMinionsDeleted = minionsCountSet.getInt(MINION_COUNT);
        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement
                    (DELETE_MINIONS_VILLAINS_BY_GIVEN_VILLAIN_ID);
            deleteMinionsVillains.setInt(1, idVillain);
            deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement
                    (DELETE_VILLAINS_BY_GIVEN_ID);
            deleteVillain.setInt(1, idVillain);
            deleteVillain.executeUpdate();

            connection.commit();

            System.out.println(villainName + " was deleted");
            System.out.println(countMinionsDeleted + " minions released");
        } catch (SQLException e) {
            e.printStackTrace();

            connection.rollback();
        }

        connection.close();
    }
}
