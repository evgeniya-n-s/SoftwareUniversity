import java.util.Scanner;
public class CharityChampaign {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int numberDays = Integer.parseInt(scanner.nextLine());
        int numberCooker = Integer.parseInt(scanner.nextLine());
        int cake = Integer.parseInt(scanner.nextLine());
        int cake2 = Integer.parseInt(scanner.nextLine());
        int pancake = Integer.parseInt(scanner.nextLine());
        double sumCake = cake*45;
        double sumCake2 = cake2*5.80;
        double sumPancake = pancake*3.20;
        double sumPerday = (sumCake+sumCake2+sumPancake)*numberCooker;
        double sumAll = sumPerday*numberDays;
        double totalSum = (sumAll/8)*7;
        System.out.printf("%.2f",totalSum);
    }

}
