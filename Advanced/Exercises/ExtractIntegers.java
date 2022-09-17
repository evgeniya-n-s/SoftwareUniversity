import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) throws FileNotFoundException {

        String path = "input.txt";

        Scanner scanner = new Scanner ( new FileInputStream ( path ) );

        PrintWriter writer = new PrintWriter ( "04.ExtractIntegersOutput.txt" );

        while (scanner.hasNext ()){
            if(scanner.hasNextInt ()) {
                int number = scanner.nextInt ();
                writer.println ( number );
            }
            scanner.next ();
        }
        writer.flush ();
    }
}
