import java.util.Scanner;

public class SummerShopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine());
        double costTowel = Double.parseDouble(scanner.nextLine());
        int percent = Integer.parseInt(scanner.nextLine());

        double countUmbrella = costTowel * 2/3;
        double countFlipFlop = countUmbrella * 0.75;
        double beachBag = (countFlipFlop + costTowel) * 1/3;

        double countPrice = (costTowel + countUmbrella + countFlipFlop + beachBag);
        double countPercent = countPrice - (countPrice * (percent * 0.01));

        if (budget >= countPercent){
            double countLeft = budget - countPercent;
            System.out.printf("Annie's sum is %.2f lv. She has %.2f lv. left.", countPercent, countLeft);
        } else {
            double countNeed = countPercent - budget;
            System.out.printf("Annie's sum is %.2f lv. She needs %.2f lv. more.", countPercent, countNeed);
        }
    }
}
