import java.util.Scanner;

public class ReadDouble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Double num = Double.parseDouble(input);
        System.out.println(num);
    }
}


