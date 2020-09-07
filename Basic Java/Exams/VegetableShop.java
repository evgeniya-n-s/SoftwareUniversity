import java.util.Scanner;
public class VegetableShop {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        double priceVegetables = Double.parseDouble(scanner.nextLine());
        double priceFruits = Double.parseDouble(scanner.nextLine());
        double weightVegetables = Double.parseDouble(scanner.nextLine());
        double weightFruits = Double.parseDouble(scanner.nextLine());

        double totalPriceVegetable = priceVegetables * weightVegetables;
        double totalPriceFruits = priceFruits * weightFruits;
        double totalPrice = totalPriceVegetable + totalPriceFruits;
        double totalPriceEuro = totalPrice / 1.94;
        System.out.println(totalPriceEuro);
    }
}
