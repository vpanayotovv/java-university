package oldPackeges.PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numbersOfToppings) {
        this.setName(name);
        this.setToppings(numbersOfToppings);
    }

    public double getOverallCalories() {
        return this.dough.calculateCalories() + this.toppings.stream().mapToDouble(Topping::calculateCalories).sum();
    }
    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    public String getName(String name) {
        return name;
    }

    private void setToppings(int numbersOfToppings) {
        if (numbersOfToppings < 0 || numbersOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>(numbersOfToppings);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.trim().length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name.trim();
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }
}
