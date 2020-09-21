import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int[][] firstMatrix = readMatrix ( scanner );
        int[][] secondMatrix = readMatrix ( scanner );

        boolean areEquals = compareMatrix(firstMatrix,secondMatrix);
//        if(areEquals){
//            System.out.println ("equal");
//        } else {
//            System.out.println ("not equal");
//        }
        System.out.println (areEquals ? "equal" : "not equal");

    }

    private static boolean compareMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        if(firstMatrix.length != secondMatrix.length){
            return false;
        }

        for (int rows = 0; rows <firstMatrix.length ; rows++) {
            int[] firstArr = firstMatrix[rows];
            int[] secondArr = secondMatrix[rows];
            if (firstArr.length != secondArr.length) {
                return false;
            }
            for (int column = 0; column < firstArr.length; column++) {
                if (firstArr[column] != secondArr[column]){
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] readMatrix(Scanner scanner) {
        String[] inputTokens = scanner.nextLine ().split ( "\\s+" );
        int row = Integer.parseInt (inputTokens[0]);
        int col = Integer.parseInt (inputTokens[1]);

        int[][] matrix = new int[row][col];

        for (int i = 0; i <row ; i++) {
            String[] tokens = scanner.nextLine ().split ( "\\s+" );
            for (int j = 0; j <col ; j++) {
                matrix[i][j] = Integer.parseInt ( tokens[j] );
            }
        }
        return matrix;
    }
}
