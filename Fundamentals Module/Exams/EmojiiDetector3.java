import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiiDetector3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regexWord = "(\\*{2}|:{2})([A-Z][a-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regexWord);

        String regexNumber = "(?<numbers>[0-9])";
        Pattern pattern1 = Pattern.compile(regexNumber);

        String input = scanner.nextLine();
        Matcher matcher = pattern.matcher(input);
        Matcher matcher1 = pattern1.matcher(input);

        List<String> listWord = new ArrayList<>();

        int number = 1;
        boolean isNumber = false;
        while (matcher1.find()) {
            int numbers = Integer.parseInt(matcher1.group("numbers"));
            number = number * numbers;
            isNumber = true;
        }

        int emojiCount = 0;
        while (matcher.find()) {
            String word = matcher.group();
            String findWord = "";
            for (int i = 0; i <word.length() ; i++) {
                if(Character.isLetter(word.charAt(i))){
                    findWord = findWord + word.charAt(i);
                }
            }

            int count = 0;
            for (int i = 0; i <findWord.length() ; i++) {
                count = count + findWord.charAt(i);
            }
            if(count>=number) {
                listWord.add(word);
            }
            emojiCount++;
        }

        if(isNumber){
            System.out.println(String.format("Cool threshold: %d",number));
        }
        if(emojiCount!=0){
            System.out.println(String.format("%d emojis found in the text. The cool ones are:",emojiCount));
        }
        if(!listWord.isEmpty()){
            listWord.forEach(e-> System.out.println(String.format("%s",e.toString().replaceAll("[\\[\\],]",""))));
        }
    }
}
