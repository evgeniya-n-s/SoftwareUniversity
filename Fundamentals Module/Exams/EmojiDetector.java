import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regexWords = "([:*]{2})(?<text>[A-Z][a-z]{2,})\\\\1";
        String regexDigits = "\\d+";
        Pattern patternWords = Pattern.compile(regexWords);
        Pattern patternDigits = Pattern.compile(regexDigits);

        String input = scanner.nextLine();
        Matcher matcherWords = patternWords.matcher(input);
        Matcher matcherDigits = patternDigits.matcher(input);

        int targetThreshold = 1;
        while (matcherDigits.find()){
            String foundDigits = matcherDigits.group();
            int temp = Integer.parseInt(foundDigits);
            int temp2=0;

            while (temp>0) {
                temp2 = temp;
                temp = temp % 10;
                targetThreshold = targetThreshold*temp;
                temp2 = temp2 / 10;
                temp=temp2;
            }
        }

        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        int threshold = 0;
        int countOfAllEmojis = 0;

        while (matcherWords.find()){
            String matcherText = matcherWords.group("text");
            String matcherFinalText = matcherWords.group();
            sb.append(matcherText);
            countOfAllEmojis++;
            for (int i = 0; i <sb.length() ; i++) {
                char symbol = sb.charAt(i);
                threshold += symbol;
            }
            if(threshold>targetThreshold){
                list.add(matcherFinalText);
            }
            sb.delete(0,sb.length());
            threshold = 0;
        }
        System.out.println(String.format("Cool threshold: %d",targetThreshold));
        System.out.println(String.format("%d emojis found in the text. The cool ones are:",countOfAllEmojis));
        for (String s : list) {
            System.out.println(s);
        }
    }
}
