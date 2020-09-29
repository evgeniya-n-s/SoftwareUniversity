import java.util.LinkedHashMap;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String input = scanner.nextLine ();
        LinkedHashMap<String,String> emails = new LinkedHashMap<> (  );

        int count = 0;
        String temp = "";

        while (!input.equals ( "stop" )){
            if(count%2==0){
                temp = input;
            } else {
                if(!input.endsWith ( ".us" ) && !input.endsWith ( ".uk" ) && !input.endsWith ( ".com" )){
                    emails.putIfAbsent ( temp,input );
                }
            }
            count++;
            input =scanner.nextLine ();
        }
        emails.entrySet ().stream ().forEach ( e-> System.out.println (String.format ( "%s -> %s",e.getKey (),e.getValue () )) );
    }
}
