import java.util.Scanner;

public class DartsTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstPoints = Integer.parseInt(scanner.nextLine());

        int counter = 0;
        boolean exit = false;

        while (firstPoints>0){
            String target = scanner.nextLine();
            counter ++;
            if (target.equals("bullseye")){
                exit = true;
                break;
            }

            int gamerPoint = Integer.parseInt(scanner.nextLine());

            if (target.equals("number section")){
                firstPoints = firstPoints - gamerPoint;

            } else if (target.equals("double ring")){
                gamerPoint = gamerPoint * 2;
                firstPoints = firstPoints - gamerPoint;
            } else if (target.equals("triple ring")){
                gamerPoint = gamerPoint * 3;
                firstPoints = firstPoints - gamerPoint;
            }
            if (firstPoints < 0){
                break;
            }
        }
        if (exit){
            System.out.printf("Congratulations! You won the game with a bullseye in %d moves!", counter);
        } else if (firstPoints == 0) {
            System.out.printf("Congratulations! You won the game in %d moves!", counter);
        } else{
            System.out.printf("Sorry, you lost. Score difference: %d.",Math.abs(firstPoints));
        }
    }
}
