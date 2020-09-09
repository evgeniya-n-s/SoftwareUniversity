import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex= "^@#+(?=[A-Z])([A-Za-z0-9]{6,})(?<=[A-Z])@#+$";
        Pattern pattern = Pattern.compile(regex);

        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemp = new StringBuilder();
        String currentDigit = "";
        boolean isDigit = false;
        int convert = 0;

        for (int i = 0; i <n ; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);

            if(matcher.find()){
                sb.append(input);
                for (int j = 0; j <sb.length() ; j++) {
                    char digit = sb.charAt(j);
                    if(Character.isDigit(digit)){
                        currentDigit = currentDigit + digit;
                        isDigit = true;
                    }
                }
                if (isDigit){
                    convert = Integer.parseInt(currentDigit);
                    System.out.println("Product group: "+ convert);
                    currentDigit = "";
                    convert = 0;
                    isDigit=false;
                } else {
                    System.out.println("Product group: 00");
                }
            } else if (!matcher.find()){
                System.out.println("Invalid barcode");
            }
            sb.delete(0,sb.length());
        }
    }
}
