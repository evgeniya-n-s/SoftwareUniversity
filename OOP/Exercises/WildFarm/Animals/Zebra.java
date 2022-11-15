package WildFarm.Animals;

import WildFarm.Food.Food;
import WildFarm.Food.Meat;

public class Zebra extends Mammal {
    public Zebra(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super ( animalName, animalType, animalWeight, livingRegion );
    }

    @Override
    public void eat(Food food) {
        if(food instanceof Meat){
            throw new IllegalArgumentException ("Zebras are not eating that type of food!");
        }
        super.eat ( food );
    }

    @Override
    public void makeSound() {
        System.out.println ("Zs");
    }
}

