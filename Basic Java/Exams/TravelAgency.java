import java.util.Scanner;

public class TravelAgency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nameCity = scanner.nextLine();
        String hotelPackage = scanner.nextLine();
        String vipDiscount = scanner.nextLine();
        int days = Integer.parseInt(scanner.nextLine());

        double price = 0.0;


        if (nameCity.equals("Bansko") || nameCity.equals("Borovets")) {
            if (hotelPackage.equals("withEquipment")) {
                if (days > 7) {
                    price = (days - 1) * 100;
                } else {
                    price = days * 100;
                }
                if (vipDiscount.equals("yes")) {
                    price = price * 0.9;
                }
            } else if (hotelPackage.equals("noEquipment")) {
                if (days > 7) {
                    price = (days - 1) * 80;
                } else {
                    price = days * 80;
                }
                if (vipDiscount.equals("yes")) {
                    price = price * 0.95;
                }
            }
        }


        if (nameCity.equals("Varna") || nameCity.equals("Burgas")) {
            if (hotelPackage.equals("withBreakfast")) {
                if (days > 7) {
                    price = (days - 1) * 130;
                } else {
                    price = days * 130;
                }
                if (vipDiscount.equals("yes")) {
                    price = price * 0.88;
                }
            } else if (hotelPackage.equals("noBreakfast")) {
                if (days > 7) {
                    price = (days - 1) * 100;
                } else {
                    price = days * 100;
                }
                if (vipDiscount.equals("yes")) {
                    price = price * 0.97;
                }
            }
        }

        if (days <= 0) {
            System.out.println("Days must be positive number!");
        } else {
            System.out.printf("The price is %.2flv! Have a nice time!", price);
        }
    }
}
