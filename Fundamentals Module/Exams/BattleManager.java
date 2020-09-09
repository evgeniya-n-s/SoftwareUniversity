import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BattleManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Integer> healthMap = new LinkedHashMap<>();
        Map<String, Integer> energyMap = new LinkedHashMap<>();

        while (!"Results".equals(input)) {
            String[] tokens = input.split(":");
            String command = tokens[0];

            switch (command) {
                case "Add":
                    String personName = tokens[1];
                    int health = Integer.parseInt(tokens[2]);
                    int energy = Integer.parseInt(tokens[3]);
                    if(healthMap.containsKey(personName) && energyMap.containsKey(personName)){
                        int currentHealth = healthMap.get(personName);
                        healthMap.put(personName,currentHealth + health);
                    } else {
                        healthMap.put(personName,health);
                        energyMap.put(personName,energy);
                    }
                    break;
                case "Attack":
                    String attackerName = tokens[1];
                    String defenderName = tokens[2];
                    int damage = Integer.parseInt(tokens[3]);
                    if(healthMap.containsKey(attackerName)&&energyMap.containsKey(attackerName)&&healthMap.containsKey(defenderName)&&energyMap.containsKey(defenderName)){
                        int defenderHealh = healthMap.get(defenderName);
                        int attackEnergy = energyMap.get(attackerName);
                        healthMap.put(defenderName,defenderHealh-damage);
                        energyMap.put(attackerName,attackEnergy-1);
                        if(healthMap.get(defenderName)<=0){
                            healthMap.remove(defenderName);
                            energyMap.remove(defenderName);
                            System.out.println(String.format("%s was disqualified!",defenderName));
                        }
                        if(energyMap.get(attackerName)<=0){
                            energyMap.remove(attackerName);
                            healthMap.remove(attackerName);
                            System.out.println(String.format("%s was disqualified!",attackerName));
                        }
                    }
                    break;
                case "Delete":
                    String username = tokens[1];
                    if(healthMap.containsKey(username)&&energyMap.containsKey(username)){
                        healthMap.remove(username);
                        energyMap.remove(username);
                    }
                    if("All".equals(username)){
                        healthMap.clear();
                        energyMap.clear();
                    }
                    break;
            }

            input = scanner.nextLine();
        }
        System.out.println("People count: "+ energyMap.entrySet().size());
        healthMap.entrySet().stream().sorted((h1,h2)->{
            int result = h2.getValue().compareTo(h1.getValue());
            if(result==0){
                result = h1.getKey().compareTo(h2.getKey());
            }
            return result;
        }).forEach(e-> System.out.println(String.format("%s - %s - %s",e.getKey(),e.getValue(),energyMap.get(e.getKey()))));
    }
}
