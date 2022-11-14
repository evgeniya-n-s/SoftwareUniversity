package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[] dimensions = readPosition ( scanner.nextLine (), "\\s+" );

        Galaxy galaxy = new Galaxy ( new Field ( dimensions[0],dimensions[1] ) );

        String position = scanner.nextLine ();
        long starsPowerCollected = 0;
        while (!position.equals ( "Let the Force be with you" )) {



            int[] jediPosition = readPosition ( position, "\\s+" );
            int[] sithPosition = readPosition ( scanner.nextLine (), "\\s+" );

            int rowJedi = jediPosition[0];
            int colJedi = jediPosition[1];

            int rowSith = sithPosition[0];
            int colSith = sithPosition[1];

            galaxy.moveSith(rowSith,colSith);
            starsPowerCollected += galaxy.moveJedi(rowJedi,colJedi);

            position = scanner.nextLine ();
        }

        System.out.println ( starsPowerCollected );


    }

    private static int[] readPosition(String position, String s) {
        return Arrays.stream ( position.split ( s ) )
                .mapToInt ( Integer::parseInt )
                .toArray ();
    }
}
