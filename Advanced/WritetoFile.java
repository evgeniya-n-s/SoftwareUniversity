import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WritetoFile {
    public static void main(String[] args) throws FileNotFoundException {

        String path = "input.txt";

        FileInputStream inputStream = new FileInputStream ( path );

        Scanner scanner = new Scanner(inputStream);

        String table = ",.!?";

        while (scanner.hasNext ()){
            String line = scanner.nextLine ();
            for (int i = 0; i <line.length () ; i++) {
                char symbol = line.charAt ( i );
                if(!table.contains ( String.valueOf ( symbol ) )){
                    System.out.print (symbol);
                }
            }
            System.out.println ();
        }

    }
}
