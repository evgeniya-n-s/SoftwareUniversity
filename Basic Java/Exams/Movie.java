import java.util.Scanner;
public class Movie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double budget = Double.parseDouble(scanner.nextLine());
        int extras = Integer.parseInt(scanner.nextLine());
        double priceCloths = Double.parseDouble(scanner.nextLine());

        double decor = budget * 0.1;
        double countCloths = extras * priceCloths;


        if (extras > 150) {
            double discount = countCloths * 0.9;
            double priceMovie = decor + discount;
            double totalBudget = Math.abs(budget - priceMovie);
            if (totalBudget > budget) {
                System.out.println("Not enough money!");
                System.out.printf("Wingard needs %.2f leva more.", totalBudget);
            } else {
                System.out.println("Action!");
                System.out.printf("Wingard starts filming with %.2f leva left.", totalBudget);
            }
        }
        if (extras < 150){
            double priceMovie1 = decor + countCloths;
            double leftMoney = Math.abs(budget - priceMovie1);
            if (leftMoney > budget) {
                System.out.println("Not enough money!");
                System.out.printf("Wingard needs %.2f leva more.", leftMoney);
            }
            else {
                System.out.println("Action!");
                System.out.printf("Wingard starts filming with %.2f leva left.", leftMoney);
            }
        }
    }
}
