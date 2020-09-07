import java.util.Scanner;

public class Moon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double averageSpeed = Double.parseDouble(scanner.nextLine());
        double litterGas = Double.parseDouble(scanner.nextLine());

        double totalDistance = 384400 * 2;
        double time = Math.ceil(totalDistance / averageSpeed);
        double totalTime = time + 3;
        double totalGas = litterGas*totalDistance/100;

        System.out.printf("%.0f%n", totalTime);
        System.out.printf("%.0f",totalGas);
    }
}
