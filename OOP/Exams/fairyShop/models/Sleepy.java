package fairyShop.models;

public class Sleepy extends BaseHelper{
    private int energy;
    private static final int DEFAULT_ENERGY = 50;
    public Sleepy(String name) {
        super(name, DEFAULT_ENERGY);
    }

    @Override
    public void work() {
        if (this.energy - 15 < 0) {
            this.energy = 0;
        } else {
            this.energy -= 15;
        }
    }
}
