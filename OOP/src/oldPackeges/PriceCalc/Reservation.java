package oldPackeges.PriceCalc;


public class Reservation {
    private double pricePerDay;
    private int numberOfDay;
    private Season season;
    private DiscountType discountType;

    public Reservation(double pricePerDay, int numberOfDay,Season season, DiscountType discountType) {
        this.pricePerDay = pricePerDay;
        this.numberOfDay = numberOfDay;
        this.season = season;
        this.discountType = discountType;
    }
    public double calc() {
        double basePrice = pricePerDay * numberOfDay;
        basePrice = season.fixedPrice(basePrice);
        basePrice = discountType.discountFor(basePrice);

        return basePrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "pricePerDay=" + pricePerDay +
                ", numberOfDay=" + numberOfDay +
                ", season=" + season +
                ", discountType=" + discountType +
                '}';
    }

}
