import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String drinks = scanner.nextLine();
        String sugar = scanner.nextLine();
        int numberDrinks = Integer.parseInt(scanner.nextLine());

        double priceDrink = 0.0;


        if (drinks.equals("Espresso")) {
            if (sugar.equals("Without")) {
                priceDrink = (numberDrinks * 0.90) * 0.65;
            } else if (sugar.equals("Normal")) {
                priceDrink = numberDrinks * 1.0;
            } else if (sugar.equals("Extra")) {
                priceDrink = numberDrinks * 1.20;
            }
            if (numberDrinks >= 5) {
                priceDrink = priceDrink * 0.75;
            }
        }

        if (drinks.equals("Cappuccino")) {
            if (sugar.equals("Without")) {
                priceDrink = (numberDrinks * 1.0) * 0.65;
            } else if (sugar.equals("Normal")) {
                priceDrink = numberDrinks * 1.20;
            } else if (sugar.equals("Extra")) {
                priceDrink = numberDrinks * 1.60;
            }
        }

        if (drinks.equals("Tea")) {
            if (sugar.equals("Without")) {
                priceDrink = (numberDrinks * 0.50) * 0.65;
            } else if (sugar.equals("Normal")) {
                priceDrink = numberDrinks * 0.60;
            } else if (sugar.equals("Extra")) {
                priceDrink = numberDrinks * 0.70;
            }
        }

        if (priceDrink > 15) {
            priceDrink = priceDrink * 0.8;
            System.out.printf("You bought %s cups of %s for %.2f lv.", numberDrinks, drinks, priceDrink);
        } else {
            System.out.printf("You bought %s cups of %s for %.2f lv.", numberDrinks, drinks, priceDrink);
        }
    }
}
