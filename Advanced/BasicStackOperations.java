import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class BasicStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(tokens[0]);
        int s = Integer.parseInt(tokens[1]);
        int x = Integer.parseInt(tokens[2]);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        String[] numbers = scanner.nextLine().split("\\s+");

        for (int i = 0; i < n; i++) {
            stack.push(Integer.parseInt(numbers[i]));
        }

        for (int i = 0; i < s; i++) {
            stack.pop();
        }

        if(stack.isEmpty()){
            System.out.println(0);
        } else {
            if(stack.contains(x)){
                System.out.println("true");
            } else {
                System.out.println(Collections.min(stack));
            }
        }
    }
}
