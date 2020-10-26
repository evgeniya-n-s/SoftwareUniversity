import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class KnightsofHonor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String [] names = scanner.nextLine ().split ( "\\s+" );
        Consumer<String []> print = namesArray -> {
            for (int i = 0; i < namesArray.length; i++) {
                System.out.println ("Sir " + namesArray[i]);
            }
        };
        print.accept ( names );
    }
}
