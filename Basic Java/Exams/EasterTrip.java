import java.util.Scanner;

public class EasterTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String destination = scanner.nextLine();
        String date = scanner.nextLine();
        int night = Integer.parseInt(scanner.nextLine());

        double countMoney = 0.0;

        if (destination.equals("France")){
            if (date.equals("21-23")){
                countMoney = night * 30;
            } else if (date.equals("24-27")){
                countMoney = night * 35;
            } else if (date.equals("28-31")){
                countMoney = night * 40;
            }
        }

        if (destination.equals("Italy")){
            if (date.equals("21-23")){
                countMoney = night * 28;
            } else if (date.equals("24-27")){
                countMoney = night * 32;
            } else if (date.equals("28-31")){
                countMoney = night * 39;
            }
        }

        if (destination.equals("Germany")){
            if (date.equals("21-23")){
                countMoney = night * 32;
            } else if (date.equals("24-27")){
                countMoney = night * 37;
            } else if (date.equals("28-31")){
                countMoney = night * 43;
            }
        }

        System.out.printf("Easter trip to %s : %.2f leva.", destination,countMoney);
    }
}
