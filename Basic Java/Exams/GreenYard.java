import java.util.Scanner;
public class GreenYard {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double yard = Double.parseDouble(scanner.nextLine());
        double fristPrice = yard*7.61;
        double secondPrice = 0.18*fristPrice;
        double finalPrice = fristPrice-secondPrice;
        System.out.printf("The final price is: %.2f lv.",finalPrice);
        System.out.println();
        System.out.printf("The discount is: %.2f lv.",secondPrice);
    }

}
