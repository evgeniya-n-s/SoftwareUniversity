import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeaponSmith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\|");
        List<String> nameAndWeapon = new ArrayList<>();

        for (String s : input) {
            nameAndWeapon.add(s);
        }

        String command = scanner.nextLine();

        while (!"Done".equals(command)) {
            String[] tokens = command.split(" ");
            if (tokens[0].equals("Move") && tokens[1].equals("Left")) {
                int indexLeft = Integer.parseInt(tokens[2]);
                if (indexLeft > 0 && indexLeft <= nameAndWeapon.size()) {
                    String tempFirst = nameAndWeapon.get(indexLeft);
                    String tempSecond = nameAndWeapon.get(indexLeft + 1);
                    nameAndWeapon.set(indexLeft, tempSecond);
                    nameAndWeapon.set(indexLeft + 1, tempFirst);
                }
            }
            if (tokens[0].equals("Move") && tokens[1].equals("Right")) {
                int indexRight = Integer.parseInt(tokens[2]);
                if (indexRight >= 0 && indexRight < nameAndWeapon.size()) {
                    String tempFirst = nameAndWeapon.get(indexRight);
                    String tempSecond = nameAndWeapon.get(indexRight + 1);
                    nameAndWeapon.set(indexRight, tempSecond);
                    nameAndWeapon.set(indexRight + 1, tempFirst);
                }
            }
            if (tokens[0].equals("Check") && tokens[1].equals("Even")) {
                for (int i = 0; i < nameAndWeapon.size(); i++) {
                    if (i %2 == 0) {
                        System.out.println(nameAndWeapon.get(i) + " ");
                    }
                }
            }
            if (tokens[0].equals("Check") && tokens[1].equals("Odd")) {
                for (int i = 0; i <nameAndWeapon.size() ; i++) {
                    if(i %2 !=0){
                        System.out.print(nameAndWeapon.get(i) + " ");
                    }
                }
            }
            command = scanner.nextLine();
        }
        System.out.print("You crafted ");
        for (int i = 0; i <nameAndWeapon.size() ; i++) {
            System.out.print(nameAndWeapon.get(i));
        }
    }
}