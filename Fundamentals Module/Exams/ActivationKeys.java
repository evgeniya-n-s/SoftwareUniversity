import java.util.Scanner;

public class ActivationKeys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String command = scanner.nextLine();

        StringBuilder sb = new StringBuilder(input);

        while (!"Generate".equals(command)) {
            String[] tokens = command.split(">>>");
            String nameCommand = tokens[0];

            switch (nameCommand) {
                case "Contains":
                    String contains = tokens[1];
                    if(sb.lastIndexOf(contains)>=0){
                        System.out.printf(sb+" contains %s",contains);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    String upperOrLowerCase = tokens[1];
                    int startIndex = Integer.parseInt(tokens[2]);
                    int endIndex = Integer.parseInt(tokens[3]);

                    if("Upper".equals(upperOrLowerCase)){
                        String currentUpper = sb.substring(startIndex,endIndex);
                        currentUpper = currentUpper.toUpperCase();
                        sb.replace(startIndex,endIndex, currentUpper);
                        System.out.println(sb);
                    }else {
                        String currentLower = sb.substring(startIndex,endIndex);
                        currentLower = currentLower.toLowerCase();
                        sb.replace(startIndex,endIndex,currentLower);
                        System.out.println(sb);
                    }

                    break;
                case "Slice":
                    int startIndexSlice = Integer.parseInt(tokens[1]);
                    int endIndexSlice = Integer.parseInt(tokens[2]);
                    sb.delete(startIndexSlice,endIndexSlice);
                    System.out.println(sb);
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.println("Your activation key is: "+sb);
    }
}
