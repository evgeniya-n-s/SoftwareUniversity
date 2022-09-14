import java.util.Scanner;

public class Bee {
    public static int rowBee = 0;
    public static int columnBee = 0;
    public static int flowers = 0;
    public static int check = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int numberOfSquare = Integer.parseInt ( scanner.nextLine () );
        char[][] matrix = new char[numberOfSquare][numberOfSquare];

        for (int i = 0; i < matrix.length; i++) {
            String rowOfTerritory = scanner.nextLine ();
            if (rowOfTerritory.contains ( "B" )) {
                rowBee = i;
                columnBee = rowOfTerritory.indexOf ( "B" );
            }
            matrix[i] = rowOfTerritory.toCharArray ();
        }

        String command = scanner.nextLine ();
        while (!command.equals ( "End" )) {
            //row-1
            if (command.equals ( "up" )) {
                if (isInBound ( rowBee - 1, columnBee, matrix )) {
                    if (moveBee ( rowBee, columnBee, rowBee - 1, columnBee, matrix )) {
                        rowBee--;
                        if(check>0){
                            if (isInBound ( rowBee - 1, columnBee, matrix )){
                               if (moveBee ( rowBee, columnBee, rowBee - 1, columnBee, matrix )) {
                                    rowBee--;
                                    check=0;
                                }
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    matrix[rowBee][columnBee]='.';
                    System.out.println ("The bee got lost!");
                    break;
                }

            }//row+1
            else if (command.equals ( "down" )) {
                if (isInBound ( rowBee + 1, columnBee, matrix )) {
                    if (moveBee ( rowBee, columnBee, rowBee + 1, columnBee, matrix )) {
                        rowBee++;
                        if(check>0){
                            if (isInBound ( rowBee + 1, columnBee, matrix )){
                                if (moveBee ( rowBee, columnBee, rowBee + 1, columnBee, matrix )) {
                                    rowBee++;
                                    check=0;
                                }
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    matrix[rowBee][columnBee]='.';
                    System.out.println ("The bee got lost!");
                    break;
                }
            }//col-1
            else if (command.equals ( "left" )) {
                if (isInBound ( rowBee, columnBee - 1, matrix )) {
                    if (moveBee ( rowBee, columnBee, rowBee, columnBee - 1, matrix )) {
                        columnBee--;
                        if(check>0){
                            if (isInBound ( rowBee , columnBee-1, matrix )){
                                if (moveBee ( rowBee, columnBee, rowBee, columnBee-1, matrix )) {
                                    columnBee--;
                                    check=0;
                                }
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    matrix[rowBee][columnBee]='.';
                    System.out.println ("The bee got lost!");
                    break;
                }
            }//col+1
            else if (command.equals ( "right" )) {
                if (isInBound ( rowBee, columnBee + 1, matrix )) {
                    if (moveBee ( rowBee, columnBee, rowBee, columnBee + 1, matrix )) {
                        columnBee++;
                        if(check>0){
                            if (isInBound ( rowBee , columnBee+1, matrix )){
                                if (moveBee ( rowBee, columnBee, rowBee, columnBee+1, matrix )) {
                                    columnBee++;
                                    check=0;
                                }
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    matrix[rowBee][columnBee]='.';
                    System.out.println ("The bee got lost!");
                    break;
                }
            }
            command = scanner.nextLine ();
        }

        if(flowers >=5){
            System.out.println (String.format ( "Great job, the bee manage to pollinate %d flowers!" ,flowers));
        } else {
            int neededFlowers = 5-flowers;
            System.out.println (String.format ( "The bee couldn't pollinate the flowers, she needed %d flowers more",neededFlowers ));
        }

        printMatrix ( matrix );
    }

    private static boolean isInBound(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static boolean moveBee(int oldRow, int oldCol, int newRow, int newCol, char[][] matrix) {
        if (matrix[newRow][newCol] == '.') {
            matrix[oldRow][oldCol] = '.';
            matrix[newRow][newCol] = 'B';
        } else if (matrix[newRow][newCol] == 'f') {
            matrix[oldRow][oldCol] = '.';
            matrix[newRow][newCol] = 'B';
            flowers++;
        } else if (matrix[newRow][newCol] == 'O') {
            matrix[oldRow][oldCol] = '.';
            matrix[newRow][newCol] = 'B';
            check++;
        } else {
            return false;
        }
        return true;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print ( matrix[row][col] );
            }
            System.out.println ();
        }
    }
}
