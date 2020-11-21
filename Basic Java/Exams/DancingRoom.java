import java.util.Scanner;
public class DancingRoom {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        double lenghtRoom = Double.parseDouble(scanner.nextLine());
        double widthRoom = Double.parseDouble(scanner.nextLine());
        double sideWardrobe = Double.parseDouble(scanner.nextLine());
        double sizeRoom = (lenghtRoom*100) * (widthRoom*100);
        double sizeWardrobe = (sideWardrobe*100)*(sideWardrobe*100);
        double sizeBench = sizeRoom / 10;
        double freeSpace = sizeRoom - sizeWardrobe - sizeBench;
        double dansers= Math.floor(freeSpace/(40+7000));
        System.out.printf("%.0f", dansers);
    }

}
