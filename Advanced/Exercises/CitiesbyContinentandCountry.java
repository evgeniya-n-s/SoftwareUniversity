import java.util.*;

public class CitiesbyContinentandCountry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        int n = Integer.parseInt ( scanner.nextLine () );
        Map<String,Map<String, List<String>>> data = new LinkedHashMap<> (  );

        while (n-- >0){
            String[] tokens = scanner.nextLine ().split ( "\\s+" );
            String continents = tokens[0];
            String country = tokens[1];
            String city = tokens[2];

            data.putIfAbsent ( continents,new LinkedHashMap<> (  ) );
            data.get ( continents ).putIfAbsent ( country,new ArrayList<> (  ) );
            data.get ( continents ).get(country).add(city);
        }

        data.forEach (( key,value )->{
            System.out.println (key + ":");
            value.forEach ( (innerKey, innerValue )->{
                System.out.println ("  "+innerKey + " -> "
                +String.join ( ", ",innerValue ));
            });
        });
    }
}
