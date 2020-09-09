import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "^(@[#]+)(?<barcode>[A-Z][A-Za-z0-9]{4,}[A-Z])\\1$";
        Pattern pattern = Pattern.compile(regex);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i <n ; i++) {
            String inputBarcode = scanner.nextLine();
            Matcher matcher = pattern.matcher(inputBarcode);

            if(matcher.find()){
                String barcode = matcher.group("barcode");
                String digits = "";
                boolean isDigit = false;

                for (int j = 0; j <barcode.length() ; j++) {
                    char symbol = barcode.charAt(j);
                    if(Character.isDigit(symbol)){
                        digits = digits + symbol;
                        isDigit = true;
                    }
                }

                if(isDigit){
                    System.out.println(String.format("Product group: %s",digits));
                } else {
                    System.out.println("Product group: 00");
                }
            } else {
                System.out.println("Invalid barcode");
            }
        }

    }
}
