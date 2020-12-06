import java.util.Scanner;

public class comlicateCondition {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);

        double input = Double.parseDouble(scanner.nextLine());

        if (input >= -100 && input <= 100 && input != 0){
            System.out.print("Yes");
        }
        else{
            System.out.print("No");
        }
    }
}
