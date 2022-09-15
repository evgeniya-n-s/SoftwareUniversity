import java.util.Scanner;

public class FilltheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String[] input = scanner.nextLine ().split ( ", " );
        int size = Integer.parseInt ( input[0] );
        String symbol = input[1];

        int[][] matrix = new int[size][size];

        if (symbol.equals ( "A" )) {
            matrix = fillPatternA ( size );
            printMatrix(matrix);
        } else if (symbol.equals ( "B" )) {
            matrix = fillPatternB ( size );
            printMatrix(matrix);
        }


    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row <matrix.length ; row++) {
            for (int col = 0; col <matrix[row].length ; col++) {
                System.out.print (matrix[row][col] + " ");
            }
            System.out.println ();
        }
    }

    private static int[][] fillPatternA(int size) {
        int [][] matrix = new int[size][size];

        int value = 1;
        for (int col = 0; col <size ; col++) {
            for (int row = 0; row <size ; row++) {
                matrix[row][col] = value++;
            }
        }
        return matrix;
    }

    private static int[][] fillPatternB(int size) {
        int[][] matrix = new int[size][size];
        
        int value = 1;
        for (int col = 0; col <size ; col++) {
            if(col %2 ==0) {
                for (int row = 0; row < size; row++) {
                    matrix[row][col] = value++;
                }
            }else {
                for (int row = size-1; row >=0 ; row--) {
                    matrix[row][col] = value++;
                }
            }
        }
        
        return matrix;
    }
}
