import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeartDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputNeighbour = scanner.nextLine().split("@");
        List<Integer> houses = new ArrayList<>();

        for (String house : inputNeighbour) {
            houses.add(Integer.parseInt(house));
        }

        int temp = 0;
        String command = scanner.nextLine();
        while (!"Love!".equals(command)) {
            String[] tokens = command.split(" ");
            int jump = Integer.parseInt(tokens[1]);

            temp = temp + jump;

            if (temp >= houses.size()) {
                temp = 0;
            }
            int currentJump = houses.get(temp);

            if ((currentJump == 0)) {
                System.out.printf("Place %d already had Valentine's day.", temp);
                System.out.println();
            } else {
                if (temp <= houses.size()) {
                    for (int i = 0; i < houses.size(); i++) {
                        if (i == temp) {
                            currentJump = houses.get(temp) - 2;
                            houses.set(temp, currentJump);
                            if (currentJump == 0) {
                                System.out.printf("Place %d has Valentine's day.", temp);
                                System.out.println();
                            }
                        }
                    }

                }
            }
            command = scanner.nextLine();
        }
        System.out.printf("Cupid's last position was %d.", temp);
        System.out.println();
        int countArray = -1;
        int countHouse = 0;
        for (int i = 0; i < houses.size(); i++) {
            if (houses.get(i) == 0) {
                countArray++;
            } else {
                countHouse++;
            }
        }
        if (countArray == houses.size()) {
            System.out.println("Mission was successful.");
            System.out.println();
        } else {
            System.out.printf("Cupid has failed %d places.", countHouse);
        }
    }
}