import java.util.Scanner;

public class ExperienceGaining {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double neededExperience = Double.parseDouble(scanner.nextLine());
        int countOfBattle = Integer.parseInt(scanner.nextLine());
        double experienceEarnedPerBattle = Double.parseDouble(scanner.nextLine());

        double countExperience = 0.0;
        int countEveryThird = 0;
        int countEveryFifth = 0;
        double currentNumber = 0.0;
        int countBattle = 0;

        for (int i = 0; i < countOfBattle; i++) {
            currentNumber = experienceEarnedPerBattle;
            countExperience = countExperience + experienceEarnedPerBattle;
            countEveryThird++;
            countEveryFifth++;

            if (countEveryThird == 3) {
                countExperience = countExperience + (currentNumber * 0.15);
                countEveryThird = 0;
            }
            if (countEveryFifth == 5) {
                countExperience = countExperience - (currentNumber * 0.1);
                countEveryFifth = 0;
            }

            if (countExperience >= neededExperience) {
                countBattle = i+1;
            }
            if (i < countOfBattle - 1) {
                experienceEarnedPerBattle = Double.parseDouble(scanner.nextLine());
            }
        }
        if (neededExperience == countExperience || countExperience > neededExperience) {
            System.out.printf("Player successfully collected his needed experience for %d battles.", countBattle);
        } else {
            double needExperience = neededExperience - countExperience;
            System.out.printf("Player was not able to collect the needed experience, %.2f more needed.", needExperience);
        }
    }
}
