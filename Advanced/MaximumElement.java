import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberRow = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < numberRow; i++) {
            String[] inputCommands = scanner.nextLine().split("\\s+");
            int command = Integer.parseInt(inputCommands[0]);

            switch (command) {
                case 1:
                    stack.push(Integer.parseInt(inputCommands[1]));
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    if(!stack.isEmpty()){
                        System.out.println(Collections.max(stack));
                    }
                    break;
            }
        }
    }
}
