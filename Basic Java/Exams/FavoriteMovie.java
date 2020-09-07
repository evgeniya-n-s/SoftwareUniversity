import java.util.Scanner;

public class FavoriteMovie {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nameMovie = scanner.nextLine();
        int max = -1;
        int counter = 0;
        String bestMovie = "";

        while (!nameMovie.equals("STOP")){
            int currentSum = 0;
            counter++;
            for (int i = 0; i <nameMovie.length() ; i++) {
                int currentWord = nameMovie.charAt(i);
                    currentSum +=currentWord;
                if (currentWord >=65 && currentWord <=90){
                    currentSum -=nameMovie.length();
                } else if (currentWord >= 97 && currentWord <= 122){
                    currentSum -=nameMovie.length() *2;
                }
            }
            if (currentSum > max){
                max = currentSum;
                bestMovie = nameMovie;
            }
            if (counter >7){
                break;
            }
            nameMovie = scanner.nextLine();
        }
        if (counter >=7){
            System.out.printf("The limit is reached.");
        }
        System.out.printf("The best movie for you is %s with %d ASCII sum.", nameMovie, max);
    }
}
