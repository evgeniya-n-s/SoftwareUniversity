import java.util.Scanner;
public class AlcoholShop {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        double priceWhisky = Double.parseDouble(scanner.nextLine());
        double quantityBeer = Double.parseDouble(scanner.nextLine());
        double quantityWine = Double.parseDouble(scanner.nextLine());
        double quantityRakia = Double.parseDouble(scanner.nextLine());
        double quantityWhisky = Double.parseDouble(scanner.nextLine());
        double priceRakia = priceWhisky * 0.5;
        double priceWine = priceRakia * 0.6;
        double priceBeer = priceRakia * 0.2;
        double sumRakia = quantityRakia * priceRakia;
        double sumWine = quantityWine * priceWine;
        double sumBeer = quantityBeer * priceBeer;
        double sumWhisky = quantityWhisky * priceWhisky;
        double totalPrice = sumWhisky + sumRakia + sumWine + sumBeer;
        System.out.printf("%.2f",totalPrice);
    }

}
