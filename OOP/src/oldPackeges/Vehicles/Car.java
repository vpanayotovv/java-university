package oldPackeges.Vehicles;

public class Car extends Vehicles{

    public Car(double fuel, double fuelConsumption,int tankCapacity) {
        super(fuel, fuelConsumption + 0.9,tankCapacity);
    }
}
