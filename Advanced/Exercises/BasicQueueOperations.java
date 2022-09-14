import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(tokens[0]);
        int s = Integer.parseInt(tokens[1]);
        int x = Integer.parseInt(tokens[2]);

        String[] inputNumbers = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i <n ; i++) {
            queue.offer(Integer.parseInt(inputNumbers[i]));
        }

        for (int i = 0; i <s ; i++) {
            queue.poll();
        }

        if(queue.isEmpty()){
            System.out.println(0);
        } else {
            if(queue.contains(x)){
                System.out.println("true");
            } else {
                System.out.println(Collections.min(queue));
            }
        }
    }
}
