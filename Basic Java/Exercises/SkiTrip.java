import java.util.Scanner;

public class SkiTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int day = Integer.parseInt(scanner.nextLine());
        String kindRoom = scanner.nextLine();
        String feedback = scanner.nextLine();

        int countNight = day - 1;
        double priceRoom = 18.00;
        double priceApartment = 25.00;
        double pricePresident = 35.00;
        double price = 0.0;

        if (kindRoom.equals("room for one person")) {
            price = priceRoom * countNight;
        }

        if (kindRoom.equals("apartment")) {
            if (day < 10) {
                price = (priceApartment * countNight) * 0.7;
            } else if (day < 15) {
                price = (priceApartment * countNight) * 0.65;
            } else {
                price = (priceApartment * countNight) * 0.5;
            }
        }

        if (kindRoom.equals("president apartment")) {
            if (day < 10) {
                price = (pricePresident * countNight) * 0.9;
            } else if (day < 15) {
                price = (pricePresident * countNight) * 0.85;
            } else {
                price = (pricePresident * countNight) * 0.8;
            }
        }

        if (feedback.equals("positive")) {
            price = price + (price * 0.25);
        } else if (feedback.equals("negative")){
            price = price - (price * 0.1);
        }
        System.out.printf("%.2f",price);
    }
}
