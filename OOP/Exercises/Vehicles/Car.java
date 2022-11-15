package Vehicles;

public class Car extends Vehicle{
    private final static double AIR_CONDITIONER = 0.9;

    public Car(double fuel, double consumption,double tankCapacity) {
        super ( fuel, consumption + AIR_CONDITIONER,tankCapacity);
    }
}
