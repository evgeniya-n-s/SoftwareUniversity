import java.util.Scanner;

public class DisneylandJourney {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double journeyCost = Double.parseDouble(scanner.nextLine());
        int numberMonths = Integer.parseInt(scanner.nextLine());

        double collectingMoney = 0.0;
        int count4Months = 0;
        double safeMoney = 0.0;

        for (int i = 1; i <=numberMonths ; i++) {
            count4Months++;
            safeMoney = journeyCost*0.25;
            if (i==1){
                collectingMoney = safeMoney;
            } else if (!(i % 2 == 0)) {
                double spendMoney = collectingMoney * 0.16;
                collectingMoney = collectingMoney + safeMoney;
                collectingMoney = collectingMoney - spendMoney;
            } else if (count4Months==4){
                count4Months=0;
                double bonusMoney = collectingMoney * 0.25;
                collectingMoney = collectingMoney + safeMoney;
                collectingMoney = collectingMoney + bonusMoney;
            }else {
                collectingMoney = collectingMoney + safeMoney;
            }
        }
        if(collectingMoney>=journeyCost){
            double totalMoneyLeft = collectingMoney - journeyCost;
            System.out.printf("Bravo! You can go to Disneyland and you will have %.2flv. for souvenirs.",totalMoneyLeft);
        } else {
            double needMoney = journeyCost - collectingMoney;
            System.out.printf("Sorry. You need %.2flv. more.",needMoney);
        }
    }
}
