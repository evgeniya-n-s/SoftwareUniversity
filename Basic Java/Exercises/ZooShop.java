import java.util.Scanner;
public class ZooShop {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int Dogs = Integer.parseInt(scanner.nextLine());
        int Animals = Integer.parseInt(scanner.nextLine());
        double countDogs = Dogs*2.50;
        double countAnimals = Animals*4;
        double countAmount = countDogs + countAnimals;
        System.out.printf("%.2f lv.", countAmount);
    }

}
