import java.util.Scanner;

public class OscarsCeremony {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rent = Integer.parseInt(scanner.nextLine());
        double countStatus = rent * 0.7;
        double countKetaring = countStatus * 0.85;
        double countSound = countKetaring /2;

        double sum = rent + countStatus + countKetaring + countSound;

        System.out.printf("%.2f", sum);
    }
}