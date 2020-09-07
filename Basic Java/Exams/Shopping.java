import java.util.Scanner;

public class Shopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        int numberKarts = Integer.parseInt(scanner.nextLine());
        int numberProcessor = Integer.parseInt(scanner.nextLine());
        int numberRAM = Integer.parseInt(scanner.nextLine());

//        double priceKarts = 250;
//        double countPriceKart = 0.0;
//        double countPriceProcessor = 0.0;
//        double countRAM = 0.0;
      double  countPriceKart = 250 * numberKarts;
      double  countPriceProcessor = (countPriceKart * 0.35) * numberProcessor;
      double  countRAM = (countPriceKart * 0.1) * numberRAM;

        if (numberKarts > numberProcessor){
            double totalcount = (countPriceKart + countPriceProcessor + countRAM) * 0.85;
            if (budget > totalcount){
                double leftMoney = budget - totalcount;
                System.out.printf("You have %.2f leva left!", leftMoney);
            } else{
                double needMoney = totalcount - budget;
                System.out.printf("Not enough money! You need %.2f leva more!", needMoney);
            }
        } else{
            double totalcount = countPriceKart + countPriceProcessor + countRAM;
            if (budget > totalcount){
                double leftMoney = budget - totalcount;
                System.out.printf("You have %.2f leva left!", leftMoney);
            } else{
                double needMoney = totalcount - budget;
                System.out.printf("Not enough money! You need %.2f leva more!", needMoney);
            }
        }
    }
}
