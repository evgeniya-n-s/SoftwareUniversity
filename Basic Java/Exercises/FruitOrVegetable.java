import java.util.Scanner;

public class FruitOrVegetable {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine().toLowerCase();

        if (name.equals("banana") || name.equals("apple") || name.equals("kiwi") || name.equals("cherry") || name.equals("lemon") || name.equals("grapes")){
            System.out.print("fruit");
        }
        else if (name.equals("tomato") || name.equals("cucumber") || name.equals("pepper") || name.equals("carrot")){
            System.out.print("vegetable");
        }
        else{
            System.out.print("unknown");
        }
    }
}

