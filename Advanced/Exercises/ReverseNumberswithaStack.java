import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class ReverseNumberswithaStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split("\\s+");
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String number: numbers){
            stack.push(number);
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
