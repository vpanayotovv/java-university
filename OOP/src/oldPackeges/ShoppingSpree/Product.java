package oldPackeges.ShoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        this.setName(name);
        this.setCost(cost);
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }

    public void setName(String name) {
        Validator.validateName(name);
        this.name = name.trim();
    }

    public void setCost(double cost) {
        Validator.validateMoney(cost);
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
