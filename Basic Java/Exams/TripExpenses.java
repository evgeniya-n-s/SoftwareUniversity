import java.util.Scanner;

public class TripExpenses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int durationOfHoliday = Integer.parseInt(scanner.nextLine());

        double countPriceProduct = 0.0;
        double plusMoney = 0.0;

        for (int i = 0; i <= durationOfHoliday; i++) {
            double limitedMoney = 60 - countPriceProduct + plusMoney;
            countPriceProduct = 0.0;

            String priceProduct = scanner.nextLine();

            int product = 0;

            while (!(priceProduct.equals("Day over"))) {
                double priceProductConvert = Double.parseDouble(priceProduct);

                if (limitedMoney - priceProductConvert >= 0) {
                    limitedMoney = limitedMoney - priceProductConvert;
                    product++;
                } else {
                    priceProduct = scanner.nextLine();
                }
                if (limitedMoney == 0) {
                    break;
                }
                priceProduct = scanner.nextLine();
            }
            if (priceProduct.equals("Day over")) {
                limitedMoney = Math.abs(limitedMoney - countPriceProduct);
                plusMoney = limitedMoney;
                System.out.printf("Money left from today: %.2f. You've bought %d products.%n", limitedMoney, product);
            } else {
                System.out.printf("Daily limit exceeded! You've bought %d products.%n", product);
            }
        }
    }
}