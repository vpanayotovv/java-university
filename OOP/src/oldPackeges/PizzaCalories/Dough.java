package oldPackeges.PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private int weight;

    public Dough(String flourType, String bakingTechnique, int weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public double calculateCalories(){
        return 2 * this.weight
                * Ingredients.valueOf(this.flourType.toUpperCase()).getCalories()
                * Ingredients.valueOf(this.bakingTechnique.toUpperCase()).getCalories();
    }

    private void setFlourType(String flourType) {
        if (!(Ingredients.WHITE.toString().equalsIgnoreCase(flourType)
                || Ingredients.WHOLEGRAIN.toString().equalsIgnoreCase(flourType))){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!(Ingredients.CRISPY.toString().equalsIgnoreCase(bakingTechnique)
                || Ingredients.CHEWY.toString().equalsIgnoreCase(bakingTechnique)
                || Ingredients.HOMEMADE.toString().equalsIgnoreCase(bakingTechnique))){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(int weight) {
        if (weight < 0 || weight > 200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
}
