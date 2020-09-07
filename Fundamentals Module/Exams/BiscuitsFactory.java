import java.util.Scanner;

public class BiscuitsFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int amountOfBiscuitPerDay = Integer.parseInt(scanner.nextLine());
        int workers = Integer.parseInt(scanner.nextLine());
        int numberBiscuitCompetition = Integer.parseInt(scanner.nextLine());

        int everyThirdDay = 0;
        double countAmountProduction = 0.0;

        for (int i = 1; i <= 30; i++) {
            everyThirdDay++;
            if (everyThirdDay == 3) {
                double count = Math.floor(workers*amountOfBiscuitPerDay) - ((workers * amountOfBiscuitPerDay) * 0.25);
                countAmountProduction = countAmountProduction + count;
                everyThirdDay=0;
            } else {
                countAmountProduction = countAmountProduction + (workers * amountOfBiscuitPerDay);
            }
        }
        System.out.printf("You have produced %.0f biscuits for the past month.",countAmountProduction);
        System.out.println();

        double countDifferent = 0.0;
        double countPercent = 0.0;
        if(countAmountProduction>numberBiscuitCompetition){
            countDifferent = countAmountProduction - numberBiscuitCompetition;
            countPercent = (countDifferent /numberBiscuitCompetition) * 100;
            System.out.printf("You produce %.2f percent more biscuits.",countPercent);
        } else {
           countDifferent = numberBiscuitCompetition - countAmountProduction;
           countPercent = (countDifferent / numberBiscuitCompetition) * 100;
            System.out.printf("You produce %.2f percent less biscuits.",countPercent);
        }
    }
}
