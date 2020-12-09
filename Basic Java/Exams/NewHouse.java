import java.util.Scanner;

public class NewHouse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String typeFlowers = scanner.nextLine();
        int countFlowers = Integer.parseInt(scanner.nextLine());
        int budget = Integer.parseInt(scanner.nextLine());

        double currentCount = 0.0;


        if (typeFlowers.equals("Roses")) {
            if (countFlowers > 80) {
                currentCount = (countFlowers * 5) * 0.9;
            } else{
                currentCount = countFlowers * 5;
            }
        }

        if (typeFlowers.equals("Dahlias")) {
            if (countFlowers > 90) {
                currentCount = (countFlowers * 3.80) * 0.85;
            } else{
                currentCount = countFlowers * 3.80;
            }
        }

        if (typeFlowers.equals("Tulips")) {
            if (countFlowers > 80) {
                currentCount = (countFlowers * 2.80) * 0.85;
            } else{
                currentCount = countFlowers * 2.80;
            }
        }

        if (typeFlowers.equals("Narcissus")) {
            if (countFlowers < 120) {
                currentCount = (countFlowers * 3);
                currentCount = currentCount  + (currentCount * 0.15);
            } else{
                currentCount = countFlowers * 3;
            }
        }

        if (typeFlowers.equals("Gladiolus")) {
            if (countFlowers < 80) {
                currentCount = (countFlowers * 2.50);
                currentCount = currentCount + (currentCount * 0.2);
            } else {
                currentCount = countFlowers * 2.50;
            }
        }

        if (budget >= currentCount){
            double totalLeft = budget - currentCount;
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", countFlowers, typeFlowers, totalLeft);
        } else{
            double totalNeed = currentCount - budget;
            System.out.printf("Not enough money, you need %.2f leva more.", totalNeed);
        }
    }
}
