import java.util.Scanner;

public class FamilyTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double familyBudget = Double.parseDouble(scanner.nextLine());
        int numberNight = Integer.parseInt(scanner.nextLine());
        double priceNight = Double.parseDouble(scanner.nextLine());
        int extraExpence = Integer.parseInt(scanner.nextLine());

        double countNight = 0.0;
        double countBudget = 0.0;
        double countTotal = 0.0;

        if (numberNight <= 7) {
            countNight = numberNight * priceNight;
            countBudget = (familyBudget * extraExpence) * 0.01;
            countTotal = countNight + countBudget;
            if (familyBudget < countTotal) {
                double needMoney = countTotal - familyBudget;
                System.out.printf("%.2f leva needed.", needMoney);
            } else {
                double leftMoney = familyBudget - countTotal;
                System.out.printf("Ivanovi will be left with %.2f leva after vacation.", leftMoney);
            }
        } else {
            double discount = priceNight * 0.05;
            double discount2 = priceNight - discount;
            countNight = numberNight * discount2;
            countBudget = (familyBudget * extraExpence) * 0.01;
            countTotal = countNight + countBudget;
            if (familyBudget < countTotal) {
                double needMoney = countTotal - familyBudget;
                System.out.printf("%.2f leva needed.", needMoney);
            } else {
                double leftMoney = familyBudget - countTotal;
                System.out.printf("Ivanovi will be left with %.2f leva after vacation.", leftMoney);
            }
        }
    }
}
