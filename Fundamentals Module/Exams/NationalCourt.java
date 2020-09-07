import java.util.Scanner;

public class NationalCourt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstEmployeeWork = Integer.parseInt(scanner.nextLine());
        int secondEmployeeWork = Integer.parseInt(scanner.nextLine());
        int thirdEmployeeWork = Integer.parseInt(scanner.nextLine());

        int totalRequest = Integer.parseInt(scanner.nextLine());

        int timeBreak = 0;
        int countPeoplePerHour = (firstEmployeeWork + secondEmployeeWork + thirdEmployeeWork);
        int totalTime = 0;

        for (int i = 1; i <=totalRequest ; i++) {
            totalTime++;
            timeBreak++;
            if(timeBreak==4){
                timeBreak=0;
            }else {
                totalRequest = totalRequest - countPeoplePerHour;
            }
        }
        if(totalRequest>0){
            totalTime++;
        }
        System.out.printf("Time needed: %dh.",totalTime);
    }
}
