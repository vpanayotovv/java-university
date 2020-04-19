package oldPackeges.PizzaCalories;

public enum Ingredients {
    WHITE(1.5),
    WHOLEGRAIN(1.0),
    CRISPY(0.9),
    CHEWY(1.1),
    HOMEMADE(1.0),
    MEAT(1.2),
    VEGGIES(0.8),
    CHEESE(1.1),
    SAUCE(0.9);

    private double calories;

    Ingredients(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return this.calories;
    }
}
