import java.util.Scanner;

public class GreaterNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number1 = scanner.nextInt();
        int number2 = scanner.nextInt();

        if (number1 > number2){
            System.out.print(number1);
        }else{
            System.out.print(number2);
        }
    }
}
