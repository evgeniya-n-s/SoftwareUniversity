import java.util.Scanner;
public class Repair {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int sizeArea = Integer.parseInt(scanner.nextLine());
        double sizeWidth = Double.parseDouble(scanner.nextLine());
        double sizeLength = Double.parseDouble(scanner.nextLine());
        int benchWidth = Integer.parseInt(scanner.nextLine());
        int benchLength = Integer.parseInt(scanner.nextLine());

        int totalSizeArea = sizeArea * sizeArea;
        int totalBench = benchWidth * benchLength;
        double totalPlate = sizeWidth * sizeLength;
        int totalSize = totalSizeArea - totalBench;
        double plate = totalSize / totalPlate;
        double totalTime = plate * 0.2;
        System.out.println(plate);
        System.out.println(totalTime);
    }
}
