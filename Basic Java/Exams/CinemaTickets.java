import java.util.Scanner;

public class CinemaTickets {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String nameMovie = scanner.nextLine();

        int strudentTicket = 0;
        int studentTicket1 = 0;
        int standardTicket = 0;
        int standardTicket1 = 0;
        int kidTicket = 0;
        int kidTicket1 = 0;

        while (!nameMovie.equals("Finish")){
            int freeSpace = Integer.parseInt(scanner.nextLine());
            studentTicket1 = 0;
            standardTicket1 = 0;
            kidTicket1 = 0;
            for (int i=1; i<=freeSpace; i++){
                String typeTicket = scanner.nextLine();
                if (typeTicket.equals("student")){
                    strudentTicket ++;
                    studentTicket1 ++;
                } else if (typeTicket.equals("standard")){
                    standardTicket ++;
                    standardTicket1 ++;
                } else if (typeTicket.equals("kid")){
                    kidTicket ++;
                    kidTicket1 ++;
                } else{
                    break;
                }
            }
            double countTicketPersent = 1.0*(studentTicket1 + standardTicket1 + kidTicket1)/freeSpace*100;
            System.out.printf("%s - %.2f%% full.%n",nameMovie,countTicketPersent);
            nameMovie = scanner.nextLine();
        }
        int countTicket = strudentTicket + standardTicket + kidTicket;
        System.out.printf("Total tickets: %d%n", countTicket);
        double countStudentTickets = 1.0*strudentTicket/countTicket*100;
        System.out.printf("%.2f%% student tickets.%n", countStudentTickets);
        double countStandardTicket = 1.0*standardTicket/countTicket*100;
        System.out.printf("%.2f%% standard tickets.%n",countStandardTicket);
        double countKidTicket = 1.0*kidTicket/countTicket*100;
        System.out.printf("%.2f%% kids tickets.",countKidTicket);
    }
}
