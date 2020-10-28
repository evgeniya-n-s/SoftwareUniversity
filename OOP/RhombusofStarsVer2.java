import java.util.Scanner;

public class RhombusofStarsVer2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int n = Integer.parseInt ( scanner.nextLine ());

        printTop(n);


        printButton ( n-1 );
    }

    private static void printButton(int count) {
        for (int i = 1; i <= count; i++) {
            printString (i," ");
            printString ( count-(i-1), "* " );
            System.out.println ();
        }
    }

    private static void printTop(int row) {
        for (int i = 1; i <= row; i++) {
            printString (row-i," ");
            printString ( i, "* " );
            System.out.println ();
        }
    }

    private static void printString(int count, String str){
        for (int i = 0; i < count; i++) {
            System.out.print(str);
        }
    }
}
