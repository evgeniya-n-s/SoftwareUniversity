package _05_ChangeTownNamesCasing;

import _01_Connection.ConnectionSQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownName {
    //Before you run the program import the Libraries: mysql-connector-java-8.0.27
    private static final String COUNT_CITY_BY_GIVEN_COUNTRY =
            "SELECT COUNT(t.country) as count_city " +
                    "from towns as t " +
                    "WHERE t.country LIKE ? " +
                    "GROUP BY t.country;";
    private static final String COUNT_CITY = "count_city";
    private static final String PRINT_FORMAT_COUNT_CITY = "%d town names were affected.%n";
    private static final String PRINT_FORMAT_NONE_CITY = "No town names were affected.";
    private static final String UPDATE_TOWNS_NAMES = "UPDATE towns SET `name`=upper(`name`) WHERE country LIKE ?";
    private static final String QUERY_TOWNS_NAMES_BY_GIVEN_COUNTRY = "SELECT name FROM towns WHERE country = ?";
    private static final String NAME = "name";

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionSQL.getConnection();

        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        PreparedStatement countCityInCountry = connection.prepareStatement
                (COUNT_CITY_BY_GIVEN_COUNTRY);
        countCityInCountry.setString(1, country);
        ResultSet resultSet = countCityInCountry.executeQuery();

        if (resultSet.next()) {
            int dbCountCity = resultSet.getInt(COUNT_CITY);
            System.out.printf(PRINT_FORMAT_COUNT_CITY, dbCountCity);
        } else {
            System.out.println(PRINT_FORMAT_NONE_CITY);
            return;
        }

        PreparedStatement updateTownNames = connection.prepareStatement
                (UPDATE_TOWNS_NAMES);
        updateTownNames.setString(1, country);
        updateTownNames.executeUpdate();

        PreparedStatement selectAllTowns = connection.prepareStatement
                (QUERY_TOWNS_NAMES_BY_GIVEN_COUNTRY);
        selectAllTowns.setString(1, country);
        ResultSet townsSet = selectAllTowns.executeQuery();

        List<String> towns = new ArrayList<>();

        while (townsSet.next()) {
            String townName = townsSet.getString(NAME);
            towns.add(townName);
        }

        System.out.println(towns);

        connection.close();

    }
}
