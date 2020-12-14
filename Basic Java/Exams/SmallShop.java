import java.util.Scanner;
public class SmallShop {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);

        String productName = scanner.nextLine().toLowerCase();
        String city = scanner.nextLine().toLowerCase();
        double quantity = Double.parseDouble(scanner.nextLine());

        if (city.equals("sofia")) {
            if (productName.equals("coffee")) {
                double price = quantity * 0.50;
                System.out.print(price);
            }
            if (productName.equals("water")) {
                double price = quantity * 0.80;
                System.out.print(price);
            }
            if (productName.equals("beer")) {
                double price = quantity * 1.20;
                System.out.print(price);
            }
            if (productName.equals("sweets")) {
                double price = quantity * 1.45;
                System.out.print(price);
            }
            if (productName.equals("peanuts")) {
                double price = quantity * 1.60;
                System.out.print(price);
            }
        }

        if (city.equals("plovdiv")) {
            if (productName.equals("coffee")) {
                double price = quantity * 0.40;
                System.out.print(price);
            }
            if (productName.equals("water")) {
                double price = quantity * 0.70;
                System.out.print(price);
            }
            if (productName.equals("beer")) {
                double price = quantity * 1.15;
                System.out.print(price);
            }
            if (productName.equals("sweets")) {
                double price = quantity * 1.30;
                System.out.print(price);
            }
            if (productName.equals("peanuts")) {
                double price = quantity * 1.50;
                System.out.print(price);
            }
        }

        if (city.equals("varna")) {
            if (productName.equals("coffee")) {
                double price = quantity * 0.45;
                System.out.print(price);
            }
            if (productName.equals("water")) {
                double price = quantity * 0.70;
                System.out.print(price);
            }
            if (productName.equals("beer")) {
                double price = quantity * 1.10;
                System.out.print(price);
            }
            if (productName.equals("sweets")) {
                double price = quantity * 1.35;
                System.out.print(price);
            }
            if (productName.equals("peanuts")) {
                double price = quantity * 1.55;
                System.out.print(price);
            }
        }
    }
}
