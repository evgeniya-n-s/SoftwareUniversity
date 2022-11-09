import java.util.Scanner;

public class HotelRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine();
        int nigth = Integer.parseInt(scanner.nextLine());

        double priceStudio = 0.0;
        double priceApartment = 0.0;
        String studio = "";
        String apartment = "";

        if (month.equals("May") || month.equals("October")) {
            if (nigth <= 7) {
                priceStudio = nigth * 50;
                studio = "Studio";
                priceApartment = nigth * 65;
                apartment = "Apartment";
            } else if (nigth <= 14) {
                priceStudio = (nigth * 50) * 0.95;
                studio = "Studio";
                priceApartment = nigth * 65;
                apartment = "Apartment";
            } else {
                priceStudio = (nigth * 50) * 0.7;
                studio = "Studio";
                priceApartment = (nigth * 65) * 0.9;
                apartment = "Apartment";
            }
        }

        if (month.equals("June") || month.equals("September")) {
            if (nigth <= 14) {
                priceStudio = nigth * 75.20;
                studio = "Studio";
                priceApartment = nigth * 68.70;
                apartment = "Apartment";
            } else {
                priceStudio = (nigth * 75.20) * 0.8;
                studio = "Studio";
                priceApartment = (nigth * 68.70) * 0.9;
                apartment = "Apartment";
            }
        }

        if (month.equals("July") || month.equals("August")) {
            if (nigth <= 14) {
                priceStudio = nigth * 76;
                studio = "Studio";
                priceApartment = nigth * 77;
                apartment = "Apartment";
            } else {
                priceStudio = nigth * 76;
                studio = "Studio";
                priceApartment = (nigth * 77) * 0.9;
                apartment = "Apartment";
            }
        }
        System.out.printf("%s: %.2f lv.", apartment, priceApartment);
        System.out.println();
        System.out.printf("%s: %.2f lv.", studio, priceStudio);
    }
}
