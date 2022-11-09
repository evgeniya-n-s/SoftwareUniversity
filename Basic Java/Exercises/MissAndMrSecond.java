import java.util.Scanner;
public class MissAndMrSecond {
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);

        double age = Double.parseDouble(scanner.nextLine());
        String gender = scanner.nextLine();

        if ("f".equals(gender)){
            if(age >= 16)
                System.out.print("Ms.");
            else
            System.out.print("Miss");
        }

        if ("m".equals(gender)){
            if(age >= 16)
                System.out.print("Mr.");
            else
            System.out.print("Master");
        }
    }
}
