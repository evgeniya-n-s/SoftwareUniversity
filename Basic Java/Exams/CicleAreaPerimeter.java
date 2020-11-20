import java.util.Scanner;
public class CicleAreaPerimeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cicle radius. r = ");
        double r = Double.parseDouble(scanner.nextLine());
        double cicleArea = Math.PI*r*r;
        double perimeter = Math.PI*2*r;
        System.out.println("Area = "+ cicleArea);
        System.out.println("Peprimeter = " + perimeter);
    }
}
