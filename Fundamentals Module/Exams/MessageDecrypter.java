import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageDecrypter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String regex = "^([\\$%?])(?<tag>[A-Z][a-z]{2,})\\1\\: \\[(?<number1>[0-9]+)\\]\\|\\[(?<number2>[0-9]+)\\]\\|\\[(?<number3>[0-9]+)\\]\\|$";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i <number ; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);

            if(matcher.find()){
                String numberOne = matcher.group("number1");
                String numberTwo = matcher.group("number2");
                String numberThree = matcher.group("number3");
                String tag = matcher.group("tag");

                int firstNumber = Integer.parseInt(numberOne);
                int secondNumber = Integer.parseInt(numberTwo);
                int thirdNumber = Integer.parseInt(numberThree);

                char ch1 = (char)firstNumber;
                char ch2 = (char)secondNumber;
                char ch3 = (char)thirdNumber;

                System.out.print(tag + ": ");
                System.out.print(ch1);
                System.out.print(ch2);
                System.out.print(ch3);
                System.out.println();
            } else {
                System.out.println("Valid message not found!");
            }
        }
    }
}
