package Vehicles;

public class Truck extends Vehicle{
    private final static double AIR_CONDITIONER = 1.6;

    public Truck(double fuel, double consumption,double tankCapacity) {
        super ( fuel, consumption + AIR_CONDITIONER,tankCapacity);
    }

    @Override
    public void refuel(double liters) {
        super.refuel ( liters * 0.95);
    }
}
