import java.util.Scanner;

public class BachelorParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double priceSinger = Double.parseDouble(scanner.nextLine());
        String stop = scanner.nextLine();

        double countPriceGroup = 0.0;
        double countTotalPrice = 0.0;
        int countGuest = 0;

        while (!stop.equals("The restaurant is full")) {
            int convertNumber = Integer.parseInt(stop);

            if (convertNumber >= 5) {
                countPriceGroup = convertNumber * 70;
                countTotalPrice = countPriceGroup + countTotalPrice;
                countGuest = convertNumber + countGuest;
            } else {
                countPriceGroup = convertNumber * 100;
                countTotalPrice = countPriceGroup + countTotalPrice;
                countGuest = convertNumber + countGuest;
            }
            stop = scanner.nextLine();
        }
        if (priceSinger <= countTotalPrice) {
            double totalCount = countTotalPrice - priceSinger;
            System.out.printf("You have %d guests and %.0f leva left.", countGuest, totalCount);
        } else{
            System.out.printf("You have %d guests and %.0f leva income, but no singer.",countGuest, countTotalPrice);
        }

    }
}
