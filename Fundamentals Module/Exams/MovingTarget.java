import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovingTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> inputNumbersList = readInputNumbers(scanner);
        String command = scanner.nextLine();

        while (!"End".equals(command)) {
            String[] tokens = command.split(" ");
            if("Shoot".equals(tokens[0])){
                int index = Integer.parseInt(tokens[1]);
                int number = Integer.parseInt(tokens[2]);
                if(index>=0&&index<=inputNumbersList.size()){
                    int temp = inputNumbersList.get(index);
                    temp = temp - number;
                    if(temp>0){
                        inputNumbersList.set(index,temp);
                    }else {
                        inputNumbersList.remove(index);
                    }
                }
            }
            if("Add".equals(tokens[0])){
                int index = Integer.parseInt(tokens[1]);
                int number = Integer.parseInt(tokens[2]);
                if(index>=0&&index<=inputNumbersList.size()){
                    inputNumbersList.add(index,number);
                } else {
                    System.out.println("Invalid placement!");
                }
            }
            if("Strike".equals(tokens[0])){
                int index = Integer.parseInt(tokens[1]);
                int number = Integer.parseInt(tokens[2]);

                int temp = index - number;
                if (temp<0){
                    System.out.println("Strike missed!");
                } else {
                int leftSide = Math.max(index - number,0);
                int rightSide = Math.min(index+number,inputNumbersList.size()-1);

                    for (int i = rightSide; i >= leftSide; i--) {
                        inputNumbersList.remove(i);
                    }
                }
            }
            command = scanner.nextLine();
        }
        printResultList(inputNumbersList);
    }

    private static void printResultList(List<Integer> inputNumbersList) {
        for (int i = 0; i <inputNumbersList.size() ; i++) {
            if(i==0){
                System.out.print(inputNumbersList.get(i));
            } else {
                System.out.print("|" + inputNumbersList.get(i));
            }
        }
    }

    private static List<Integer> readInputNumbers(Scanner scanner) {
        List<Integer> inputNumber = new ArrayList<>();
        String[] number = scanner.nextLine().split("\\s+");

        for (String s : number) {
            inputNumber.add(Integer.parseInt(s));
        }
        return inputNumber;
    }
}
