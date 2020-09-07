import java.util.Scanner;

public class LimonadeStand {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double kgLemons = Double.parseDouble(scanner.nextLine());
        double kgSugar = Double.parseDouble(scanner.nextLine());
        double lWater = Double.parseDouble(scanner.nextLine());

        double countLitter = kgLemons * 980;
        double countLemons = countLitter + (lWater*1000) + (kgSugar * 0.3);
        double countCup = Math.floor(countLemons / 150);
        double countMoney = countCup * 1.2;

        System.out.printf("All cups sold: %.0f", countCup);
        System.out.println();
        System.out.printf("Money earned: %.2f", countMoney);
    }
}
