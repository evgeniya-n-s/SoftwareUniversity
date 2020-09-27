import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class SoftUniParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String input = scanner.nextLine ();
        LinkedHashSet<String> vips = new LinkedHashSet<> (  );
        LinkedHashSet<String> regulars = new LinkedHashSet<> (  );

        while (!input.equals ( "PARTY" )){
            if(Character.isDigit (input.charAt ( 0 ) )){
                vips.add ( input );
            } else {
                regulars.add ( input );
            }
            input=scanner.nextLine ();
        }

        input= scanner.nextLine ();
        TreeSet<String> allGuess = new TreeSet<> ( vips );
        allGuess.addAll ( regulars );
        while (!input.equals ( "END" )){
            allGuess.remove ( input );
            input=scanner.nextLine ();
        }

        System.out.println (allGuess.size ());
        System.out.println ( String.join ( System.lineSeparator (), allGuess ) );
    }
}
