import java.util.Scanner;

public class TheMostPowerfulWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        double max = 0.0;
        String finalWord = "";

        while (!word.equals("End of words")){
            double counter = 0;
            for (int i = 0; i <word.length() ; i++) {
                counter += word.charAt(i);
            }
            int numberLength = word.length();
            String wordException = word.toLowerCase();
            if (wordException.charAt(0)== 'a' || wordException.charAt(0)== 'e' || wordException.charAt(0)== 'i' || wordException.charAt(0)== '0' || wordException.charAt(0)== 'u' || wordException.charAt(0)== 'y'){
                counter = counter * numberLength;
            } else{
                counter = Math.floor(counter / numberLength);
            }
            if (counter >max){
                max = counter;
                finalWord = word;
            }
            word = scanner.nextLine();
        }
        System.out.printf("The most powerful word is %s - %.0f", finalWord, max);
    }
}
