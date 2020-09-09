import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Pirates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> populationMap = new LinkedHashMap<>();
        Map<String, Integer> goldMap = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!"Sail".equals(input)) {
            String[] token = input.split("\\|\\|");
            String nameCity = token[0];
            int population = Integer.parseInt(token[1]);
            int gold = Integer.parseInt(token[2]);

            populationMap.putIfAbsent(nameCity, population);
            goldMap.putIfAbsent(nameCity, gold);
            populationMap.put(nameCity,population);
            goldMap.put(nameCity,gold);

            input = scanner.nextLine();
        }
        String command = scanner.nextLine();
        while (!"End".equals(command)) {
            String[] tokens = command.split("=>");
            String events = tokens[0];
            String town = tokens[1];

            switch (events) {
                case "Plunder":
                    int people = Integer.parseInt(tokens[2]);
                    int gold = Integer.parseInt(tokens[3]);
                    populationMap.put(town, populationMap.get(town) - people);
                    goldMap.put(town, goldMap.get(town) - gold);
                    System.out.println(String.format("%s plundered! %d gold stolen, %d citizens killed.", town, gold, people));
                    if ((populationMap.get(town) <= 0) || (goldMap.get(town) <= 0)) {
                        populationMap.remove(town);
                        goldMap.remove(town);
                        System.out.println(String.format("%s has been wiped off the map!", town));
                    }
                    break;
                case "Prosper":
                    int goldProsperCommand = Integer.parseInt(tokens[2]);
                    if (goldProsperCommand < 0) {
                        System.out.println(String.format("Gold added cannot be a negative number!"));
                    } else {
                        int totalGold = goldMap.get(town) + goldProsperCommand;
                        goldMap.put(town, totalGold);
                        System.out.println(String.format("%d gold added to the city treasury. %s now has %d gold.", goldProsperCommand, town, totalGold));
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        if (goldMap.isEmpty()) {
            System.out.println(String.format("Ahoy, Captain! All targets have been plundered and destroyed!"));
        } else {
            System.out.println(String.format("Ahoy, Captain! There are %d wealthy settlements to go to:", goldMap
                    .entrySet()
                    .size()));

            goldMap
                    .entrySet()
                    .stream()
                    .sorted((x1, x2) -> {
                        int result = x2.getValue().compareTo(x1.getValue());
                        if (result == 0) {
                            result = x1.getKey().compareTo(x2.getKey());
                        }
                        return result;
                    })
                    .forEach(e -> System.out.println(String.format("%s -> Population: %d citizens, Gold: %d kg", e.getKey(), populationMap.get(e.getKey()), e.getValue())));
        }
    }
}
