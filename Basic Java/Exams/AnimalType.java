import java.util.Scanner;

public class AnimalType {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animal = scanner.nextLine();
//        dog -&gt; mammal
// crocodile, tortoise, snake -&gt; reptile
// others -&gt; unknown

        switch (animal) {
            case "dog":
                System.out.print("mammal");
                break;
            case "crocodile":
            case "tortoise":
            case "snake":
                System.out.print("reptile");
                break;
            default:
                System.out.print("unknown");
                break;

        }
    }
}
