import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lootbox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner ( System.in );

        ArrayDeque<Integer> firstLootBox = Arrays.stream (scanner.nextLine ().split ( "\\s+" ))
                .map ( Integer::parseInt )
                .collect ( Collectors.toCollection ( ArrayDeque::new ) );

        ArrayDeque<Integer> secondLootBox = new ArrayDeque<> ();
        Arrays.stream ( scanner.nextLine ().split ( "\\s+" ) )
                .mapToInt ( Integer::parseInt )
                .forEach ( secondLootBox::push );

        int value = 0;

        while (!firstLootBox.isEmpty () && !secondLootBox.isEmpty ()){
            int elementFirstLookBox = firstLootBox.peek ();
            int elementSecondLookBox = secondLootBox.pop ();
            int sum = elementFirstLookBox + elementSecondLookBox;

            if(sum % 2 ==0){
                value +=sum;
                elementFirstLookBox = firstLootBox.poll ();
            }else {
                firstLootBox.addLast ( elementSecondLookBox );
            }
        }

        if(firstLootBox.isEmpty ()){
            System.out.println ("First lootbox is empty");
        } else {
            System.out.println ("Second lootbox is empty");
        }

        if(value >=100){
            System.out.println (String.format ( "Your loot was epic! Value: %d",value ));
        } else {
            System.out.println (String.format ( "Your loot was poor... Value: %d",value ));
        }
    }
}
