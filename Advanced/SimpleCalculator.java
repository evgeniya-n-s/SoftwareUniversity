import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        ArrayDeque<String> stack = new ArrayDeque<>();
        Collections.addAll(stack, tokens);

        while (stack.size() > 1) {
            int first = Integer.valueOf(stack.pop());
            String operation = stack.pop();
            int second = Integer.valueOf(stack.pop());

            switch (operation) {
                case "+":
                    stack.push(String.valueOf(first+second));
                    break;
                case "-":
                    stack.push(String.valueOf(first-second));
                    break;
            }
        }
        System.out.println(stack.peek());
    }
}

