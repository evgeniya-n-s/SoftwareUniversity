import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");
        List<String> listCollection = new ArrayList<>();

        for (String s : input) {
            listCollection.add(s);
        }

        String command = scanner.nextLine();
        while (!"Craft!".equals(command)) {
            String[] tokens = command.split(" - ");
            switch (tokens[0]) {
                case "Collect":
                    boolean isInCollection = false;
                    for (int i = 0; i < listCollection.size(); i++) {
                        if (tokens[1].equals(listCollection.get(i))) {
                            isInCollection = true;
                        }
                    }
                    if (!isInCollection) {
                        listCollection.add(tokens[1]);
                    }
                    break;
                case "Drop":
                    for (int i = 0; i < listCollection.size(); i++) {
                        if (tokens[1].equals(listCollection.get(i))) {
                            listCollection.remove(i);
                        }
                    }
                    break;
                case "Combine Items":
                    String commandSplitAgain = tokens[1];
                    String[] combineItem = commandSplitAgain.split(":");
                    for (int i = 0; i <listCollection.size() ; i++) {
                        if(combineItem[0].equals(listCollection.get(i))){
                            listCollection.add(i+1,combineItem[1]);
                        }
                    }
                    break;
                case "Renew":
                    for (int i = 0; i <listCollection.size() ; i++) {
                        if(tokens[1].equals(listCollection.get(i))){
                            listCollection.remove(i);
                            listCollection.add(tokens[1]);
                        }
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        for (int i = 0; i <listCollection.size() ; i++) {
            if(i==0){
                System.out.print(listCollection.get(i));
            } else {
                System.out.print(", "+ listCollection.get(i));
            }
        }
    }
}
