package ShoppingSpree;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner ( System.in );

        List<Person> people = Arrays.stream (scanner.nextLine ().split ( ";" ))
                .map ( personInfo -> {
                    String[] tokens = personInfo.split ( "=" );
                    try {
                        return new Person ( tokens[0],Double.parseDouble ( tokens[1] ) );
                    } catch (IllegalArgumentException ex){
                        System.out.println (ex.getMessage ());
                    }
                    return null;
                } )
                .collect( Collectors.toList());

        List<Product> products = Arrays.stream (scanner.nextLine ().split ( ";" ))
                .map ( personInfo -> {
                    String[] tokens = personInfo.split ( "=" );
                    try {
                        return new Product ( tokens[0],Double.parseDouble ( tokens[1] ) );
                    } catch (IllegalArgumentException ex){
                        System.out.println (ex.getMessage ());
                    }
                    return null;
                } )
                .collect( Collectors.toList());

        if(people.stream ().anyMatch ( Objects::isNull )
        || products.stream ().anyMatch ( Objects::isNull )){
            return;
        }

        String input = scanner.nextLine ();

        while (!input.equals ( "END" )){
            String[] tokens = input.split ( "\\s+" );

            String person = tokens[0];
            String product = tokens[1];

            for (Person p: people){
                if(p.getName ().equals ( person )){
                    try{
                        Product productToBuy = products.stream ().filter ( prod -> prod.getName ().equals ( product ) )
                                .findFirst ()
                                .orElseThrow (Exception::new);

                        p.buyProduct ( productToBuy );
                        System.out.println (person + " bought " + product);
                    } catch (IllegalArgumentException ex){
                        System.out.println (ex.getMessage ());
                    }
                }
            }
            input = scanner.nextLine ();
        }

        for (Person p: people){
            System.out.println (p.toString ());
        }
    }
}
