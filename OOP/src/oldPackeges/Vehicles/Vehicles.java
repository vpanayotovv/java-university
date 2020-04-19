package oldPackeges.Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicles {
    private double fuel;
    private double fuelConsumption;
    private DecimalFormat format;
    private int tankCapacity;



    protected Vehicles(double fuel, double fuelConsumption,int tankCapacity) {
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.format = new DecimalFormat("##.##");
        this.tankCapacity = tankCapacity;
    }

    protected void drive(double distance){
        double fuelNeeded = distance * this.fuelConsumption;
        if (fuelNeeded > this.fuel){
            throw new IllegalStateException(this.getClass().getSimpleName() +" needs refueling");
        }
        else {
            this.fuel -= fuelNeeded;
            throw new IllegalStateException(this.getClass().getSimpleName() +" travelled "+ format.format(distance) +" km");
        }
    }

    protected void busDriveWithPeople(double distance){
        double fuelNeeded = distance * (this.fuelConsumption + 1.4);
        if (fuelNeeded > this.fuel){
            throw new IllegalStateException(this.getClass().getSimpleName() +" needs refueling");
        }
        else {
            this.fuel -= fuelNeeded;
            throw new IllegalStateException(this.getClass().getSimpleName() +" travelled "+ format.format(distance) +" km");
        }
    }

    protected void refuel(double liters){
        if(this.fuel + liters > this.tankCapacity){
            throw new IllegalStateException("Cannot fit fuel in tank");
        }
        this.fuel += liters;
    }

    @Override
    public String toString() {
        return String.format(": %.2f",this.fuel);
    }
}
