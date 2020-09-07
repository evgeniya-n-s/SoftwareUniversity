import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TanksCollector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(", ");
        List<String> listPremiumTanks = new ArrayList<>();

        for (String s : input) {
            listPremiumTanks.add(s);
        }

        int numberOfCommand = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfCommand; i++) {
            String[] command = scanner.nextLine().split(", ");
            switch (command[0]) {
                case "Add":
                    if(!(listPremiumTanks.contains(command[1]))){
                        listPremiumTanks.add(command[1]);
                        System.out.println("Tank successfully bought");
                    } else {
                        System.out.println("Tank is already bought");
                    }
                    break;
                case "Remove":
                    if(listPremiumTanks.contains(command[1])){
                        listPremiumTanks.remove(command[1]);
                        System.out.println("Tank successfully sold");
                    } else {
                        System.out.println("Tank not found");
                    }
                    break;
                case "Remove At":
                    int index = Integer.parseInt(command[1]);
                    if(index>=0 && index<listPremiumTanks.size()){
                        listPremiumTanks.remove(index);
                        System.out.println("Tank successfully sold");
                    } else {
                        System.out.println("Index out of range");
                    }
                    break;
                case "Insert":
                    int foundIndex = Integer.parseInt(command[1]);
                    if(foundIndex>=0 && foundIndex<listPremiumTanks.size()){
                        if(listPremiumTanks.contains(command[2])){
                            System.out.println("Tank is already bought");
                        } else {
                            listPremiumTanks.add(foundIndex,command[2]);
                            System.out.println("Tank successfully bought");
                        }
                    } else {
                        System.out.println("Index out of range");
                    }
                    break;

            }
        }
        for (int i = 0; i <listPremiumTanks.size(); i++) {
            if(i==0){
                System.out.print(listPremiumTanks.get(i));
            } else {
                System.out.print(", " + listPremiumTanks.get(i));
            }
        }
    }
}
