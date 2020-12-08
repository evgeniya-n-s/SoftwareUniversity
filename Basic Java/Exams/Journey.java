import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine();

        double count = 0.0;
        String destination = "";
        String vacationType = "";

        if(budget <= 100){
            destination = "Bulgaria";
            if (season.equals("summer")){
                count = budget * 0.3;
                vacationType = "Camp";
            } else if(season.equals("winter")){
                count = budget * 0.7;
                vacationType = "Hotel";
            }
        } else if (budget<=1000){
            destination = "Balkans";
            if (season.equals("summer")){
                count = budget * 0.4;
                vacationType = "Camp";
            } else if(season.equals("winter")){
                count = budget * 0.8;
                vacationType = "Hotel";
            }
        }else{
            destination = "Europe";
            count = budget * 0.9;
            vacationType = "Hotel";
        }
        System.out.printf("Somewhere in %s", destination);
        System.out.println();
        System.out.printf("%s - %.2f", vacationType, count);
    }
}
