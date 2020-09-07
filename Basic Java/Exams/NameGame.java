import java.util.Scanner;

public class NameGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();


        int bestPoint = 0;
        String finalName = "";

        while (!name.equals("Stop")){
            int counter = 0;
            for (int i = 0; i <name.length() ; i++) {
                int number = Integer.parseInt(scanner.nextLine());
                if (number == name.charAt(i)) {
                    counter = counter + 10;
                } else {
                    counter = counter + 2;
                }
            }
                if (counter >= bestPoint){
                    bestPoint = counter;
                    finalName = name;
                }
            name = scanner.nextLine();
        }
        System.out.printf("The winner is %s with %d points!", finalName,bestPoint);
    }
}
