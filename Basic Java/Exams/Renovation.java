import java.util.Scanner;

public class Renovation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int height = Integer.parseInt(scanner.nextLine());
        int width = Integer.parseInt(scanner.nextLine());
        int noPaintArea = Integer.parseInt(scanner.nextLine());

        double countArea = height * width * 4;
        double countTotalArea = Math.ceil(countArea - countArea * 1.0 * noPaintArea/100);

        String command = scanner.nextLine();
        while (!command.equals("Tired!")) {
            int literPaint = Integer.parseInt(command);
            countTotalArea = countTotalArea - literPaint;
            if (countTotalArea <= 0) {
                break;
            }
            command = scanner.nextLine();
        }

        if ( countTotalArea == 0) {
            System.out.print("All walls are painted! Great job, Pesho!");

        } else if (countTotalArea > 0) {
            System.out.printf("%.0f quadratic m left.", countTotalArea);

        } else {
            System.out.printf("All walls are painted and you have %.0f l paint left!", Math.abs(countTotalArea));
        }
    }
}
