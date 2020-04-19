package oldPackeges.PriceCalc;

public enum DiscountType {
    VIP(0.2),
    SECONDVISIT(0.1),
    NONE(0.0);

    private double discount;

    DiscountType(double discount) {
        this.discount = discount;
    }
    public double discountFor(double amount){
        double discount = amount * this.discount;
        return amount - discount;
    }
}