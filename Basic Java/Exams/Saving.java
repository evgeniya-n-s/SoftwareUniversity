import java.util.Scanner;
public class Saving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double income = Double.parseDouble(scanner.nextLine());
        int mounth = Integer.parseInt(scanner.nextLine());
        double expense = Double.parseDouble(scanner.nextLine());

        double mounthNeed = income * 0.3;
        double mountSave = income - (expense + mounthNeed);
        double savingMonye = mounth * mountSave;
        double percent = (mountSave / income) * 100;

        System.out.printf("She can save %.2f", percent);
        System.out.print("%");
        System.out.println();
        System.out.printf("%.2f", savingMonye);
    }
}

