import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        String input = scanner.nextLine ();
        LinkedHashSet<String> parkingLot = new LinkedHashSet<> (  );

        while (!input.equals ( "END" )){
            int indexOf = input.indexOf ( ", " );

            String command = input.substring ( 0,indexOf );
            String registration = input.substring ( indexOf+2 );

            if(command.equals ( "IN" )){
                parkingLot.add ( registration );
            } else {
                parkingLot.remove ( registration );
            }

            input=scanner.nextLine ();
        }

        if(parkingLot.isEmpty ()){
            System.out.println ("Parking Lot is Empty");
        } else {
            System.out.println ( String.join ( System.lineSeparator (), parkingLot ) );
        }
    }
}
