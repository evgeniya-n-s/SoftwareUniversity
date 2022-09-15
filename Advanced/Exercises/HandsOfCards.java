import java.util.*;

public class HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String input = scanner.nextLine ();
        LinkedHashMap<String, LinkedHashSet<String>> players = new LinkedHashMap<> ();

        while (!input.endsWith ( "JOKER" )) {
            String[] tokens = input.split ( ": " );
            String names = tokens[0];
            String[] hands = tokens[1].split ( ", " );

            players.putIfAbsent ( names, new LinkedHashSet<> () );

            LinkedHashSet<String> string = players.get ( names );
            string.addAll ( Arrays.asList ( hands ) );
            players.put ( names, string );

            input = scanner.nextLine ();
        }
        players.forEach ( (key,value)->{
            int totalPower = 0;

            for (String card : value) {
                totalPower += getPower ( card.substring ( 0,card.length ()-1 ) ) *
                        getMultiPlayer ( card.substring ( card.length ()-1 ) );
            }
            System.out.println (String.format ( "%s: %d",key,totalPower ));
        } );
    }

    public static int getPower(String power) {
        switch (power) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return 0;
        }
    }

    private static int getMultiPlayer(String type) {
        switch (type) {
            case "C":
                return 1;
            case "D":
                return 2;
            case "H":
                return 3;
            case "S":
                return 4;
            default:
                return 0;
        }
    }
}
