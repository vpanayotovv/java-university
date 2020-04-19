package oldPackeges.WildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;
    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion,String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    @Override
    protected void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
       return String.format("%s[%s, %s, %s, %s, %d]",getAnimalType(),getAnimalName(),this.breed,new DecimalFormat("##.##").format(getAnimalWeight()),getLivingRegion(),getFoodEaten());
    }
}
