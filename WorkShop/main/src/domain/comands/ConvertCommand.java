package domain.comands;

import domain.entities.Money;
import domain.external.Exchange;
import domain.io.Logger;

public class ConvertCommand implements Command<ConvertCommand.Input> {
    private final Exchange exchange;
    private final Logger logger;

    public ConvertCommand(Exchange exchange, Logger logger) {
        this.exchange = exchange;
        this.logger = logger;
    }

    @Override
    public void execute(Input input) {
        this.protectedExecute(input);
    }

    protected Money protectedExecute(Input input){
        Money converted = exchange.convert(input.from, input.toCurrency);
        logger.log(converted.toString().trim());
        return converted;
    }
    public static class Input extends EmptyInput {

        private final Money from;
        private final String toCurrency;

        public Input(Money from, String toCurrency) {
            this.from = from;
            this.toCurrency = toCurrency;
        }

        public Money getFrom() {
            return this.from;
        }

        public String getToCurrency() {
            return this.toCurrency;
        }


    }

    @Override
    public String toString() {
        return "ConvertCommand{" +
                "exchange=" + exchange +
                ", logger=" + logger +
                '}';
    }
}
