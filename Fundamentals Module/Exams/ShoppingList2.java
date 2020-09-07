import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingList2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] inputShoppingList = input.split("!");
        List<String> shoppingList = new ArrayList<>();

        for (String s : inputShoppingList) {
            shoppingList.add(s);
        }

        String command = scanner.nextLine();
        while (!"Go Shopping!".equals(command)) {
            String[] tokens = command.split(" ");
            switch (tokens[0]) {
                case "Urgent":
                    boolean isInTheList = false;
                    for (int i = 0; i <shoppingList.size() ; i++) {
                        if(tokens[1].equals(shoppingList.get(i))){
                            isInTheList = true;
                        }
                    }
                    if(!isInTheList){
                        shoppingList.add(0,tokens[1]);
                    }
                    break;
                case "Unnecessary":
                    boolean isUnnecessary = false;
                    for (int i = 0; i <shoppingList.size() ; i++) {
                        if(tokens[1].equals(shoppingList.get(i))){
                            isUnnecessary =true;
                        }
                    }
                    if(isUnnecessary){
                        shoppingList.remove(tokens[1]);
                    }
                    break;
                case "Correct":
                    boolean isCorrected = false;
                    int getIndex = 0;
                    for (int i = 0; i <shoppingList.size() ; i++) {
                        if(tokens[1].equals(shoppingList.get(i))){
                            isCorrected =true;
                            getIndex = i;
                        }
                    }
                    if (isCorrected){
                        shoppingList.remove(tokens[1]);
                        shoppingList.add(getIndex,tokens[2]);
                    }
                    break;
                case "Rearrange":
                    boolean isRearrange = false;
                    String getTheName = "";
                    for (int i = 0; i <shoppingList.size() ; i++) {
                        if(tokens[1].equals(shoppingList.get(i))){
                            isRearrange = true;
                            getTheName = tokens[1];
                        }
                    }
                    if(isRearrange){
                        shoppingList.remove(tokens[1]);
                        shoppingList.add(getTheName);
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        for (int i = 0; i <shoppingList.size() ; i++) {
            if(i==0){
                System.out.print(shoppingList.get(i));
            }else {
                System.out.print(", " + shoppingList.get(i));
            }
        }
    }
}
