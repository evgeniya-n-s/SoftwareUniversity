import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double number = Double.parseDouble(scanner.nextLine());
        String enter = scanner.nextLine();
        String exit = scanner.nextLine();
        double c = 0;
        if (enter == "USD" || exit == "BGN"){
                c = number * 1.79549;
            //    System.out.printf("%.2f", USDCount);
            }
        System.out.printf("%.2f", c);
  //          else if (exit == "EUR"){
 //               double USDCount = number * 0.91801;
  //              System.out.printf("%.2f", USDCount);
  //          }
 //           else if (exit == "GBP"){
  //              double USDCount = number * 0.70854;
  //              System.out.printf("%.2f", USDCount);
   //         }
  //     }
    }
}
