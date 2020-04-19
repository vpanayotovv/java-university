package oldPackeges.WildFarm;

public class Vegetable extends Food {
    protected Vegetable(int quantity) {
        super(quantity);
    }

    public String getType(){
        return "Vegetable";
    }

}
