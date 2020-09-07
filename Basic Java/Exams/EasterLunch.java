import java.util.Scanner;

public class EasterLunch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberKozunaks = Integer.parseInt(scanner.nextLine());
        int numberEggs = Integer.parseInt(scanner.nextLine());
        int numberKorabii = Integer.parseInt(scanner.nextLine());

        double countKozunaks = numberKozunaks * 3.20;
        double countEggs = numberEggs * 4.35;
        double countKorabii = numberKorabii * 5.40;

        double countOneEgg = numberEggs * 12 * 0.15;
        double totalCount = countKozunaks + countEggs + countKorabii + countOneEgg;

        System.out.printf("%.2f", totalCount);
    }
}
