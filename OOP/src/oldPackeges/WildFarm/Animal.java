package oldPackeges.WildFarm;

public abstract class Animal {
    private String animalType;
    private String animalName;
    private Double animalWeight;
    private Integer foodEaten;

    public Animal(String animalType, String animalName, Double animalWeight) {
        this.animalType = animalType;
        this.animalName = animalName;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    protected String getAnimalType() {
        return this.animalType;
    }

    protected String getAnimalName() {
        return this.animalName;
    }

    protected Double getAnimalWeight() {
        return this.animalWeight;
    }

    protected Integer getFoodEaten() {
        return this.foodEaten;
    }

    protected abstract void makeSound();

    protected void eat(Food food){
        this.foodEaten += food.getQuantity();
    }
}
