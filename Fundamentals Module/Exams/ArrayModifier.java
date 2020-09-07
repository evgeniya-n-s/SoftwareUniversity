import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayModifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        List<Integer> elements = new ArrayList<>();

        for (String s : input) {
            elements.add(Integer.parseInt(s));
        }

        String command = scanner.nextLine();
        while (!"end".equals(command)) {
            String[] tokens = command.split(" ");

            switch (tokens[0]) {
                case "swap":
                    int indexOneSwap = Integer.parseInt(tokens[1]);
                    int indexSecondSwap = Integer.parseInt(tokens[2]);

                    if ((indexOneSwap >= 0 && indexOneSwap <= elements.size()) && (indexSecondSwap >= 0 && indexSecondSwap <= elements.size())) {
                        int tempOneSwap = elements.get(indexOneSwap);
                        int tempSecondSwap = elements.get(indexSecondSwap);

                        elements.set(indexOneSwap, tempSecondSwap);
                        elements.set(indexSecondSwap, tempOneSwap);
                    }
                    break;
                case "multiply":
                    int indexOneMultiply = Integer.parseInt(tokens[1]);
                    int indexSecondMultiply = Integer.parseInt(tokens[2]);

                    if((indexOneMultiply>=0 && indexOneMultiply<=elements.size()) &&(indexSecondMultiply>=0 && indexSecondMultiply<=elements.size())){
                        int tempFirstMultiply = elements.get(indexOneMultiply);
                        int tempSecondMultiply = elements.get(indexSecondMultiply);
                        int multiplyFirstAndSecond = tempFirstMultiply * tempSecondMultiply;

                        elements.set(indexOneMultiply,multiplyFirstAndSecond);
                    }
                    break;
                case "decrease":
                    for (int i = 0; i <elements.size() ; i++) {
                        int number = elements.get(i);
                        elements.set(i,number-1);
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        for (int i = 0; i <elements.size() ; i++) {
            if(i==0){
                System.out.print(elements.get(i));
            }else {
                System.out.print(", " + elements.get(i));
            }
        }
    }
}
