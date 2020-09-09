import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiiDetector2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> emojis = new ArrayList<>();
        Pattern pattern1 = Pattern.compile("([0-9])");
        Pattern pattern2 = Pattern.compile("(\\*{2}|:{2})([A-Z][a-z]{2,})\\1");
        int treshold = 1;

        Matcher matcher = pattern1.matcher(input);
        while (matcher.find()) {
            treshold *= Integer.parseInt(matcher.group(1));
        }

        Matcher matcher2 = pattern2.matcher(input);

        while (matcher2.find()) {
            emojis.add(matcher2.group());
        }

        System.out.println(String.format("Cool threshold: %d", treshold));
        System.out.println(String.format("%s emojis found in the text. The cool ones are:", emojis.size()));
        List<String> coolEmojies = new ArrayList<>();
        for (int j = 0; j < emojis.size(); j++) {
            int sumLetter = 0;
            String element = emojis.get(j);
            for (int k = 0; k < element.length(); k++) {
                char c = element.charAt(k);
                if (Character.isLetter(c))
                    sumLetter += c;
            }
            if (sumLetter > treshold) {
                System.out.println(element);
            }
        }
    }

}
