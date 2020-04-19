package oldPackeges.WildFarm;

public class Mouse extends Mammal {
    public Mouse(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }


    @Override
    protected void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    protected void eat(Food food) {
        if (food.getType().equals("Meat")){
            throw new IllegalArgumentException( "Mice are not eating that type of food!");
        }else {
            super.eat(food);
        }
    }
}
