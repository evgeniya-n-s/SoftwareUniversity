import java.util.Scanner;
public class Project {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int project = Integer.parseInt(scanner.nextLine());
        int countHours = project*3;
        System.out.printf("The architect %s will need %d hours to complete %d project/s.", name, countHours, project);
    }

}
