package domain.comands;

import domain.entities.Money;
import domain.external.Exchange;
import domain.io.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ConvertCommandTest {
    @Test
    public void converting_bgn_to_usd_using_rate_2x_return_usd() {

        Exchange exchange = Mockito.mock(Exchange.class);
        Logger logger = Mockito.mock(Logger.class);
        Money sixLeva = new Money(new BigDecimal(6), "BGN");
        Money treeUsd = new Money(new BigDecimal(3), "USD");
        ConvertCommand.Input input = new ConvertCommand.Input(sixLeva, "USD");
        Mockito.when(exchange.convert(sixLeva, "USD")).thenReturn(treeUsd);
        ConvertCommand convertCommand = new ConvertCommand(exchange, logger);
        convertCommand.execute(input);
        Mockito.verify(exchange, Mockito.times(1)).convert(sixLeva,"USD");
}
}