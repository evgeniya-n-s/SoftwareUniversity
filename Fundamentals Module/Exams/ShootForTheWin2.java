import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShootForTheWin2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> inputTarget = readInput(scanner);
        String command = scanner.nextLine();

        int target = 0;

        while (!"End".equals(command)) {
            int number = Integer.parseInt(command);

            if (number >= 0 && number < inputTarget.size()) {
                int temp = inputTarget.get(number);

                if(temp == -1){
                    int sum = 0;
                } else {
                    for (int i = 0; i < inputTarget.size(); i++) {
                        if (inputTarget.get(i) == -1) {
                            int sum = 0;
                        } else if (inputTarget.get(i) > temp) {
                            int subtraction = inputTarget.get(i) - temp;
                            inputTarget.set(i, subtraction);
                        } else {
                            int sum = temp + inputTarget.get(i);
                            inputTarget.set(i, sum);
                        }
                    }
                }
                inputTarget.set(number, -1);
                target++;
            } else {
                int sum = 0;
            }
            command = scanner.nextLine();
        }
        printList(inputTarget, target);
    }

    private static void printList(List<Integer> inputTarget, int target) {
        System.out.printf("Shot targets: %d -> ", target);
        for (Integer numbers : inputTarget) {
            System.out.print(numbers + " ");
        }
    }

    private static List<Integer> readInput(Scanner scanner) {
        List<Integer> input = new ArrayList<>();
        String[] numbers = scanner.nextLine().split(" ");

        for (String number : numbers) {
            input.add(Integer.parseInt(number));
        }
        return input;
    }
}
