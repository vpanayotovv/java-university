package oldPackeges.WildFarm;

public class Zebra extends Mammal {
    public Zebra(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("Zs");
    }

    protected void eat(Food food) {
        if (Food.class.getSimpleName().equals("Meat")){
            throw new IllegalArgumentException( "Zebra are not eating that type of food!");
        }else {
            super.eat(food);
        }
    }

}
