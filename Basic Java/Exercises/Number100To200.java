import java.util.Scanner;
public class Number100To200 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        if (number < 100){
            System.out.print("Less than 100");
        } else if (number >= 100 && number <= 200){
            System.out.print("Between 100 and 200");
        } else
            System.out.print("Greater than 200");
    }
}
