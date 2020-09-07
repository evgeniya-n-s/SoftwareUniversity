import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BonusScrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countOfStudent = Integer.parseInt(scanner.nextLine());
        int countOfLecture = Integer.parseInt(scanner.nextLine());
        int bonus = Integer.parseInt(scanner.nextLine());

        double calculateBonus = 0.0;
        double temp = 0.0;
        int student = 0;

        for (int i = 0; i <countOfStudent ; i++) {
            int eachStudentAttendance = Integer.parseInt(scanner.nextLine());
            calculateBonus = (eachStudentAttendance *1.0/countOfLecture) * (5+bonus);
            if(calculateBonus>temp){
                temp = calculateBonus;
                student = eachStudentAttendance;
            }
        }
        temp=Math.ceil(temp);
        System.out.printf("Max Bonus: %.0f.",temp);
        System.out.println();
        System.out.printf("The student has attended %d lectures.",student);
    }
}
