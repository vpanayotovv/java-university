package console;

import domain.comands.*;
import domain.entities.Money;
import domain.external.Exchange;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.math.BigDecimal;

public class ConsoleComandParser {

    private final ConversionHistoryRepository repo;
    private final Logger logger;
    private final Exchange exchange;

    public ConsoleComandParser(ConversionHistoryRepository repo, Logger logger, Exchange exchange){

        this.repo = repo;
        this.logger = logger;
        this.exchange = exchange;
    }

    public void execute(String[] args) {
        switch (args[0]) {
            case "END":
                end();
                break;
            case "CONVERT":
                convert(args);
                break;
            case "HISTORY":
                history(args);
                break;
            default:
                logger.log("Incorrect command");
        }
    }

    private void history(String[] split) {
        Command<HistoryCommand.Input> cmd = new HistoryCommand(repo, logger);
        cmd.execute(new HistoryCommand.Input(Integer.parseInt(split[1])));
    }

    private void end() {
        new EndCommand().execute(new EmptyInput());
    }

    private void convert(String[] split) {
        BigDecimal fromValue = new BigDecimal(split[1]);
        String fromCurrency = split[2];
        String toCurrency = split[3];
        ConvertCommand.Input input = new ConvertCommand.Input(new Money(fromValue, fromCurrency), toCurrency);

        new HistorySavingConvertCommand(exchange, logger, repo).execute(input);
    }
}
