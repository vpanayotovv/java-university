package oldPackeges.WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Animal> animals = new ArrayList<>();
        while (!input.equals("End")) {
            String[] firstInputLine = input.split("\\s+");
            String animalType = firstInputLine[0];
            String animalName = firstInputLine[1];
            Double animalWeight = Double.parseDouble(firstInputLine[2]);
            String animalLivingRegion = firstInputLine[3];
            Animal animal;
            switch (animalType) {
                case "Mouse":
                    animal = new Mouse(animalType, animalName, animalWeight, animalLivingRegion);
                    break;
                case "Zebra":
                    animal = new Zebra(animalType, animalName, animalWeight, animalLivingRegion);
                    break;
                case "Tiger":
                    animal = new Tiger(animalType, animalName, animalWeight, animalLivingRegion);
                    break;
                default:
                    animal = new Cat(animalType, animalName, animalWeight, animalLivingRegion, firstInputLine[4]);
                    break;
            }
            animals.add(animal);
            String[] secondInputLine = scanner.nextLine().split("\\s+");
            String foodType = secondInputLine[0];
            int quantity = Integer.parseInt(secondInputLine[1]);
            animal.makeSound();
            if (foodType.equals("Meat")) {
                try {
                    Meat meat = new Meat(quantity);
                    animal.eat(meat);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                try {
                    Vegetable vegetable = new Vegetable(quantity);
                    animal.eat(vegetable);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            input = scanner.nextLine();
        }
        for (Animal animal1 : animals) {
            System.out.println(animal1);
        }
    }
}