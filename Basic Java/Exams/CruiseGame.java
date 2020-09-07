import java.util.Scanner;

public class CruiseGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nameGamer = scanner.nextLine();
        int numberGames = Integer.parseInt(scanner.nextLine());

        int countGames = 0;

        double countPointV = 0.0;
        double countPointT = 0.0;
        double countPointB = 0.0;

        int countVGame = 0;
        int countTGame = 0;
        int countBGame = 0;

        while (countGames < numberGames){
            String nameGame = scanner.nextLine();
            int pointsGame = Integer.parseInt(scanner.nextLine());
            if (nameGame.equals("volleyball")){
                countPointV += (pointsGame * 1.07);
                countVGame ++;
            } else if (nameGame.equals("tennis")){
                countPointT += (pointsGame * 1.05);
                countTGame ++;
            } else if (nameGame.equals("badminton")){
                countPointB += (pointsGame * 1.02);
                countBGame ++;
            }
            countGames++;
        }
        double checkGameV = (countPointV / countVGame);
        double checkGameT = (countPointT / countTGame);
        double checkGameB = (countPointB / countBGame);
        double average = Math.floor(countPointV + countPointT + countPointB);
        if ((checkGameV >=75) && (checkGameT>=75) && (checkGameB >= 75)){
            System.out.printf("Congratulations, %s! You won the cruise games with %.0f points.", nameGamer, average);
        } else {
            System.out.printf("Sorry, %s, you lost. Your points are only %.0f.", nameGamer, average);
        }


    }
}
