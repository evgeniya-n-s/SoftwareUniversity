import java.util.Scanner;
public class Money {
    public static void main(String[] args){
        Scanner scanner = new Scanner (System.in);
        int bitCount = Integer.parseInt(scanner.nextLine());
        double chineCount = Double.parseDouble(scanner.nextLine());
        double commission = Double.parseDouble(scanner.nextLine());

        double countBitCount = bitCount * 1168;
        double countChineCount = chineCount * 0.15;
        double countDollar = countChineCount * 1.76;
        double countEuro = (countBitCount + countDollar) / 1.95;
        double convertCommission = commission * 0.01;
        double countCommission =  countEuro* convertCommission;
        double totalMoney = countEuro - countCommission;
        System.out.println(totalMoney);
    }
}
