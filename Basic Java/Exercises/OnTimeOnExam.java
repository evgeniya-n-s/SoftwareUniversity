import java.util.Scanner;

public class OnTimeOnExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hourExam = Integer.parseInt(scanner.nextLine());
        int minutesExam = Integer.parseInt(scanner.nextLine());
        int hourStudent = Integer.parseInt(scanner.nextLine());
        int minutesStudent = Integer.parseInt(scanner.nextLine());

        int countMinutesExam = (hourExam * 60) + minutesExam;
        int countMinutesStudent = (hourStudent * 60) + minutesStudent;
        int countDifferent = Math.abs(countMinutesExam - countMinutesStudent);

        //   System.out.println(countDifferent);
        if ((countMinutesExam < countMinutesStudent)) {
            System.out.println("Late");
            int hours = countDifferent / 60;
            int minutes = countDifferent % 60;

            if (hours == 0) {
                System.out.printf("%d minutes after the start", minutes);
            } else {
                if (minutes < 10) {
                    System.out.printf("%d:0%d hours after the start", hours, minutes);
                } else {
                    System.out.printf("%d:%d hours after the start", hours, minutes);
                }
            }
        }

        if ((countMinutesExam > countMinutesStudent) && (countDifferent > 30)) {
            System.out.println("Early");
            int hours = countDifferent / 60;
            int minutes = countDifferent % 60;

            if (hours == 0) {
                System.out.printf("%d minutes before the start", minutes);
            } else {
                if (minutes < 10) {
                    System.out.printf("%d:0%d hours before the start", hours, minutes);
                } else {
                    System.out.printf("%d:%d hours before the start", hours, minutes);
                }
            }
        }

        if ((countMinutesExam == countMinutesStudent)) {
            System.out.println("On time");
        }

        if ((countMinutesExam > countMinutesStudent) && (countDifferent <= 30)) {
            System.out.println("On time");
            int minutes = countDifferent % 60;
//            if (minutes < 10) {
//                System.out.printf("0%d minutes before the start", minutes);
//            } else {
            System.out.printf("%d minutes before the start", minutes);
        }
    }
}
