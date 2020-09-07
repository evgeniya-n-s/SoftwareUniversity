import java.util.Scanner;

public class MathPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int key = Integer.parseInt(scanner.nextLine());
        boolean check = false;

        for (int i = 1; i <= 30; i++) {
            for (int j = 1; j <= 30; j++) {
                for (int k = 1; k <= 30; k++) {
                    if (i < j && j < k) {
                        if ((i + j + k) == key) {
                            int resultSum = i + j + k;
                            System.out.printf("%d + %d + %d = %d%n", i, j, k, resultSum);
                            check = true;
                        }
                    } else if (i > j && j > k) {
                        if ((i * j * k) == key) {
                            int resultMultiple = i * j * k;
                            System.out.printf("%d * %d * %d = %d%n", i, j, k, resultMultiple);
                            check = true;
                        }
                    }
                }
            }
        }
        if (!check){
            System.out.println("No!");
        }
    }
}
