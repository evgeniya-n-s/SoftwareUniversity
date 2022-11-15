package Vehicles;

public class Bus extends Vehicle{
    private final static double AIR_CONDITIONER = 1.4;

    public Bus(double fuel, double consumption, double tankCapacity) {
        super ( fuel, consumption, tankCapacity );
    }

    public String driveWithPassengers(double distance){
        super.addConsumption ( AIR_CONDITIONER );
        String out = super.drive ( distance );
        super.removeConsumption ( AIR_CONDITIONER );
        return out;
    }
}
