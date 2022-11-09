import java.util.Scanner;
public class MissAndMr {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        String gender = scanner.nextLine();

        switch (gender){
            case "f":
                System.out.print("Miss");
                break;
            case "m":
                System.out.print("Mr.");
                break;
        }
    }
}
