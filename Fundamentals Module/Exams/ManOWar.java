import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManOWar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pirateShipInput = scanner.nextLine();
        String[] pirateShipInputSplit = pirateShipInput.split(">");
        List<Integer> pirateShip = new ArrayList<>();
        for (String currentSection : pirateShipInputSplit) {
            int sectionAsInteger = Integer.parseInt(currentSection);
            pirateShip.add(sectionAsInteger);
        }

        String warShipInput = scanner.nextLine();
        String[] warShipInputSplit = warShipInput.split(">");
        List<Integer> warShip = new ArrayList<>();
        for (String currentSection : warShipInputSplit) {
            int sectionAsInteger = Integer.parseInt(currentSection);
            warShip.add(sectionAsInteger);
        }

        int maxHealth = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();
        while (!command.equals("Retire")) {

            String[] commandArguments = command.split(" ");
            String commandName = commandArguments[0];
            switch (commandName) {
                case "Fire":
                    int fireIndex = Integer.parseInt(commandArguments[1]);
                    int damage = Integer.parseInt(commandArguments[2]);
                    if (fireIndex < 0 || fireIndex >= warShip.size()) {
                        break;
                    }
                    int sectionHealth = warShip.get(fireIndex); // warship[fireIndex]
                    sectionHealth -= damage;
                    if (sectionHealth <= 0) {
                        System.out.println("You won! The enemy ship has sunken.");
                        return;
                    } else {
                        warShip.set(fireIndex, sectionHealth); // warship[fireIndex] = sectionHealth
                    }
                    break;
                case "Defend":
                    int startIndex = Integer.parseInt(commandArguments[1]);
                    int endIndex = Integer.parseInt(commandArguments[2]);
                    int damageDealt = Integer.parseInt(commandArguments[3]);
                    if (startIndex < 0 || startIndex >= pirateShip.size()) {
                        break;
                    }
                    if (endIndex < 0 || endIndex >= pirateShip.size()) {
                        break;
                    }

                    for (int i = startIndex; i <= endIndex; i++) {
                        int currentSection = pirateShip.get(i);
                        currentSection -= damageDealt;
                        if (currentSection <= 0) {
                            System.out.println("You lost! The pirate ship has sunken.");
                            return;
                        } else {
                            pirateShip.set(i, currentSection); // pirateShip[i] = currentSection
                        }
                    }

                    break;
                case "Repair":
                    int repairIndex = Integer.parseInt(commandArguments[1]);
                    int repairHealth = Integer.parseInt(commandArguments[2]);
                    if (repairIndex < 0 || repairIndex >= pirateShip.size()) {
                        break;
                    }
                    int sectionToRepair = pirateShip.get(repairIndex); // pirateShip[repairIndex];
                    sectionToRepair += repairHealth;
                    if (sectionToRepair > maxHealth) {
                        sectionToRepair = maxHealth;
                    }
                    pirateShip.set(repairIndex, sectionToRepair); // pirateShip[repairIndex] = sectionToRepair
                    break;
                case "Status":
                    int count = 0;
                    for (Integer currentSection : pirateShip) {
                        if (currentSection < 0.2 * maxHealth) {
                            count++;
                        }
                    }
                    System.out.println(count + " sections need repair.");
                    break;
            }
            command = scanner.nextLine();
        }
        //Тук
        int pirateShipStatus = 0;
        int warShipStatus = 0;

        for (Integer currentSection : warShip) {
            warShipStatus+=currentSection;
        }
        for (Integer currentSection : pirateShip) {
            pirateShipStatus+=currentSection;
        }

        System.out.println("Pirate ship status: " + pirateShipStatus);
        System.out.println("Warship status: " + warShipStatus);
    }
}
