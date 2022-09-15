import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class HotPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");
        int number = Integer.parseInt(scanner.nextLine());
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (String child:input){
            queue.offer(child);
        }

        while (queue.size()>1){
            for (int i = 1; i <number ; i++) {
                queue.offer(queue.poll());
            }
            System.out.println("Removed" + " "+queue.poll());
        }
        System.out.println("Last is" + " "+queue.poll());
    }
}
