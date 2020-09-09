import java.util.Scanner;

public class ActivationKeys2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        String instructions = sc.nextLine();
        while (!instructions.equals("Generate")) {
            String[] commands = instructions.split(">>>");
            String cases = commands[0];
            switch (cases) {
                case "Contains":
                    String subString = commands[1];
                    if (key.contains(subString)) {
                        System.out.println(String.format("%s contains %s.", key, subString));
                    } else {
                        System.out.println(String.format("Substring not found!"));
                    }
                    break;
                case "Flip"://StringBuilder
                    String upperLower = commands[1];
                    int startIndex = Integer.parseInt(commands[2]);
                    int endIndex = Integer.parseInt(commands[3]);
                    String toChanges = key.substring(startIndex, endIndex);
                    if (upperLower.equals("Upper")) {
                        toChanges=toChanges.toUpperCase();
                        StringBuilder sb = new StringBuilder();
                        sb.append(key);
                        sb.replace(startIndex, endIndex, (toChanges));
                        key = sb.toString();
                    } else {
                        toChanges=toChanges.toLowerCase();
                        StringBuilder sb = new StringBuilder();
                        sb.append(key);
                        sb.replace(startIndex, endIndex, (toChanges));
                        key = sb.toString();

                    }
                    System.out.println(key);
                    break;
                case "Slice"://StringBuilder
                    int startX = Integer.parseInt(commands[1]);
                    int endX = Integer.parseInt(commands[2]);
                    StringBuilder deleter = new StringBuilder();
                    deleter.append(key);
                    deleter.replace(startX, endX, "");
                    key=deleter.toString();

                    System.out.println(key);
                    break;
                default:
                    throw new IllegalStateException("wrong input!!!");
            }
            instructions = sc.nextLine();
        }
        System.out.println(String.format("Your activation key is: %s", key));
    }
}
