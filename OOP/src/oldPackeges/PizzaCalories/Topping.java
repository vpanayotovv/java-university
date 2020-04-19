package oldPackeges.PizzaCalories;

public class Topping {

    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    public double calculateCalories(){
        return this.weight * 2 * Ingredients.valueOf(this.toppingType.toUpperCase()).getCalories();
    }

    private void setToppingType(String toppingType) {
        if(!(Ingredients.CHEESE.toString().equalsIgnoreCase(toppingType)
        || Ingredients.MEAT.toString().equalsIgnoreCase(toppingType)
        || Ingredients.SAUCE.toString().equalsIgnoreCase(toppingType)
        || Ingredients.VEGGIES.toString().equalsIgnoreCase(toppingType))){
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException( this.toppingType + " weight should be in the range [1..50].");
        }
        this.weight = weight;
    }
}
