import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FriendlistMaintenance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");
        List<String> listName = new ArrayList<>();

        for (String s : input) {
            listName.add(s);
        }

        String command = scanner.nextLine();
        int countBlacklisted = 0;
        int countLostNames = 0;
        while (!"Report".equals(command)) {
            String[] tokens = command.split(" ");

            switch (tokens[0]) {
                case "Blacklist":
                    boolean isFoundName = false;
                    int foundIndex = 0;
                    for (int i = 0; i <listName.size() ; i++) {
                        if(tokens[1].equals(listName.get(i))){
                            isFoundName=true;
                            foundIndex = i;
                        }
                    }
                    if(isFoundName){
                        listName.remove(foundIndex);
                        listName.add(foundIndex,"Blacklisted");
                        System.out.printf("%s was blacklisted.",tokens[1]);
                        System.out.println();
                        countBlacklisted++;
                    } else {
                        System.out.printf("%s was not found.",tokens[1]);
                        System.out.println();
                    }
                    break;
                case "Error":
                    int index = Integer.parseInt(tokens[1]);
                    String name = listName.get(index);
                    if(("Blacklisted".equals(name)) || ("Lost".equals(name))){
                        break;
                    } else {
                        listName.set(index,"Lost");
                        System.out.printf("%s was lost due to an error.",name);
                        System.out.println();
                        countLostNames++;
                    }
                    break;
                case "Change":
                    int givenIndex = Integer.parseInt(tokens[1]);
                    if (givenIndex<=listName.size()-1){
                        String currentName = listName.get(givenIndex);
                        listName.set(givenIndex,tokens[2]);
                        System.out.printf("%s changed his username to %s. ",currentName,tokens[2]);
                        System.out.println();
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.printf("Blacklisted names: %d ",countBlacklisted);
        System.out.println();
        System.out.printf("Lost names: %d ",countLostNames);
        System.out.println();

        for (String s : listName) {
            System.out.print(s + " ");
        }
    }
}
