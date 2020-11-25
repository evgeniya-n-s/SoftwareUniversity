import java.util.Scanner;
public class USDBGN {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double usd = Double.parseDouble(scanner.nextLine());
        double change = usd*1.79549;
        System.out.printf("%.2f",change);
    }

}
