import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Scheduling2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );
        ArrayDeque<Integer> tasks = new ArrayDeque<> ();
        Arrays.stream ( scanner.nextLine ().split ( ", " ) )
                .mapToInt ( Integer::parseInt )
                .forEach ( tasks::push );

        ArrayDeque<Integer> threads = Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                .map ( Integer::parseInt )
                .collect ( Collectors.toCollection ( ArrayDeque::new ) );

        int killNumber = Integer.parseInt ( scanner.nextLine () );
        boolean isKillTheNumber = false;
        int thread = 0;
        int tasktoBeKilled = 0;

        while (!threads.isEmpty () && !tasks.isEmpty ()){
            int numberTask = tasks.peek ();
            int numberThread= threads.peek ();

            if (numberThread >= numberTask){
                if(killNumber ==numberTask){
                    isKillTheNumber = true;
                    thread = numberThread;
                    tasktoBeKilled = numberTask;
                    break;
                } else {
                    tasks.poll ();
                    threads.pop ();
                }
            } else {
                if(killNumber ==numberTask){
                    isKillTheNumber = true;
                    thread = numberThread;
                    tasktoBeKilled = numberTask;
                    break;
                } else {
                    threads.pop ();
                }
            }
        }
        System.out.println (String.format ( "Thread with value %d killed task %d",thread,tasktoBeKilled ));

        while (!threads.isEmpty()){
            System.out.print(threads.pop() + " ");
        }
    }
}
