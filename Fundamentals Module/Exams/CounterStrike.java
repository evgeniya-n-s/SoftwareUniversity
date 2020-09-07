import java.util.Scanner;

public class CounterStrike {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputEnergy = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();

        int countWinBattle = 0;
        int countAllWinBattle = 0;
        boolean isWin=false;

        while (!(input.equals("End of battle"))) {
            int distance = Integer.parseInt(input);
            if (distance <= inputEnergy) {
                countAllWinBattle++;
                countWinBattle++;
                inputEnergy = inputEnergy - distance;
                if (countWinBattle == 3) {
                    inputEnergy = inputEnergy + 3;
                    countWinBattle = 0;
                }
            } else {
                isWin=true;
                break;
            }
            input = scanner.nextLine();
        }
            if(isWin){
                String output = String.format("Not enough energy! Game ends with %d won battles and %d energy", countAllWinBattle, inputEnergy);
                System.out.println(output);
            }else {
                String outputWin = String.format("Won battles: %d. Energy left: %d", countAllWinBattle, inputEnergy);
                System.out.println(outputWin);
            }
    }
}
