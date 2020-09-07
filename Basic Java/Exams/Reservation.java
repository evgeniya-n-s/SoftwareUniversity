import java.util.Scanner;

public class Reservation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dayReservation = Integer.parseInt(scanner.nextLine());
        int monthReservation = Integer.parseInt(scanner.nextLine());
        int dayCheckIn = Integer.parseInt(scanner.nextLine());
        int monthCheckIn = Integer.parseInt(scanner.nextLine());
        int dayCheckOut = Integer.parseInt(scanner.nextLine());
        int monthCheckOut = Integer.parseInt(scanner.nextLine());

        double count = 0.0;
        double count1 = 0.0;

        if (monthReservation < monthCheckIn){
            count = dayCheckOut - dayCheckIn;
            count1 = (count * 25) * 0.8;
        } else if (monthReservation == monthCheckIn){
            double count3 = (dayCheckIn - dayReservation);
            if (count3 < 10){
                count = dayCheckOut - dayCheckIn;
                count1 = count * 30;
            } else{
                count = dayCheckOut - dayCheckIn;
                count1 = count * 25;
            }
        }
        System.out.printf("Your stay from %d/%d to %d/%d will cost %.2f", dayCheckIn, monthCheckIn, dayCheckOut, monthCheckOut, count1);
    }
}
