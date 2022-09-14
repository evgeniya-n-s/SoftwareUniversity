import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String input = scanner.nextLine ();

        TreeMap<Character,Integer> characterSymbols = new TreeMap<> (  );

        for (char symbol : input.toCharArray ()) {
            characterSymbols.putIfAbsent ( symbol,0 );
            characterSymbols.put ( symbol,characterSymbols.get ( symbol )+1 );
        }

        System.out.println ( characterSymbols.entrySet ()
                .stream ()
                .map ( e -> String.format ( "%c: %d time/s", e.getKey (), e.getValue () ) )
                .collect ( Collectors.joining ( System.lineSeparator () ) ) );
    }
}
