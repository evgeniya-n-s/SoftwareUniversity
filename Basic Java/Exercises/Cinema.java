import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String projection = scanner.nextLine();
        int rows = Integer.parseInt(scanner.nextLine());
        int column = Integer.parseInt(scanner.nextLine());

        double income = 0.0;

        switch (projection) {
            case "Premiere":
                income = rows * column * 12;
                System.out.printf("%.2f leva",income);
                break;
            case "Normal":
                income = rows * column * 7.5;
                System.out.printf("%.2f leva",income);
                break;
            case "Discount":
                income = rows * column * 5;
                System.out.printf("%.2f leva",income);
                break;
        }
    }
}
