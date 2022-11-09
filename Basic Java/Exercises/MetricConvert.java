import java.util.Scanner;
public class MetricConvert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double number = Double.parseDouble(scanner.nextLine());
        String enterMesure = scanner.nextLine();
        String exitMesure = scanner.nextLine();
        double count = 0;

        if((enterMesure.equals("mm")) && (exitMesure.equals("m"))){
            count = number * 0.001;
        }
        else if ((enterMesure.equals("mm")) && (exitMesure.equals("cm"))){
            count = number * 0.1;
        }
        else if ((enterMesure.equals("m")) && (exitMesure.equals("cm"))){
            count = number * 100;
        }
        else if ((enterMesure.equals("m")) && (exitMesure.equals("mm"))){
            count = number * 1000;
        }
        else if ((enterMesure.equals("cm")) && (exitMesure.equals("mm"))){
            count = number * 10;
        }
        else if ((enterMesure.equals("cm")) && (exitMesure.equals("m")))
        {
            count = number * 0.01;
        }
        System.out.printf("%.3f", count);
    }
}
