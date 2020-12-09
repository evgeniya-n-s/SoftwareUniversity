import java.util.Scanner;

public class OperationBetweenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();

        double count = 0.0;
        String even = "";
        String odd = "";

        if (operator.equals("+")) {
            count = n1 + n2;
            if (count % 2 == 0) {
                even = "even";
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, count, even);
            } else {
                odd = "odd";
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, count, odd);
            }
        }

        if (operator.equals("-")) {
            count = n1 - n2;
            if (count % 2 == 0) {
                even = "even";
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, count, even);
            } else {
                odd = "odd";
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, count, odd);
            }
        }

        if (operator.equals("*")) {
            count = n1 * n2;
            if (count % 2 == 0) {
                even = "even";
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, count, even);
            } else {
                odd = "odd";
                System.out.printf("%d %s %d = %.0f - %s", n1, operator, n2, count, odd);
            }
        }

        if (operator.equals("/")) {
            if (n1 == 0 || n2 == 0 ) {
                System.out.printf("Cannot divide %d by zero", n1);
            }else {
                count = (n1 * 0.1) / (n2 * 0.1);
                System.out.printf("%d %s %d = %.2f", n1, operator, n2, count);
            }
        }

        if (operator.equals("%")) {
            if (n1 == 0 || n2 == 0 ) {
                System.out.printf("Cannot divide %d by zero", n1);
            } else {
                count = n1 % n2;
                if (count % 2 != 0) {
                    System.out.printf("%d %s %d = %.0f", n1, operator, n2, count);
                } else {
                    System.out.printf("%d %s %d = %.0f", n1, operator, n2, count);
                }
            }
        }
    }
}
