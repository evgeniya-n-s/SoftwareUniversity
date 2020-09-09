import java.util.Scanner;

public class ActivationKeys3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String activationKey = scanner.nextLine();
        StringBuilder sb = new StringBuilder(activationKey);

        String inputCommands = scanner.nextLine();
        while (!"Generate".equals(inputCommands)) {
            String[] tokens = inputCommands.split(">>>");
            String command = tokens[0];

            switch (command) {
                case "Contains":
                    String substring = tokens[1];
                    if(activationKey.contains(substring)){
                        System.out.println(String.format("%s contains %s",activationKey,substring));
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    String type = tokens[1];
                    int startIndex = Integer.parseInt(tokens[2]);
                    int endIndex = Integer.parseInt(tokens[3]);
                    String substring1 = sb.substring(startIndex,endIndex);

                    if("Upper".equals(type)){
                        substring1 = substring1.toUpperCase();
                        sb.replace(startIndex,endIndex,substring1);
                        activationKey = sb.toString();
                        System.out.println(activationKey);
                    } else {
                        substring1 = substring1.toLowerCase();
                        sb.replace(startIndex,endIndex,substring1);
                        activationKey = sb.toString();
                        System.out.println(activationKey);
                    }

                    break;
                case "Slice":
                    int start = Integer.parseInt(tokens[1]);
                    int end = Integer.parseInt(tokens[2]);
                    sb.replace(start,end,"");
                    activationKey = sb.toString();
                    System.out.println(activationKey);
                    break;
            }
            inputCommands = scanner.nextLine();
        }
        System.out.println(String.format("Your activation key is: %s",activationKey));
    }
}
