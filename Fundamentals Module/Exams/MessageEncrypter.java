import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageEncrypter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String regex = "([\\*@?])(?<message>[A-Z][a-z]{2,})\\1\\: \\[(?<group1>[A-Za-z]+)\\]\\|\\[(?<group2>[A-Za-z]+)\\]\\|\\[(?<group3>[A-Za-z]+)\\]\\|$";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i <number ; i++) {
            String message = scanner.nextLine();
            Matcher matcher = pattern.matcher(message);

            if(matcher.find()){
                String messages = matcher.group("message");
                String group1 = matcher.group("group1");
                String group2 = matcher.group("group2");
                String group3 = matcher.group("group3");

                char ch1 = group1.charAt(0);
                char ch2 = group2.charAt(0);
                char ch3 = group3.charAt(0);
                System.out.printf("%s: ",messages);
                System.out.print((int) ch1 +" "+(int)ch2 + " "+(int)ch3);
                System.out.println();

            } else {
                System.out.println("Valid message not found!");
            }
        }
    }
}
