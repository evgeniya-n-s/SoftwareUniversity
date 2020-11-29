import java.util.Scanner;
public class AreaOfFigures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String geometricShape = scanner.nextLine().toLowerCase();

        switch (geometricShape) {
            case "square":
                double side = Double.parseDouble(scanner.nextLine());
                double areaSquare = side * side;
                System.out.printf("%.3f%n", areaSquare);
                break;
            case "rectangle":
                double length = Double.parseDouble(scanner.nextLine());
                double width = Double.parseDouble(scanner.nextLine());
                double areaRectangle = length * width;
                System.out.printf("%.3f%n", areaRectangle);
                break;
            case "circle":
                double radius = Double.parseDouble(scanner.nextLine());
                double areaCicle = Math.PI * radius * radius;
                System.out.printf("%.3f%n", areaCicle);
                break;
            case "triangle":
                double base = Double.parseDouble(scanner.nextLine());
                double hight = Double.parseDouble(scanner.nextLine());
                double areaTriangle = (base * hight) / 2;
                System.out.printf("%.3f%n", areaTriangle);
                break;
        }
    }
}
