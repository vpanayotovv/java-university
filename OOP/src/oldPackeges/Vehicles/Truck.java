package oldPackeges.Vehicles;

public class Truck extends Vehicles {
    public Truck(double fuel, double fuelConsumption,int tankCapacity) {
        super(fuel, fuelConsumption + 1.6,tankCapacity);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }
}
