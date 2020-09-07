import java.util.Scanner;

public class FootballTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String teamName = scanner.nextLine();
        int gamesCount = Integer.parseInt(scanner.nextLine());

        int totalPoints = 0;

        int totalWins = 0;
        int totalDraws = 0;

        for (int i = 0; i < gamesCount; i++) {
            String gameType = scanner.nextLine();

            if ("W".equals(gameType)) {
                totalPoints += 3;
                totalWins++;
            } else if ("D".equals(gameType)) {
                totalPoints += 1;
                totalDraws++;
            }
        }

        if (gamesCount == 0) {
            System.out.println(String.format("%s hasn't played any games during this season.", teamName));
        } else {
            double winRate = totalWins * 1.0 / gamesCount * 100;

            System.out.println(String.format("%s has won %d points during this season.", teamName, totalPoints));
            System.out.println("Total stats:");
            System.out.println(String.format("## W: %d", totalWins));
            System.out.println(String.format("## D: %d", totalDraws));
            System.out.println(String.format("## L: %d", gamesCount - totalWins - totalDraws));
            System.out.println(String.format("Win rate: %.2f%%", winRate));
        }
    }
}
