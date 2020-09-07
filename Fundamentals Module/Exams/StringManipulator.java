import java.lang.reflect.InaccessibleObjectException;
import java.util.Scanner;

public class StringManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        String input = scanner.nextLine();

        while (!"End".equals(input)) {
            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "Translate":
                    String ch = tokens[1];
                    String replacement = tokens[2];
                    if (string.contains(ch)) {
                        string = string.replace(ch, replacement);
                        System.out.println(string);
                    }
                    break;
                case "Includes":
                    String inputString = tokens[1];
                    if (string.contains(inputString)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Start":
                    String startString = tokens[1];
                    if (string.startsWith(startString)) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
                    break;
                case "Lowercase":
                    string = string.toLowerCase();
                    System.out.println(string);
                    break;
                case "FindIndex":
                    String findChar = tokens[1];
                    int result = string.lastIndexOf(findChar);
                    System.out.println(result);
                    break;
                case "Remove":
                    int startIndex = Integer.parseInt(tokens[1]);
                    int count = Integer.parseInt(tokens[2]);
                    StringBuilder sb = new StringBuilder(string);
                    sb = sb.replace(startIndex,startIndex+count,"");
                    string = sb.toString();
                    System.out.println(string);
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
