import java.util.Scanner;

public class MatrixofPalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String[] input = scanner.nextLine ().split ( "\\s+" );
        int rows = Integer.parseInt ( input[0] );
        int columns = Integer.parseInt ( input[1] );

        String[][] matrix = new String[rows][columns];

        char symbol = 'a';
        for (int row = 0; row <rows ; row++) {
            for (int col = 0; col <columns ; col++) {
                String palindrome = "" +symbol + (char)(symbol+col)+ symbol;
                matrix[row][col]= palindrome;
                System.out.print (palindrome + " ");
            }
            symbol = (char)(symbol+1);
            System.out.println ();
        }
    }
}
