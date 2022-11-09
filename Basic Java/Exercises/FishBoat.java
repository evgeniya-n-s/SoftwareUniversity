import java.util.Scanner;

public class FishBoat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine();
        int countPersons = Integer.parseInt(scanner.nextLine());

        double count = 0.0;

        if (season.equals("Spring")) {
            if (countPersons <= 6) {
                count = 3000 * 0.9;
            } else if (countPersons > 7 && countPersons <= 11) {
                count = 3000 * 0.85;
            } else if (countPersons > 12) {
                count = 3000 * 0.75;
            } if (countPersons %2 == 0) {
                count = count * 0.95;
            }
        }

        if (season.equals("Summer")) {
            if (countPersons <= 6) {
                count = 4200 * 0.9;
            } else if (countPersons > 7 && countPersons <= 11) {
                count = 4200 * 0.85;
            } else if (countPersons > 12) {
                count = 4200 * 0.75;
            } if (countPersons %2 == 0) {
                count = count * 0.95;
            }
        }

        if (season.equals("Autumn")) {
            if (countPersons <= 6) {
                count = 4200 * 0.9;
            } else if (countPersons > 7 && countPersons <= 11) {
                count = 4200 * 0.85;
            } else if (countPersons > 12) {
                count = 4200 * 0.75;
            }
        }

        if (season.equals("Winter")) {
            if (countPersons <= 6) {
                count = 2600 * 0.9;
            } else if (countPersons > 7 && countPersons <= 11) {
                count = 2600 * 0.85;
            } else if (countPersons > 12) {
                count = 2600 * 0.75;
            } if (countPersons %2 == 0) {
                count = count * 0.95;
            }
        }

        if (budget >= count){
            double totalLeft = budget - count;
            System.out.printf("Yes! You have %.2f leva left.", totalLeft);
        } else {
            double totalNeed = count - budget;
            System.out.printf("Not enough money! You need %.2f leva.", totalNeed);
        }
    }
}
