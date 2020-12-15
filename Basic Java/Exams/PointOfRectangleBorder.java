import java.util.Scanner;

public class PointOfRectangleBorder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x1 = Double.parseDouble(scanner.nextLine());
        double y1 = Double.parseDouble(scanner.nextLine());
        double x2 = Double.parseDouble(scanner.nextLine());
        double y2 = Double.parseDouble(scanner.nextLine());
        double x = Double.parseDouble(scanner.nextLine());
        double y = Double.parseDouble(scanner.nextLine());

     //   boolean firstConditionInside = ((x>=x1 && (x<=x2)) && ((y>=y1) &&(y<=y2)));
     //   boolean secondConditionOutside = ((x<x1) || (x>x2) || (y<y1) || (y>y2));

        if ((x == x1 || x == x2) && (y >= y1 && y <= y2))       //point on vertical border
            System.out.print("Border");
        else if ((y == y1 || y == y2) && (x <= x2 && x >= x1))  //point on horizontal border
            System.out.print("Border");
        else
            System.out.print("Inside / Outside");

//        if (firstConditionInside || secondConditionOutside){
//            System.out.print("Inside / Outside");
//        } else{
//            System.out.print("Border");
//        }
    }
}
