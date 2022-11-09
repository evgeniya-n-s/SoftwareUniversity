import java.util.Scanner;
public class SewingWorkshop {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        int countRectangleTable = Integer.parseInt(scanner.nextLine());
        double lenghtRectangleTable = Double.parseDouble(scanner.nextLine());
        double weightRectangleTable = Double.parseDouble(scanner.nextLine());
        double countTable = countRectangleTable*(lenghtRectangleTable+2*0.30)*(weightRectangleTable+2*0.30);
        double countSquare = countRectangleTable*(lenghtRectangleTable/2)*(lenghtRectangleTable/2);
        double priceDollars = (countTable * 7) + (countSquare * 9);
        double priceLeva = priceDollars * 1.85;
        System.out.printf("%.2f USD",priceDollars);
        System.out.println();
        System.out.printf("%.2f BGN",priceLeva);
    }

}
