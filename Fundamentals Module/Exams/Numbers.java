import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(" ");
        List<Integer> numbers = new ArrayList<>();

        for (String s : input) {
            numbers.add(Integer.parseInt(s));
        }

        double countSumNumbers = 0;
        double countNumbers = 0;

        if(numbers.size()==1){
            System.out.print("No");
            return;
        }

        for (int i = 0; i < numbers.size(); i++) {
            countSumNumbers = countSumNumbers + numbers.get(i);
            countNumbers++;
        }

        double countAverage = (countSumNumbers * 1.0) / (countNumbers * 1.0);
        List<Integer> topIntegers = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            int compareNumber = numbers.get(i);
            if (compareNumber > countAverage) {
                topIntegers.add(compareNumber);
            }
        }

        Collections.sort(topIntegers);
        Collections.reverse(topIntegers);
        if (topIntegers.size() > 5) {
            int countSize = 0;
            for (int i = 0; i < topIntegers.size(); i++) {
                System.out.print(topIntegers.get(i) + " ");
                countSize++;
                if (countSize == 5) {
                    break;
                }
            }
        } else {
            for (Integer topInteger : topIntegers) {
                System.out.print(topInteger + " ");
            }
        }
    }
}