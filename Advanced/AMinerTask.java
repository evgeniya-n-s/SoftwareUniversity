import java.nio.charset.IllegalCharsetNameException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class AMinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String input = scanner.nextLine ();
        LinkedHashMap<String, Integer> miner = new LinkedHashMap<> (  );

        int count = 0;
        String temp = "";

        while (!input.equals ( "stop" )){

            if(count%2==0){
                miner.putIfAbsent ( input,0 );
                temp = input;
            }else {
                if(miner.containsKey ( temp )){
                    int convert = Integer.parseInt ( input );
                    miner.put ( temp,miner.get ( temp )+convert );
                }
            }
            count++;
            input=scanner.nextLine ();
        }
        miner.entrySet ().stream ().forEach ( e-> System.out.println (String.format ( "%s -> %s",e.getKey (),e.getValue () )) );
    }
}
