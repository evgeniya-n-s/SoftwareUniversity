import java.util.Scanner;

public class TransportPrice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countKm = Integer.parseInt(scanner.nextLine());
        String time = scanner.nextLine();

        double price = 0.0;

        if (time.equals("day")){
            if (countKm < 20){
                price = ((countKm * 0.79) + 0.70);
            } else if (countKm < 100){
                price = countKm * 0.09;
            } else {
                price = countKm * 0.06;
            }
        }
        if (time.equals("night")){
            if (countKm < 20){
                price = ((countKm * 0.90) + 0.70);
            }else if (countKm < 100){
                price = countKm * 0.09;
            } else {
                price = countKm * 0.06;
            }
        }

        System.out.printf("%.2f",price);
    }
}
