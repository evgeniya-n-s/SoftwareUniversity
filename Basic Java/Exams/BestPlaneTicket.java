import java.util.Scanner;

public class BestPlaneTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String numberTicket = scanner.nextLine();
        //    double priceTicket = Double.parseDouble(scanner.nextLine());
        //    int minuteWait = Integer.parseInt(scanner.nextLine());

        int minMinutes = Integer.MAX_VALUE;
        double minPriceTicket = Double.MAX_VALUE;
        String bestTicket = "";

        while (!numberTicket.equals("End")) {
            double priceTicket = Double.parseDouble(scanner.nextLine());
            int minuteWait = Integer.parseInt(scanner.nextLine());
            priceTicket = priceTicket * 1.96;

            if (minuteWait < minMinutes) {
                minPriceTicket = priceTicket;
                minMinutes = minuteWait;
                bestTicket = numberTicket;
            }
            numberTicket = scanner.nextLine();

        }
        System.out.printf("Ticket found for flight %s costs %.2f leva with %dh %dm stay", bestTicket, minPriceTicket, minMinutes / 60, minMinutes % 60);

    }
}
