import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String[] input = scanner.nextLine ().split ( "\\s+" );
        int rows = Integer.parseInt ( input[0] );
        int column = Integer.parseInt ( input[1] );

        int[][] matrix = new int[rows][column];

        for (int row = 0; row <rows ; row++) {
            String[] tokens = scanner.nextLine ().split ( "\\s+" );
            for (int col = 0; col <column ; col++) {
                matrix[row][col] = Integer.parseInt ( tokens[col]);
            }
        }

        int maxSum=Integer.MIN_VALUE;
        int row = 0;
        int col = 0;
        for (int r = 1; r <matrix.length - 1 ; r++) {
            for (int c = 1; c <matrix[r].length - 1 ; c++) {
                int sum = gerSumMatrix(matrix,r,c);
                if(sum>maxSum){
                    maxSum = sum;
                    row = r;
                    col = c;
                }
            }
        }
        System.out.printf ((String.format ( "Sum = %d", maxSum)));
        System.out.println ();

        row--;
        col--;
        for (int i = row; i <row+3 ; i++) {
            for (int j = col; j <col+3 ; j++) {
                System.out.print (matrix[i][j] + " ");
            }
            System.out.println ();
        }
    }

    private static int gerSumMatrix(int[][] matrix, int r, int c) {
        int sum = 0;

        r--;
        c--;

        for (int i = r; i <r+3 ; i++) {
            for (int j = c; j <c+3 ; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
