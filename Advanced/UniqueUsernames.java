import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int number = Integer.parseInt ( scanner.nextLine () );
        LinkedHashSet<String> unique = new LinkedHashSet<> ();

        while (number-- >0){
            String input = scanner.nextLine ();
            unique.add ( input );
        }

        System.out.println (String.join ( System.lineSeparator (), unique ));
    }
}
