import java.util.Scanner;

public class PoolDay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countPeople = Integer.parseInt(scanner.nextLine());
        double taxEnter = Double.parseDouble(scanner.nextLine());
        double priceSunBed = Double.parseDouble(scanner.nextLine());
        double priceUmbrella = Double.parseDouble(scanner.nextLine());

        double enter = countPeople * taxEnter;
        double needSunBed = Math.ceil(countPeople * 0.75);
        double countSunBed = needSunBed * priceSunBed;
        double needUmbrella = Math.ceil(countPeople * 0.5);
        double countUmbrella = needUmbrella * priceUmbrella;
        double totalprice = enter + countSunBed + countUmbrella;

        System.out.printf("%.2f lv.", totalprice);
    }
}
