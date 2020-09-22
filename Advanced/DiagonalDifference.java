import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int size = Integer.parseInt ( scanner.nextLine () );
        int[][] matrix = new int[size][size];

        for (int row = 0; row <size ; row++) {
            for (int col = 0; col <size ; col++) {
                matrix[row][col] = scanner.nextInt ();
            }
        }

        int mainDiagonal = findDiagonal(matrix);
        int secondDiagonal = getDiagonal(matrix);
        int result = Math.abs ( mainDiagonal-secondDiagonal );
        System.out.println (result);
    }

    private static int getDiagonal(int[][] matrix) {
        int sum = 0;

        int row = matrix.length-1;
        int col = 0;
        while (row >= 0 && col < matrix[row].length) {
            sum += matrix[row][col];
            row--;
            col++;
        }
        return sum;
    }

    private static int findDiagonal(int[][] matrix) {
        int sum = 0;

        int row = 0;
        int col = 0;
        while (row<matrix.length && col<matrix[row].length){
            sum += matrix[row][col];
            row++;
            col++;
        }
        return sum;
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print ( matrix[row][col] + " " );
            }
            System.out.println ();
        }
    }
}
