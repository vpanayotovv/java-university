package external;

import domain.entities.Money;
import domain.external.Exchange;

import java.math.BigDecimal;

public class StabbedExchange implements Exchange {
    @Override
    public Money convert(Money from, String toCurrency) {
        return new Money(BigDecimal.ONE,"USD");
    }
}
