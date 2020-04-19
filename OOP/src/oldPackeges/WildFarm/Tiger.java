package oldPackeges.WildFarm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    protected void eat(Food food) {
        if (food.getType().equals("Vegetable")){
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }else {
            super.eat(food);
        }
    }
}
