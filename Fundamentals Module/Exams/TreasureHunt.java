import java.util.*;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String treasureChestContent = scanner.nextLine();
        //Gold|Silver|Bronze|Medallion|Cup
        String[] splitChest = treasureChestContent.split("\\|");
        List<String> chestContentList = new ArrayList<>(
                Arrays.asList(splitChest)
        );

        String command = scanner.nextLine();
        while (!command.equals("Yohoho!")) {
            String[] commandsArray = command.split(" ");
            String currentCommand = commandsArray[0];
            switch (currentCommand) {
                case "Loot":
                    for (int i = 1; i < commandsArray.length; i++) {
                        if (!chestContentList.contains(commandsArray[i])) {
                            chestContentList.add(0, commandsArray[i]);
                        }
                    }
                    break;
                case "Drop":
                    int dropIndex = Integer.parseInt(commandsArray[1]);
                    if (dropIndex < 0 || dropIndex >= chestContentList.size()) {
                        break;
                    }
                    chestContentList.add(chestContentList.remove(dropIndex));
                    break;
                case "Steal":
                    int stealAmount = Integer.parseInt(commandsArray[1]);
                    List<String> stolen = new ArrayList<>();
                    String output = "";
                    if (stealAmount >= chestContentList.size()) {
                        output = String.join(", ", chestContentList);
                        System.out.println(output);
                        chestContentList.clear();
                        break;
                    }
                    for (int i = 0; i < stealAmount; i++) {
                        int lastIndex = chestContentList.size() - 1;
                        stolen.add(chestContentList.remove(lastIndex));
                    }
                    Collections.reverse(stolen);
                    output = String.join(", ", stolen);
                    System.out.println(output);
                    break;
            }
            command = scanner.nextLine();
        }

        int totalSizeofLoot = 0;
        for (String currentLoot : chestContentList) {
            totalSizeofLoot += currentLoot.length();
        }
        if (totalSizeofLoot == 0) {
            System.out.println("Failed treasure hunt.");
        } else {
            double average = 1.0 * totalSizeofLoot / chestContentList.size();
            System.out.printf("Average treasure gain: %.2f pirate credits.", average);
        }
    }
}
