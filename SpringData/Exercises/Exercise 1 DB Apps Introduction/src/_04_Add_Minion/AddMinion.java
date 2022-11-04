package _04_Add_Minion;

import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.Scanner;

public class AddMinion {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    private static final String INSERT_INTO_MINIONS_AGE_NAME_TOWN_ID = "Insert into minions(age,name,town_id) values (?, ?, ?)";
    private static final String SELECT_MINIONS_BY_ID_DESC = "select id from minions order by id DESC";
    private static final String ID = "id";
    private static final String INSERT_INTO_MINIONS_VILLAINS = "Insert into minions_villains values(?,?)";
    private static final String PRINT_FORMAT_SUCCESSFULLY_ADDED = "Successfully added %s to be minion of %s.";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        Scanner scanner = new Scanner(System.in);
        String[] minionInfo = scanner.nextLine().trim().split("\\s+");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villainsName = scanner.nextLine().split("\\s+")[1];

        int townID = getOrInsertID(connection, minionTown);

        int villainId = getOrInsertVillain(connection, villainsName);

        PreparedStatement insertMinions = connection.prepareStatement
                (INSERT_INTO_MINIONS_AGE_NAME_TOWN_ID);
        insertMinions.setInt(1, minionAge);
        insertMinions.setString(2, minionName);
        insertMinions.setInt(3, townID);
        insertMinions.executeUpdate();

        PreparedStatement getLastMinion = connection.prepareStatement
                (SELECT_MINIONS_BY_ID_DESC);
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt(ID);

        PreparedStatement insertMinionsVillains = connection.prepareStatement
                (INSERT_INTO_MINIONS_VILLAINS);
        insertMinionsVillains.setInt(1, lastMinionId);
        insertMinionsVillains.setInt(2, villainId);
        insertMinionsVillains.executeUpdate();


        System.out.printf(PRINT_FORMAT_SUCCESSFULLY_ADDED, minionName, villainsName);

        connection.close();

    }

    private static int getOrInsertVillain(Connection connection, String villainsName) throws SQLException {
        PreparedStatement villainsStatement = connection.prepareStatement
                ("select id from villains where name = ?");
        villainsStatement.setString(1, villainsName);
        ResultSet resultVillains = villainsStatement.executeQuery();

        int villainId = 0;
        if (!resultVillains.next()) {
            PreparedStatement insertVillains = connection.prepareStatement
                    ("Insert into villains(evilness_factor, name) values(?, ?)");
            insertVillains.setString(1, "evil");
            insertVillains.setString(2, villainsName);
            insertVillains.executeUpdate();
            ResultSet newVillainsName = villainsStatement.executeQuery();
            newVillainsName.next();
            villainId = newVillainsName.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainsName);
        } else {
            villainId = resultVillains.getInt("id");

        }
        return villainId;
    }

    private static int getOrInsertID(Connection connection, String minionTown) throws SQLException {
        PreparedStatement townStatement = connection.prepareStatement
                ("select id from towns where name = ?");

        townStatement.setString(1, minionTown);
        ResultSet resultTown = townStatement.executeQuery();

        int townId = 0;
        if (!resultTown.next()) {
            PreparedStatement insertTown = connection.prepareStatement
                    ("Insert into towns(name) value(?)");
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();
            ResultSet newTownSet = townStatement.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = resultTown.getInt("id");

        }
        return townId;
    }
}
