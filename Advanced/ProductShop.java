import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String line = scanner.nextLine ();
        Map<String, Map<String,Double>> costs = new TreeMap<> (  );

        while (!line.equals ( "Revision" )){
            String[] tokens = line.split ( ", " );
            String shops = tokens[0];
            String product = tokens[1];
            Double price = Double.parseDouble ( tokens[2]);

            costs.putIfAbsent ( shops, new LinkedHashMap<> (  ) );
            costs.get ( shops ).put ( product,price );

            line=scanner.nextLine ();
        }

        costs.entrySet ()
                .stream ()
                .forEach ( entry->{
                    System.out.println (entry.getKey () + "->");
                    entry.getValue ().entrySet ()
                            .forEach ( innerEntry->{
                                System.out.println (String.format ( "Product: %s, Price: %.1f",
                                        innerEntry.getKey (),innerEntry.getValue ()));
                            } );
                } );

    }
}
