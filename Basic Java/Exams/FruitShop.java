import java.util.Scanner;

public class FruitShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fruit = scanner.nextLine().toLowerCase();
        String day = scanner.nextLine();
        double quality = Double.parseDouble(scanner.nextLine());

        //(banana / apple / orange / grapefruit / kiwi /
        //pineapple / grapes),
        if (day.equals("Saturday") || day.equals("Sunday")) {
            switch (fruit) {
                case "banana":
                    quality = quality * 2.70;
                    System.out.printf("%.2f", quality);
                    break;
                case "apple":
                    quality = quality * 1.25;
                    System.out.printf("%.2f", quality);
                    break;
                case "orange":
                    quality = quality * 0.90;
                    System.out.printf("%.2f", quality);
                    break;
                case "grapefruit":
                    quality = quality * 1.60;
                    System.out.printf("%.2f", quality);
                    break;
                case "kiwi":
                    quality = quality * 3.00;
                    System.out.printf("%.2f", quality);
                    break;
                case "pineapple":
                    quality = quality * 5.60;
                    System.out.printf("%.2f", quality);
                    break;
                case "grapes":
                    quality = quality * 4.20;
                    System.out.printf("%.2f", quality);
                    break;
                default:
                    System.out.print("error");
            }

        } else if (day.equals("Monday") || day.equals("Tuesday") || day.equals("Wednesday") || day.equals("Thursday") || day.equals("Friday")) {
            switch (fruit) {
                case "banana":
                    quality = quality * 2.50;
                    System.out.printf("%.2f", quality);
                    break;
                case "apple":
                    quality = quality * 1.20;
                    System.out.printf("%.2f", quality);
                    break;
                case "orange":
                    quality = quality * 0.85;
                    System.out.printf("%.2f", quality);
                    break;
                case "grapefruit":
                    quality = quality * 1.45;
                    System.out.printf("%.2f", quality);
                    break;
                case "kiwi":
                    quality = quality * 2.70;
                    System.out.printf("%.2f", quality);
                    break;
                case "pineapple":
                    quality = quality * 5.50;
                    System.out.printf("%.2f", quality);
                    break;
                case "grapes":
                    quality = quality * 3.85;
                    System.out.printf("%.2f", quality);
                    break;
                default:
                    System.out.print("error");
                    break;
            }
        } else {
            System.out.print("error");
        }
    }
}