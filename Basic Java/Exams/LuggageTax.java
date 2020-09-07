import java.util.Scanner;

public class LuggageTax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        int depth = Integer.parseInt(scanner.nextLine());
        String priority = scanner.nextLine();

        double suitcase= width * height * depth;
        double tax = 0.0;

        if (suitcase <= 50){
            System.out.printf("Luggage tax: %.2f", tax);
        } else if (suitcase <= 100){
            if (priority.equals("true")){
                System.out.printf("Luggage tax: %.2f", tax);
            } else{
                tax = 25;
                System.out.printf("Luggage tax: %.2f", tax);
            }
        } else if (suitcase <=200){
            if (priority.equals("true")){
                tax = 10;
                System.out.printf("Luggage tax: %.2f", tax);
            } else{
                tax = 50;
                System.out.printf("Luggage tax: %.2f", tax);
            }
        } else if (suitcase <= 300){
            if (priority.equals("true")){
                tax = 20;
                System.out.printf("Luggage tax: %.2f", tax);
            } else{
                tax = 100;
                System.out.printf("Luggage tax: %.2f", tax);
            }
        }
    }
}
