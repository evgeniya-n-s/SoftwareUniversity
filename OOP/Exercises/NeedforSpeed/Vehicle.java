package NeedforSpeed;

public class Vehicle {
    private final static double DEFAULT_FUEL_CONSUMPTION = 1.25;
    private double fuelConsumption;
    private  double fuel;
    private  int horsePower;

    public Vehicle(double fuel, int horsePower){
        this.setFuel ( fuel );
        this.setHorsePower ( horsePower );
        this.setFuelConsumption ( DEFAULT_FUEL_CONSUMPTION );
    }

    public void drive(double kilometers){
        double usedFuel = kilometers * fuelConsumption;
        if(usedFuel <= fuel){
            fuel -= usedFuel;
        }
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public static double getDefaultFuelConsumption() {
        return DEFAULT_FUEL_CONSUMPTION;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getFuel() {
        return fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }
}
