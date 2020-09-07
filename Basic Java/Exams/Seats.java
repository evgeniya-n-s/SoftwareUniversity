import java.util.Scanner;

public class Seats {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberTicket = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i<=numberTicket; i ++){
            String ticketNumber = scanner.nextLine();
            int ticketNumberLength = ticketNumber.length();

            String seat = "";
            if (ticketNumberLength == 4){
                char firstChar = ticketNumber.charAt(0);
                if (firstChar >= 'A' && firstChar <= 'Z'){
                    seat = seat + firstChar;
                } else{
                    seat = seat + ticketNumber.charAt(3);
                }
                seat = seat + ticketNumber.charAt(1);
                seat = seat + ticketNumber.charAt(2);
            } else if(ticketNumberLength == 6 || ticketNumberLength ==5){
                seat = seat + ticketNumber.charAt(0);
                seat += (int) ticketNumber.charAt(1);
            }
            System.out.println("Seat decoded: " + seat);
        }
    }
}
