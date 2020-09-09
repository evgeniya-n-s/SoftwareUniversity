import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HeroesОfCodeАndLogic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> firstHeroesPParty = new LinkedHashMap<>();
        Map<String, Integer> secondHeroesParty = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            String name = input[0];
            int firstHP = Integer.parseInt(input[1]);
            int firstMP = Integer.parseInt(input[2]);

            firstHeroesPParty.putIfAbsent(name, firstHP);
            secondHeroesParty.putIfAbsent(name, firstMP);
            if (firstHeroesPParty.get(name) > 100) {
                firstHeroesPParty.putIfAbsent(name, 100);
            }
            if (secondHeroesParty.get(name) > 200) {
                secondHeroesParty.put(name, 200);
            }
        }
        String command = scanner.nextLine();
        while (!"End".equals(command)) {
            String[] tokens = command.split(" - ");
            String actions = tokens[0];
            String heroName = tokens[1];

            switch (actions) {
                case "CastSpell":
                    int neededMP = Integer.parseInt(tokens[2]);
                    String spellName = tokens[3];
                    if(secondHeroesParty.get(heroName)>=neededMP){
                        int reduceMP = secondHeroesParty.get(heroName) - neededMP;
                        secondHeroesParty.put(heroName,reduceMP);
                        System.out.println(String.format("%s has successfully cast %s and now has %d MP!",heroName,spellName,reduceMP));
                    }else {
                        System.out.println(String.format("%s does not have enough MP to cast %s!",heroName,spellName));
                    }
                    break;
                case "TakeDamage":
                    int damage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    firstHeroesPParty.put(heroName,firstHeroesPParty.get(heroName) - damage);
                    if(firstHeroesPParty.get(heroName) > 0){
                        int currentHP = firstHeroesPParty.get(heroName);
                        System.out.println(String.format("%s was hit for %d HP by %s and now has %d HP left!",heroName,damage,attacker,currentHP));
                    } else {
                        firstHeroesPParty.remove(heroName);
                        System.out.println(String.format("%s has been killed by %s!",heroName,attacker));
                    }
                    break;
                case "Recharge":
                    int amountMP = Integer.parseInt(tokens[2]);
                    int increaseMP = secondHeroesParty.get(heroName) + amountMP;
                    if(increaseMP>200){
                        int calculateIncreaseMP = 200-secondHeroesParty.get(heroName);
                        secondHeroesParty.put(heroName,200);
                        System.out.println(String.format("%s recharged for %d MP!",heroName,calculateIncreaseMP));
                    }else {
                        secondHeroesParty.put(heroName,increaseMP);
                        System.out.println(String.format("%s recharged for %d MP!",heroName,amountMP));
                    }
                    break;
                case "Heal":
                    int amountHP = Integer.parseInt(tokens[2]);
                    int increaseHP = firstHeroesPParty.get(heroName) + amountHP;
                    if(increaseHP>100){
                        int calculateIncreaseHP = 100 - firstHeroesPParty.get(heroName);
                        firstHeroesPParty.put(heroName,100);
                        System.out.println(String.format("%s healed for %d HP!",heroName,calculateIncreaseHP));
                    } else {
                        firstHeroesPParty.put(heroName,increaseHP);
                        System.out.println(String.format("%s healed for %d HP!",heroName,amountHP));
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        firstHeroesPParty
                .entrySet()
                .stream()
                .sorted((x1,x2)->{
                    int result = x2.getValue().compareTo(x1.getValue());
                    if(result==0){
                        result = x1.getKey().compareTo(x2.getKey());
                    }
                    return result;
                })
                .forEach(i-> System.out.println(String.format("%s%n  HP: %d%n  MP: %d",
                        i.getKey(),i.getValue(),secondHeroesParty.get(i.getKey()))));
    }
}
