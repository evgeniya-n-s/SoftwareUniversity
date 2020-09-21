import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumof2x2Submatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[] dimensions = Arrays.stream ( scanner.nextLine ().split ( ", " ) ).mapToInt ( Integer::parseInt ).toArray ();

        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream ( scanner.nextLine ().split ( ", " ) ).mapToInt ( Integer::parseInt ).toArray ();
        }

        int maxValue = Integer.MIN_VALUE;
        int[][] bestMatrix = new int[2][2];
        for (int row = 0; row <rows-1 ; row++) {
            for (int col = 0; col <cols-1 ; col++) {
                int currentSum = matrix[row][col] + matrix[row][col + 1] +matrix[row+1][col] + matrix[row+1][col+1];
                if(currentSum>maxValue){
                    maxValue = currentSum;
                    bestMatrix[0][0]= matrix[row][col];
                    bestMatrix[0][1]= matrix[row][col+1];
                    bestMatrix[1][0]= matrix[row+1][col];
                    bestMatrix[1][1]= matrix[row+1][col+1];
                }
            }
        }

        for (int row = 0; row <bestMatrix.length ; row++) {
            for (int col = 0; col <bestMatrix[row].length ; col++) {
                System.out.print (bestMatrix[row][col] + " ");
            }
            System.out.println ();
        }
        System.out.println (maxValue);
    }
}
