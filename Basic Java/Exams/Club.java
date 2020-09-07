import java.util.Scanner;

public class Club {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double mounth = Double.parseDouble(scanner.nextLine());
        String nameCocktail = scanner.nextLine();
        double countPrice = 0.0;
        double countPrice2 = 0.0;

        while (!nameCocktail.equals("Party!")) {
            int numberCoctail = Integer.parseInt(scanner.nextLine());
            int priceCocktal = nameCocktail.length();

            if (priceCocktal > 0) {
                countPrice = priceCocktal * numberCoctail;
                if (!(countPrice % 2 == 0)) {
                    countPrice = countPrice * 0.75;
                }
            }
            countPrice2 += countPrice;
            if (mounth == countPrice2 || countPrice2 > mounth){
                break;
            }
            nameCocktail = scanner.nextLine();
        }
        if (nameCocktail.equals("Party!")) {
            double needMoney = mounth - countPrice2;
            System.out.printf("We need %.2f leva more.%n", needMoney);
        }
        if (mounth == countPrice2 || countPrice2 > mounth){
            System.out.println("Target acquired.");
        }
        System.out.printf("Club income - %.2f leva.", countPrice2);
    }
}

