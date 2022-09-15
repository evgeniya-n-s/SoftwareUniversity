import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        ArrayDeque<Integer> lilies = new ArrayDeque<> ();
        Arrays.stream ( scanner.nextLine ().split ( ", " ) )
                .mapToInt ( Integer::parseInt )
                .forEach ( lilies::push );

        ArrayDeque<Integer> roses = Arrays.stream ( scanner.nextLine ().split ( ", " ) )
                .map ( Integer::parseInt )
                .collect ( Collectors.toCollection ( ArrayDeque::new ) );

        int countWreaths = 0;
        int safeWreaths = 0;
        while (!lilies.isEmpty () && !roses.isEmpty ()) {
            int liliesFlower = lilies.pop ();
            int rosesFlower = roses.poll ();
            int sum = rosesFlower + liliesFlower;

            if (sum == 15) {
                countWreaths++;
            } else if (sum > 15) {
                lilies.push ( liliesFlower - 2 );
                roses.push ( rosesFlower );
            } else {
                safeWreaths +=sum;
            }
        }

        while (safeWreaths>=15){
            safeWreaths -=15;
            countWreaths++;
        }

        if(countWreaths >=5){
            System.out.println (String.format ( "You made it, you are going to the competition with %d wreaths!",countWreaths ));
        } else {
            int needWreaths = 5 - countWreaths;
            System.out.println (String.format ( "You didn't make it, you need %d wreaths more!",needWreaths ));
        }
    }
}
