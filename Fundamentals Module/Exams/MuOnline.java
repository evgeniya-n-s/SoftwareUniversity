import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MuOnline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] rooms = scanner.nextLine().split("[ |]");
        String[] input = new String[rooms.length];

        for (int i = 0; i < rooms.length; i++) {
            input[i] = rooms[i];
        }

        int health = 100;
        int bitcoins = 0;
        int room = 0;

        for (int i = 0; i <=health ; i+=2) {
            if(i==input.length){
                break;
            }
            room++;
            int energy = Integer.parseInt(input[i+1]);
            if("potion".equals(input[i])){
                if(health + energy<=100){
                    health = health+energy;
                    System.out.printf("You healed for %d hp.%n",energy);
                    System.out.printf("Current health: %d hp.%n",health);
                } else {
                    int temp = 0;
                    temp = 100 - health;
                    health = health + temp;
                    System.out.printf("You healed for %d hp.%n",temp);
                    System.out.printf("Current health: %d hp.%n",health);
                }

            } else if("chest".equals(input[i])){
                bitcoins = bitcoins + energy;
                System.out.printf("You found %d bitcoins.%n",energy);
            } else {
                health = health - energy;
                if(health>0){
                    System.out.printf("You slayed %s.%n",input[i]);
                } else {
                    System.out.printf("You died! Killed by %s.%n",input[i]);
                    System.out.printf("Best room: %d",room);
                    break;
                }
            }
        }
        if(health>0){
            System.out.println("You've made it!");
            System.out.printf("Bitcoins: %d%n",bitcoins);
            System.out.printf("Health: %d",health);
        }
    }
}
