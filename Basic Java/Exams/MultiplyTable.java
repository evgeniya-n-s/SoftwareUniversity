import java.util.Scanner;

public class MultiplyTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = Integer.parseInt(scanner.nextLine());
        int lastNumber = input % 10;
        int middleNumber = input / 10;
        int middleNumberLast = middleNumber % 10;
        int firstNumber = input / 10;
        int firstNumberLast = firstNumber / 10;

        for (int i = 1; i <= lastNumber ; i++) {
            for (int j = 1 ; j <= middleNumberLast ; j++) {
                for (int k = 1; k<= firstNumberLast  ; k++) {
                    int rezult = i * j * k;
                    System.out.printf("%d * %d * %d = %d;%n", i, j, k, rezult);
                }
            }
        }
    }
}
