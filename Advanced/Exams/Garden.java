import java.util.Scanner;

public class Garden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String[] input = scanner.nextLine ().split ( "\\s+" );
        int n = Integer.parseInt ( input[0] );
        int m = Integer.parseInt ( input[1] );

        int[][] garden = new int[n][m];

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                garden[i][j] = 0;
//            }
//        }

        String command = scanner.nextLine ();
        while (!command.equals ( "Bloom Bloom Plow" )){
            String[] rowsAndCol = command.split ( " " );
            int row = Integer.parseInt ( rowsAndCol[0]);
            int col = Integer.parseInt ( rowsAndCol[1] );
            int currentRow = row;
            int currentCol = col;

            if(isInBound ( garden,row,col )){
                changeNumber ( row,col,garden );
                while (row >= 0 && row<= garden.length){
                        row++;
                        changeNumber ( row, col, garden );
                    }
                row = currentRow;
                while (row >= 0 && row<= garden.length){
                    row--;
                    changeNumber ( row,col,garden );
                }
                row = currentRow;
                col = currentCol;
                while (col >= 0 && col<= garden[row].length){
                    col++;
                    changeNumber ( row, col, garden );
                }
                col = currentCol;
                while (col >= 0 && col<= garden[row].length){
                    col--;
                    changeNumber ( row, col, garden );
                }
            }
            command = scanner.nextLine ();
        }

        printMatrix ( garden );
    }

    private static boolean changeNumber(int row, int col, int[][] garden){
        if(isInBound ( garden,row,col )) {
            int currentNumber = garden[row][col];
            garden[row][col] = currentNumber + 1;
        }
        return true;
    }
//    private static boolean moveSnake(int oldRow, int oldCol, int newRow, int newCol, char[][] field) {
//        if (field[newRow][newCol] == '-'){
//            field[newRow][newCol] ='S';
//        } else if (field[newRow][newCol] == '*'){
//            field[newRow][newCol] ='S';
//            food++;
//        } else if (field[newRow][newCol] == 'B'){
//            for (int row = 0; row < field.length; row++) {
//                for (int col = 0; col < field[row].length; col++) {
//                    if(field[row][col] == 'B' && (row!=newRow || col != newCol)){
//                        field[row][col] = 'S';
//                        field[newRow][newCol] = '.';
//                        field[oldRow][oldCol] = '.';
//                        rowSnake = row;
//                        colSnake = col;
//                        return true;
//                    }
//                }
//            }
//        }
//        field[oldRow][oldCol] ='.';
//        return false;
//    }
    private static void printMatrix(int[][] garden) {
        for (int row = 0; row < garden.length; row++) {
            for (int col = 0; col < garden[row].length; col++) {
                System.out.print ( garden[row][col] + " " );
            }
            System.out.println ();
        }
    }

    private static boolean isInBound(int[][] garden, int row, int col) {
        return row >= 0 && row < garden.length && col >= 0 && col < garden[row].length;
    }
}
