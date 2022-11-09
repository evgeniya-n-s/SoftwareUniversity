import java.util.Scanner;

public class ToyShop {
    public static void main (String []args ){
        Scanner scanner = new Scanner(System.in);
        double excursionPrice = Double.parseDouble(scanner.nextLine());
        int puzzleCount = Integer.parseInt(scanner.nextLine());
        int talkingDollCount = Integer.parseInt(scanner.nextLine());
        int teddyBearCount = Integer.parseInt(scanner.nextLine());
        int minionCount = Integer.parseInt(scanner.nextLine());
        int truckCount = Integer.parseInt(scanner.nextLine());

        double puzzleTotalPrice = puzzleCount * 2.6;
        double talkingDollsTotalPrice = talkingDollCount * 3;
        double teddyBearTotalPrice = teddyBearCount * 4.10;
        double minionTotalPrice = minionCount * 8.20;
        double truckTotalPirce = truckCount * 2;

        double totalPirce = puzzleTotalPrice + talkingDollsTotalPrice + teddyBearTotalPrice + minionTotalPrice + truckTotalPirce;

        double totalToyCount = puzzleCount + talkingDollCount + teddyBearCount + minionCount + truckCount;

        if (totalToyCount >=50){
            totalPirce = totalPirce * 0.75;
        }

        double finalPrice = totalPirce * 0.9;

        if (finalPrice >= excursionPrice){
            double moneyLeft = finalPrice - excursionPrice;
            System.out.printf("Yes! %.2f lv left.", moneyLeft);
        } else {
            double moneyNeed = excursionPrice - finalPrice;
            System.out.printf("Not enough money! %.2f lv needed.", moneyNeed );
        }
    }
}