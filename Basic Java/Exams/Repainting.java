import java.util.Scanner;

public class Repainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantityNylon = Integer.parseInt(scanner.nextLine());
        int quantityPaint = Integer.parseInt(scanner.nextLine());
        int quantityDiluent = Integer.parseInt(scanner.nextLine());
        int hoursWork = Integer.parseInt(scanner.nextLine());

        double extraNylon = quantityNylon + 2; //12
        double extraNylon2 = extraNylon * 1.5; //18
        double extraPaint = quantityPaint * 0.1; //1.1
        double extraPaint2 = extraPaint + quantityPaint; //12.1
        double exctraPaintPrice = extraPaint2 * 14.50; // 175.45
        double priceDiluent = quantityDiluent * 5.00; // 20
        double price = extraNylon2 + exctraPaintPrice + priceDiluent + 0.40;//18+175.45+20+0.40=213.85
        double salary = 0.3 * price; // 149.695 //64.155
        double totalSalary = salary * hoursWork; // 1197.56 //513.24
        double totalPrice = price + totalSalary; // 1411.41 //727.09

        System.out.printf("Total expenses: %.2f lv.",totalPrice);
    }
}

