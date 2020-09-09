import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        String command = scanner.nextLine();

        StringBuilder sb = new StringBuilder(string);

        while (!"Done".equals(command)) {
            String[] tokens = command.split(" ");
            String nameCommand = tokens[0];
            switch (nameCommand) {
                case "TakeOdd":
                    StringBuilder sb2 = new StringBuilder();
                    for (int i = 0; i <sb.length() ; i++) {
                        if(!(i%2==0)){
                            char symbol = sb.charAt(i);
                            sb2.append(symbol);
                        }
                    }
                    sb=sb2;
                    System.out.println(sb);
                    break;
                case "Cut":
                    int index = Integer.parseInt(tokens[1]);
                    int length = Integer.parseInt(tokens[2]);

                    sb.delete(index,index+length);
                    System.out.println(sb);
                    break;
                case "Substitute":
                    String substring = tokens[1];
                    String substitute = tokens[2];
                    boolean isContains = false;

                    while (sb.lastIndexOf(substring)!=-1){
                        int indexOf = sb.lastIndexOf(substring);
                        int lengthOfIndex = substring.length();
                        sb.replace(indexOf,indexOf+lengthOfIndex,substitute);
                        isContains=true;
                    }
                    if(isContains){
                        System.out.println(sb);
                    }else {
                        System.out.println("Nothing to replace!");
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        System.out.println("Your password is: "+sb);
    }
}
