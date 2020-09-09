import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "^@#+(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])@#+$";
        Pattern pattern = Pattern.compile(regex);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i <n ; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            String findNumber = "";
            boolean isTrue = false;

            if(matcher.find()) {
                String barcode = matcher.group("barcode");
                for (int j = 0; j < barcode.length(); j++) {
                    char ch = barcode.charAt(j);
                    if (Character.isDigit(ch)) {
                        findNumber = findNumber + ch;
                        isTrue = true;
                    }
                }
                if (isTrue) {
                    System.out.println(String.format("Product group: %s", findNumber));
                } else {
                    System.out.println("Product group: 00");
                }
            } else {
                System.out.println("Invalid barcode");
            }
        }
    }
}
