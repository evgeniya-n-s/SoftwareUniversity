import java.util.Scanner;

public class Bus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberStartPassengers = Integer.parseInt(scanner.nextLine());
        int numberBusStops = Integer.parseInt(scanner.nextLine());

        int countPassenger = numberStartPassengers;

        for (int i = 1; i <=numberBusStops ; i++) {
            int outPassenger = Integer.parseInt(scanner.nextLine());
            int inPassenger = Integer.parseInt(scanner.nextLine());
            if (i % 2 == 0){
                countPassenger = countPassenger - outPassenger + inPassenger - 2;
            } else{
                countPassenger = countPassenger - outPassenger + inPassenger + 2;
            }
        }
        System.out.printf("The final number of passengers is : %d", countPassenger);
    }
}
