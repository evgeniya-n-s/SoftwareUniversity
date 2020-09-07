import java.util.Scanner;

public class OscarsWeekInCinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String filmName = scanner.nextLine();
        String kindSalary = scanner.nextLine();
        int numberTickets = Integer.parseInt(scanner.nextLine());
        double count = 0.0;

        if (filmName.equals("A Star Is Born")){
            if (kindSalary.equals("normal")){
                count = numberTickets * 7.5;
            } else if (kindSalary.equals("luxury")){
                count = numberTickets * 10.5;
            } else if (kindSalary.equals("ultra luxury")){
                count = numberTickets * 13.5;
            }
        }

        if (filmName.equals("Bohemian Rhapsody")){
            if (kindSalary.equals("normal")){
                count = numberTickets * 7.35;
            } else if (kindSalary.equals("luxury")){
                count = numberTickets * 9.45;
            } else if (kindSalary.equals("ultra luxury")){
                count = numberTickets * 12.75;
            }
        }

        if (filmName.equals("Green Book")){
            if (kindSalary.equals("normal")){
                count = numberTickets * 8.15;
            } else if (kindSalary.equals("luxury")){
                count = numberTickets * 10.25;
            } else if (kindSalary.equals("ultra luxury")){
                count = numberTickets * 13.25;
            }
        }

        if (filmName.equals("The Favourite")){
            if (kindSalary.equals("normal")){
                count = numberTickets * 8.75;
            } else if (kindSalary.equals("luxury")){
                count = numberTickets * 11.55;
            } else if (kindSalary.equals("ultra luxury")){
                count = numberTickets * 13.95;
            }
        }
        System.out.printf("%s -> %.2f lv.", filmName, count);
    }
}
