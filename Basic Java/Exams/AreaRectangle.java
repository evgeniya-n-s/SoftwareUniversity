import java.util.Scanner;
public class AreaRectangle {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double x1 = Double.parseDouble(scanner.nextLine());
        double y1 = Double.parseDouble(scanner.nextLine());
        double x2 = Double.parseDouble(scanner.nextLine());
        double y2 = Double.parseDouble(scanner.nextLine());
        double x = Math.abs(x1-x2);
        double y = Math.abs(y2-y1);
        double area = x*y;
        double perimeter = 2*(x+y);
        System.out.printf("%.2f%n", area);
        System.out.printf("%.2f", perimeter);
    }

}
