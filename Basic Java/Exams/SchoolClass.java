import java.util.Scanner;
public class SchoolClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double length = Double.parseDouble(scanner.nextLine());
        double widght = Double.parseDouble(scanner.nextLine());
        double convertLength = Math.floor((length*100)/120);
        double convertWidgth = Math.floor(((widght*100)-100)/70);
        double workSpaces = ((convertLength*convertWidgth)-3);
        System.out.printf("%.0f",workSpaces);
    }

}
