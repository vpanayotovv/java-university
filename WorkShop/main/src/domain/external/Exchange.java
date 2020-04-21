package domain.external;

import domain.entities.Money;

public interface Exchange {
    Money convert(Money from, String toCurrency);
}
