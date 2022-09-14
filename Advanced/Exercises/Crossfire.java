import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[] dimension = Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                .mapToInt ( Integer::parseInt )
                .toArray ();

        int rows = dimension[0];
        int cols = dimension[1];

        List<List<Integer>> matrix = new ArrayList<> ();

        int counter = 1;
        for (int i = 0; i < rows; i++) {
            List<Integer> numbers = new ArrayList<> ();
            for (int j = 0; j < cols; j++) {
                numbers.add ( counter++ );
            }
            matrix.add ( numbers );
        }

        String command = "";
        while (!(command = scanner.nextLine ()).equals ( "Nuke it from orbit" )) {
            int[] tokens = Arrays.stream ( command.split ( "\\s+" ) )
                    .mapToInt ( Integer::parseInt )
                    .toArray ();

            int targetRow = tokens[0];
            int targerCol = tokens[1];
            int radius = tokens[2];

            for (int row = targetRow - radius; row <= targetRow + radius; row++) {
                if (isValid ( row, targerCol, matrix )) {
                    matrix.get ( row ).set ( targerCol, 0 );
                }
            }

            for (int col = targerCol - radius; col <= targerCol + radius; col++) {
                if (isValid ( targetRow, col, matrix )) {
                    matrix.get ( targetRow ).set ( col, 0 );
                }
            }

            for (int i = 0; i < matrix.size (); i++) {
                matrix.get ( i ).removeAll (List.of (0));
                if(matrix.get ( i ).size () ==0){
                    matrix.remove ( i );
                    i--;
                }
            }
        }
        printMatrix ( matrix );
    }

    private static void printMatrix(List<List<Integer>> matrix) {
        for (int row = 0; row < matrix.size (); row++) {
            for (int col = 0; col < matrix.get ( row ).size (); col++) {
                System.out.print ( matrix.get ( row ).get ( col ) + " " );
            }
            System.out.println ();
        }
    }

    private static boolean isValid(int row, int col, List<List<Integer>> matrix) {
        return (row >= 0 && row < matrix.size ()) && (col >= 0 && col < matrix.get ( row ).size ());
    }
}
