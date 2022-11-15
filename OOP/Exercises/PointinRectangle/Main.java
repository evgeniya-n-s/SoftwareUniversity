package PointinRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[] coordinate = Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                .mapToInt ( Integer::parseInt )
                .toArray ();

        Rectangle rectangle = new Rectangle ( new Point2D ( coordinate[0],coordinate[1] ),
                new Point2D ( coordinate[2],coordinate[3] ));

        int n = Integer.parseInt ( scanner.nextLine () );

        while (n-->0){
            coordinate = Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                    .mapToInt ( Integer::parseInt )
                    .toArray ();

            boolean contains = rectangle.contains ( new Point2D ( coordinate[0],coordinate[1] ) );
            System.out.println (contains);

        }

    }
}
