package oldPackeges.ma3x.cards;

public abstract class Card {
    private double discount;

    public Card(double discount) {
        this.discount = discount;
    }

    public final Double discountedPrice(Double price){
        Double discountSum = price * this.discount;
        return price - discountSum;
    }
}
