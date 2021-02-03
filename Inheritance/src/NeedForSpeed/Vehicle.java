package NeedForSpeed;

public class Vehicle {
    protected final static double DEFAULT_FUEL_CONSUMPTION = 1.25;
    private double fuelConsumption;
    private double fuel;
    private int horsePower;

   public Vehicle(double fuel,int horsePower){
       this.setFuel(fuel);
       this.setHorsePower(horsePower);
       this.fuelConsumption= DEFAULT_FUEL_CONSUMPTION;
   }

   public void drive(double kilometers){
       double usedFuel = kilometers*fuelConsumption;
       if(usedFuel<=fuel){
           fuel=fuel - usedFuel;
       }
   }

    public static double getDefaultFuelConsumption() {
        return DEFAULT_FUEL_CONSUMPTION;
    }

    public double getFuel() {
        return fuel;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
