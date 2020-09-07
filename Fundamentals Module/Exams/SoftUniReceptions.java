import java.util.Scanner;

public class SoftUniReceptions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstReception = Integer.parseInt(scanner.nextLine());
        int secondReception = Integer.parseInt(scanner.nextLine());
        int thirdReception = Integer.parseInt(scanner.nextLine());

        int students = Integer.parseInt(scanner.nextLine());

        int countStudentPerHour = firstReception + secondReception + thirdReception;

        int totalTime = 0;
        int days = 0;

        while (students>0){
            students = students - countStudentPerHour;
            totalTime ++;
            if(totalTime %4 == 0 && totalTime!=0){
                totalTime++;
                if(totalTime >=24){
                    days ++;
                    totalTime = totalTime - 24;
                }
            }
        }
        System.out.printf("Time needed: %dh.", days * 24 + totalTime);
    }
}
