import java.util.Scanner;

public class EasterBakery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double priceFlourKg = Double.parseDouble(scanner.nextLine());
        double kgFlour = Double.parseDouble(scanner.nextLine());
        double kgSugar = Double.parseDouble(scanner.nextLine());
        int numberBarkEggs = Integer.parseInt(scanner.nextLine());
        int yeast = Integer.parseInt(scanner.nextLine());

        double priceSugar = priceFlourKg * 0.75;
        double priceEggs = priceFlourKg * 1.1;
        double priceYeast = priceSugar * 0.2;
        double sumFlour = priceFlourKg * kgFlour;
        double sumSugar = priceSugar * kgSugar;
        double sumEggs = priceEggs * numberBarkEggs;
        double sumYeast = priceYeast * yeast;

        double totalSum = sumFlour + sumSugar + sumEggs + sumYeast;

        System.out.printf("%.2f", totalSum);
    }
}
