import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int lines = Integer.parseInt ( scanner.nextLine () );
        TreeSet<String> periodicTable = new TreeSet<> (  );

        while (lines-- >0){
            periodicTable.addAll ( Arrays.asList (scanner.nextLine ().split ( "\\s+" )) );
        }

        periodicTable.forEach ( e-> System.out.print (e +" ") );
    }
}
