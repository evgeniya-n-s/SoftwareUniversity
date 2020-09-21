import java.util.Scanner;

public class IntersectionofTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int rows = Integer.parseInt ( scanner.nextLine () );
        int cols = Integer.parseInt ( scanner.nextLine () );

        char[][] first = readMatrix ( scanner,rows,cols );
        char[][] second = readMatrix ( scanner,rows,cols );

        char[][] output = new char[rows][cols];

        for (int row = 0; row <rows ; row++) {
            for (int col = 0; col <cols ; col++) {
                output[row][col] = first[row][col]==second[row][col] ? first[row][col] : '*';
            }
        }

        for (int row = 0; row <rows ; row++) {
            for (int col = 0; col <cols ; col++) {
              char symbol = output[row][col];
                System.out.print (symbol + " ");
            }
            System.out.println ();
        }

    }
    public static char[][] readMatrix (Scanner scanner, int rows, int cols){
        char[][] matrix = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] symbols = scanner.nextLine ().split ( "\\s+" );
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = symbols[col].charAt ( 0 );
            }
        }
        return matrix;
    }
}
