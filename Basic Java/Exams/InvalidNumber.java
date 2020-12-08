import java.util.Scanner;
public class InvalidNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double number = Double.parseDouble(scanner.nextLine());
        boolean check = ((number>=100 && number <= 200) || (number == 0));

        if(!check){
            System.out.print("invalid");
        }
    }
}
