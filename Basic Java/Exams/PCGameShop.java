import java.util.Scanner;

public class PCGameShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberGames = Integer.parseInt(scanner.nextLine());

        int countHearthstone = 0;
        int countFornite = 0;
        int countOverwatch = 0;
        int countOthers = 0;

        for (int i = 1; i <= numberGames; i++){
            String nameGame = scanner.nextLine();
            if (nameGame.equals("Hearthstone")){
                countHearthstone++;
            } else if (nameGame.equals("Fornite")){
                countFornite++;
            }else if (nameGame.equals("Overwatch")){
                countOverwatch++;
            }else{
                countOthers++;
            }
        }
        double countHearthstonePercent = (1.0*countHearthstone/numberGames)*100;
        double countFornitePercent = (1.0*countFornite/numberGames)*100;
        double countOverwatchPercent = (1.0*countOverwatch/numberGames)*100;
        double countOthersPercent = (1.0*countOthers/numberGames)*100;
        System.out.printf("Hearthstone - %.2f%%%n",countHearthstonePercent);
        System.out.printf("Fornite - %.2f%%%n",countFornitePercent);
        System.out.printf("Overwatch - %.2f%%%n",countOverwatchPercent);
        System.out.printf("Others - %.2f%%%n",countOthersPercent);
    }
}
