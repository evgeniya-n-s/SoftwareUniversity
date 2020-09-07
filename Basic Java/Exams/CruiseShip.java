import java.util.Scanner;

public class CruiseShip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String typeCruise = scanner.nextLine();
        String typeCabin = scanner.nextLine();
        int night = Integer.parseInt(scanner.nextLine());

        double count = 0.0;

        if (typeCruise.equals("Mediterranean")){
            if ((typeCabin.equals("standard cabin")) && (night <= 7)){
                count = night * 27.50 * 4;
            } else if (typeCabin.equals("standard cabin")){
                count = (night * 27.50 * 4) * 0.75;
            }
            if ((typeCabin.equals("cabin with balcony")) && (night <= 7)){
                count = night * 30.20 * 4;
            } else if (typeCabin.equals("cabin with balcony")){
                count = (night * 30.20 * 4) * 0.75;
            }
            if ((typeCabin.equals("apartment")) && (night <= 7)){
                count = night * 40.50 * 4;
            } else if (typeCabin.equals("apartment")){
                count = (night * 40.50 * 4) * 0.75;
            }
        }

        if (typeCruise.equals("Adriatic")){
            if ((typeCabin.equals("standard cabin")) && (night <= 7)){
                count = night * 22.99 * 4;
            } else if (typeCabin.equals("standard cabin")){
                count = (night * 22.99 * 4) * 0.75;
            }
            if ((typeCabin.equals("cabin with balcony")) && (night <= 7)){
                count = night * 25 * 4;
            } else if (typeCabin.equals("cabin with balcony")){
                count = (night * 25 * 4) * 0.75;
            }
            if ((typeCabin.equals("apartment")) && (night <= 7)){
                count = night * 34.99 * 4;
            } else if (typeCabin.equals("apartment")){
                count = (night * 34.99 * 4) * 0.75;
            }
        }

        if (typeCruise.equals("Aegean")){
            if ((typeCabin.equals("standard cabin")) && (night <= 7)){
                count = night * 23 * 4;
            } else if (typeCabin.equals("standard cabin")){
                count = (night * 23 * 4) * 0.75;
            }
            if ((typeCabin.equals("cabin with balcony")) && (night <= 7)){
                count = night * 26.60 * 4;
            } else if (typeCabin.equals("cabin with balcony")){
                count = (night * 26.60 * 4) * 0.75;
            }
            if ((typeCabin.equals("apartment")) && (night <= 7)){
                count = night * 39.80 * 4;
            } else if (typeCabin.equals("apartment")){
                count = (night * 39.80 * 4) * 0.75;
            }
        }
        System.out.printf("Annie's holiday in the %s sea costs %.2f lv.", typeCruise, count);
    }
}
