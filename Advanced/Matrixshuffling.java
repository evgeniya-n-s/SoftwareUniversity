import java.util.Arrays;
import java.util.Scanner;

public class Matrixshuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[] dimension = Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                .mapToInt ( Integer::parseInt )
                .toArray ();
        int row = dimension[0];
        int col = dimension[1];
        String[][] matrix = new String[row][col];

        for (int rows = 0; rows <row ; rows++) {
            matrix[rows] = scanner.nextLine ().split ( "\\s+" );
        }

        String line = "";
        while (!(line=scanner.nextLine ()).equals ( "END" )){
            String[] tokens = line.split ( "\\s+" );

            if(!tokens[0].equals ( "swap" )){
                System.out.println ("Invalid input!");
                continue;
            }

            if(tokens.length!=5){
                System.out.println ("Invalid input!");
                continue;
            }
            int row1 = Integer.parseInt ( tokens[1]);
            int col1 = Integer.parseInt ( tokens[2]);
            int row2 = Integer.parseInt ( tokens[3]);
            int col2 = Integer.parseInt ( tokens[4]);

            if(row1<0 || row1>matrix.length || col1<0 || col1>matrix[0].length
            || row2<0 || row2>matrix.length || col2<0 || col2>matrix[0].length){
                System.out.println ("Invalid input!");
                continue;
            }

            String temp = matrix[row1][col1];
            matrix[row1][col1]= matrix[row2][col2];
            matrix[row2][col2]=temp;
            printMatrix ( matrix );

        }

    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print ( matrix[row][col] + " " );
            }
            System.out.println ();
        }
    }
}
