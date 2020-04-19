package oldPackeges.Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(input[1]),Double.parseDouble(input[2]),Integer.parseInt(input[3]));
        input = scanner.nextLine().split("\\s+");
        Truck truck = new Truck(Double.parseDouble(input[1]),Double.parseDouble(input[2]),Integer.parseInt(input[3]));
        input = scanner.nextLine().split("\\s+");
        Bus bus = new Bus(Double.parseDouble(input[1]),Double.parseDouble(input[2]),Integer.parseInt(input[3]));
        int n = Integer.parseInt(scanner.nextLine());
        while (n -- > 0){
            String[] command = scanner.nextLine().split("\\s+");
            switch (command[0]) {
                case "Drive":
                    switch (command[1]) {
                        case "Car":
                            try {
                                car.drive(Double.parseDouble(command[2]));
                            } catch (IllegalStateException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        case "Truck":
                            try {
                                truck.drive(Double.parseDouble(command[2]));
                            } catch (IllegalStateException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        default:
                            try {
                                bus.busDriveWithPeople(Double.parseDouble(command[2]));
                            } catch (IllegalStateException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                    }
                    break;
                case "Refuel":
                    if (Double.parseDouble(command[2]) < 1) {
                        System.out.println("Fuel must be a positive number");
                    } else {
                        try {
                            switch (command[1]) {
                                case "Car":
                                    car.refuel(Double.parseDouble(command[2]));
                                    break;
                                case "Truck":
                                    truck.refuel(Double.parseDouble(command[2]));
                                    break;
                                default:
                                    bus.refuel(Double.parseDouble(command[2]));
                                    break;
                            }
                        } catch (IllegalStateException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                case "DriveEmpty":
                    try {
                        bus.drive(Double.parseDouble(command[2]));
                    } catch (IllegalStateException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
            }
        }
        System.out.println("Car" + car);
        System.out.println("Truck" + truck);
        System.out.println("Bus" + bus);
    }
}
