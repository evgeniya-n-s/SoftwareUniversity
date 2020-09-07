import java.util.Scanner;
public class DayMoney {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int days = Integer.parseInt(scanner.nextLine());
        double money = Double.parseDouble(scanner.nextLine());
        double dollar = Double.parseDouble(scanner.nextLine());

        double monthMoney = days * money;
        double yearMoney = (monthMoney * 12) + (monthMoney*2.5);
        double tax = yearMoney * 0.25;
        double totalYearMoney = yearMoney - tax;
        double totalYearMoneyBGN = totalYearMoney * dollar;
        double dayMoney = totalYearMoneyBGN / 365;
        System.out.printf("%.2f", dayMoney);
    }
}
