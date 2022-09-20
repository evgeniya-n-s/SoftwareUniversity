import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SetsofElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String[] input = scanner.nextLine ().split ( "\\s+" );
        int firstSetNumbers = Integer.parseInt ( input[0]);
        int secondSetNumbers = Integer.parseInt ( input[1]);

        LinkedHashSet<Integer> firstSet = new LinkedHashSet<> (  );
        LinkedHashSet<Integer> secondSet = new LinkedHashSet<> (  );

        while (firstSetNumbers-- >0){
            int firstNumbers = Integer.parseInt ( scanner.nextLine () );
            firstSet.add ( firstNumbers );
        }

        while (secondSetNumbers-- >0){
            int secondNumbers = Integer.parseInt ( scanner.nextLine () );
            secondSet.add ( secondNumbers );
        }

        firstSet.retainAll ( secondSet );

        String resultSet = firstSet.stream ().map ( i->i+" " ).collect( Collectors.joining());

        System.out.println (resultSet);
    }
}
