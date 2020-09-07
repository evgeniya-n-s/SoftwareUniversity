import java.util.Scanner;

public class SeaTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyPerDay = Double.parseDouble(scanner.nextLine());
        double moneyPerSouvenir = Double.parseDouble(scanner.nextLine());
        double moneyPerHotel = Double.parseDouble(scanner.nextLine());

        double moneyPerThreeDays = 3 * moneyPerDay + 3 * moneyPerSouvenir;
        double hotelMoneyFirstDay = moneyPerHotel * 0.9;
        double hotelMoneySecondDay = moneyPerHotel * 0.85;
        double hotelMoneyThirdDay = moneyPerHotel * 0.8;

        double totalCount = 54.39 + moneyPerThreeDays + hotelMoneyFirstDay + hotelMoneySecondDay + hotelMoneyThirdDay;

        System.out.printf("Money needed: %.2f", totalCount);
    }
}
