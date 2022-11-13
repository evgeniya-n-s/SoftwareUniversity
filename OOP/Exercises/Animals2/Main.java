package Animals2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner ( System.in );

        List<Animal> animals = new ArrayList<> ();

        String input = scanner.nextLine ();

        while (!input.equals ( "Beast!" )){
            String[] tokens = scanner.nextLine ().split ( "\\s+" );
            String name = tokens[0];
            int age = Integer.parseInt ( tokens[1] );
            String gender = tokens[2];

            try {
                Animal animal= Animal.createAnimal ( input,name,age,gender );
                animals.add ( animal );
            }catch (IllegalArgumentException ex){
                System.out.println (ex.getMessage ());
            }
            input = scanner.nextLine ();

        }

        for (Animal animal : animals) {
            System.out.println (animal);
        }

    }
}
