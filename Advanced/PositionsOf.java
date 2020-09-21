import java.util.Scanner;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[][] matrix = readMatrix (scanner);

        int number = Integer.parseInt ( scanner.nextLine () );
        boolean isFound = false;

        for (int row = 0; row <matrix.length ; row++) {
            for (int col = 0; col <matrix[row].length ; col++) {
                int currentNumber = matrix[row][col];
                if(currentNumber==number){
                    System.out.println (row + " "+ col);
                    isFound = true;
                }
            }
        }

        if(!isFound){
            System.out.println ("not found");
        }

    }

    private static int[][] readMatrix(Scanner scanner) {
        String[] inputTokens = scanner.nextLine ().split ( "\\s+" );
        int row = Integer.parseInt ( inputTokens[0] );
        int col = Integer.parseInt ( inputTokens[1] );

        int[][] matrix = new int[row][col];

        for (int i = 0; i < row; i++) {
            String[] tokens = scanner.nextLine ().split ( "\\s+" );
            for (int j = 0; j < col; j++) {
                matrix[i][j] = Integer.parseInt ( tokens[j] );
            }
        }
        return matrix;
    }
}
