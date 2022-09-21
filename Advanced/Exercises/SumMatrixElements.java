import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String[] input = scanner.nextLine ().split ( ", " );
        int rows = Integer.parseInt ( input[0] );
        int columns = Integer.parseInt ( input[1] );

        int[][] matrix = new int[rows][columns];

        for (int i = 0; i <rows ; i++) {
            String[] tokens = scanner.nextLine ().split ( ", " );
            for (int j = 0; j <columns ; j++) {
                matrix[i][j] = Integer.parseInt ( tokens[j] );
            }
        }
        int sum = 0;

        for (int row = 0; row <matrix.length ; row++) {
            for (int column = 0; column <matrix[row].length ; column++) {
                int currentNumber = matrix[row][column];
                sum = sum + currentNumber;
            }
        }

        System.out.println (rows);
        System.out.println (columns);
        System.out.println (sum);
    }
}
