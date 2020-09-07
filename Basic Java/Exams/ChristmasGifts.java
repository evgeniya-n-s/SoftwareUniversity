import java.util.Scanner;

public class ChristmasGifts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String years = scanner.nextLine();

        int toy = 0;
        int countToy = 0;
        int gift = 0;
        int countGifts = 0;

        while (!years.equals("Christmas")){
            int convert = Integer.parseInt(years);
            if (convert <=16){
                toy = toy + 5;
                countToy++;
            } else {
                gift = gift + 15;
                countGifts++;
            }
            years = scanner.nextLine();
        }
        System.out.printf("Number of adults: %d%n",countGifts);
        System.out.printf("Number of kids: %d%n", countToy);
        System.out.printf("Money for toys: %d%n",toy);
        System.out.printf("Money for sweaters: %d", gift);
    }
}
