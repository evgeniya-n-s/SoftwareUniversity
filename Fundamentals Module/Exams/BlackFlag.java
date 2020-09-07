import java.util.Scanner;

public class BlackFlag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysToPlunder = Integer.parseInt(scanner.nextLine()); //5
        int dailyPlunder = Integer.parseInt(scanner.nextLine()); //10
        double expectedPlunder = Double.parseDouble(scanner.nextLine());

        double plunderToThisMoment = 0;
        for (int i = 1; i <= daysToPlunder; i++) {
            plunderToThisMoment += dailyPlunder;
            if (i % 3 == 0) {
                plunderToThisMoment += 0.5 * dailyPlunder;
            }
            if (i % 5 == 0) {
                plunderToThisMoment = 0.7 * plunderToThisMoment;
            }
        }
        if (plunderToThisMoment >= expectedPlunder) {
            System.out.printf("Ahoy! %.2f plunder gained.", plunderToThisMoment);
        } else {
            double percentOfTotalPlunder = (plunderToThisMoment / expectedPlunder) * 100;
            System.out.printf("Collected only %.2f%% of the plunder.", percentOfTotalPlunder);
        }
    }
}