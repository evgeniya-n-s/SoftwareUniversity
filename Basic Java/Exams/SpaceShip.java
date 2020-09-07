import java.util.Scanner;

public class SpaceShip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double width = Double.parseDouble(scanner.nextLine());
        double length = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());
        double averageAstro = Double.parseDouble(scanner.nextLine());

        double volumeRacket = width * length * height;
        double volumeRoom = (averageAstro + 0.4) * 2 * 2;
        double totalCount = Math.floor(volumeRacket / volumeRoom);

        if (totalCount>= 3 && totalCount <= 10){
            System.out.printf("The spacecraft holds %.0f astronauts.", totalCount);
        } else if (totalCount > 10){
            System.out.println("The spacecraft is too big.");
        } else {
            System.out.println("The spacecraft is too small.");
        }
    }
}
