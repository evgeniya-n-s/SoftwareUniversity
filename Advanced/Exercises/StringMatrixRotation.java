import java.util.ArrayList;
import java.util.Scanner;

public class StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String rotation = scanner.nextLine ();
        int degree = Integer.parseInt ( rotation.split ( "[()]+" )[1] )%360;

        String input= scanner.nextLine ();
        ArrayList<String> linesMatrix = new ArrayList<> (  );
        int maxLength = input.length ();

        while (!input.equals ( "END" )){
            linesMatrix.add ( input );
            input = scanner.nextLine ();
            if(input.length () >maxLength){
                maxLength = input.length ();
            }
        }

        int rows = linesMatrix.size ();
        int column = maxLength;
        char[][] matrix = new char[rows][column];

        for (int row = 0; row <rows ; row++) {
            for (int col = 0; col <column ; col++) {
                if(col<linesMatrix.get ( row ).length ()){
                    matrix[row][col] = linesMatrix.get ( row ).charAt ( col );
                } else {
                    matrix[row][col] = ' ';
                }
            }
        }

        if(degree == 90){
            for (int col = 0; col <column ; col++) {
                for (int row = rows-1; row >=0 ; row--) {
                    System.out.print(matrix[row][col]);
                }
                System.out.println ();
            }
        } else if(degree == 180){
            for (int row = rows-1; row >=0 ; row--) {
                for (int col = column-1; col >=0 ; col--) {
                    System.out.print (matrix[row][col]);
                }
                System.out.println ();
            }
        } else if(degree == 270){
            for (int col = column-1; col >=0 ; col--) {
                for (int row = 0; row <rows ; row++) {
                    System.out.print (matrix[row][col]);
                }
                System.out.println ();
            }
        } else {
            for (int row = 0; row <rows ; row++) {
                for (int col = 0; col <column ; col++) {
                    System.out.print (matrix[row][col]);
                }
                System.out.println ();
            }
        }
    }
}
