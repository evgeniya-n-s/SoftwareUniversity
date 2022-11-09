import java.util.Scanner;
public class Aquarium {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int lenght = Integer.parseInt(scanner.nextLine());
        int weight = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        double percent = Double.parseDouble(scanner.nextLine());
        int volume = lenght*weight*height;
        double liters = volume*0.001;
        double npercent = percent*0.01;
        double finalLiters = liters*(1-npercent);
        System.out.printf("%.3f",finalLiters);
    }

}
