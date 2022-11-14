package NeedforSpeed;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        FamilyCar familyCar = new FamilyCar ( 23,105 );
        SportCar sportCar = new SportCar ( 40,350 );

        familyCar.drive ( 5 );
        System.out.println (familyCar.getFuel ());
    }
}
