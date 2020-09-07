import java.util.Scanner;

public class GodzillaKong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budgetFilm = Double.parseDouble(scanner.nextLine());
        int extras = Integer.parseInt(scanner.nextLine());
        double priceCloths = Double.parseDouble(scanner.nextLine());

        double decor = budgetFilm * 0.1;
        double totalPriceCloths = extras * priceCloths;

        if (extras >150){
            totalPriceCloths = totalPriceCloths * 0.9;

        }

        double price = decor + totalPriceCloths;

        if (price > budgetFilm){
            System.out.println("Not enough money!");
            double missingPice = price - budgetFilm;
            System.out.printf("Wingard needs %.2f leva more.", missingPice);
        }
        else{
            System.out.println("Action!");
            double leftMoney = budgetFilm - price;
            System.out.printf("Wingard starts filming with %.2f leva left.", leftMoney);
        }
    }
}