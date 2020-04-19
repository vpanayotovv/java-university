package oldPackeges.ma3x;

import oldPackeges.ma3x.cards.Card;
import oldPackeges.ma3x.cards.GoldCard;

public class Main {
    public static void main(String[] args) {
        Card card = new GoldCard();

        System.out.println(card.discountedPrice(10.0));

    }
}
