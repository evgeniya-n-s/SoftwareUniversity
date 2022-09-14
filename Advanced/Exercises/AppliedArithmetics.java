import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppliedArithmetics {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        Function<int[], int[]> increment = arr -> Arrays.stream ( arr ).map ( e-> e+=1 ).toArray ();
        Function<int[], int[]> multiply = arr->Arrays.stream ( arr ).map ( e->e*=2 ).toArray ();
        Function<int[], int[]> decrement = arr-> Arrays.stream ( arr ).map ( e->e -=1).toArray ();
        Consumer<int[]> print = arr -> Arrays.stream ( arr ).forEach ( e-> System.out.print (e + " ") );

        int[] numbers = Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                .mapToInt ( Integer::parseInt )
                .toArray ();

        String command = scanner.nextLine ();

        while (!command.equals ( "end" )) {
            switch (command) {
                case "add":
                    numbers=increment.apply ( numbers );
                    break;
                case "subtract":
                    numbers = decrement.apply ( numbers );
                    break;
                case "multiply":
                    numbers = multiply.apply ( numbers );
                    break;
                case "print":
                    print.accept ( numbers );
                   // System.out.println ();
                    break;
            }
            command = scanner.nextLine ();
        }
    }
}
