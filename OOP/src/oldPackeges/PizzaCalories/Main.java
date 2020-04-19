package oldPackeges.PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String[] pizzaLine = scanner.nextLine().split("\\s+");
            String pizzaName = pizzaLine[1];
            int numbersOfToppings = Integer.parseInt(pizzaLine[2]);
            Pizza pizza = new Pizza(pizzaName, numbersOfToppings);
            String[] doughLine = scanner.nextLine().split("\\s+");
            Dough dough = new Dough(doughLine[1], doughLine[2], Integer.parseInt(doughLine[3]));
            pizza.setDough(dough);
            String line = scanner.nextLine();
            while (!line.equals("END")) {
                String[] toppingLine = line.split("\\s+");
                Topping topping = new Topping(toppingLine[1], Double.parseDouble(toppingLine[2]));
                pizza.addTopping(topping);
                line = scanner.nextLine();
            }
            System.out.println(pizzaName + " - " + pizza.getOverallCalories());
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}
